/*
 * Copyright © 2024 zhangxiang (fishlikewater@126.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.fishlikewater.raidencore.proxy;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ObjectUtil;
import com.github.fishlikewater.raidencore.HeadWrap;
import com.github.fishlikewater.raidencore.HttpBootStrap;
import com.github.fishlikewater.raidencore.MethodArgsBean;
import com.github.fishlikewater.raidencore.MultipartData;
import com.github.fishlikewater.raidencore.annotation.Body;
import com.github.fishlikewater.raidencore.annotation.Heads;
import com.github.fishlikewater.raidencore.annotation.Param;
import com.github.fishlikewater.raidencore.annotation.PathParam;
import com.github.fishlikewater.raidencore.enums.HttpMethod;
import com.github.fishlikewater.raidencore.interceptor.HttpClientInterceptor;
import com.github.fishlikewater.raidencore.processor.HttpClientBeanFactory;
import com.github.fishlikewater.raidencore.processor.HttpClientProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author fishlikewater@126.com
 * @date 2023年09月23日 18:30
 * @since 1.0.0
 **/
public interface InterfaceProxy {

    /**
     * 处理请求
     *
     * @param method 方法
     * @param args 参数
     * @param httpClientProcessor {@code HttpClientProcessor}
     * @param httpClientBeanFactory {@code HttpClientBeanFactory}
     * @return Object
     */
    default Object handler(Method method, Object[] args, HttpClientProcessor httpClientProcessor, HttpClientBeanFactory httpClientBeanFactory) {
        String name = method.toGenericString();
        final MethodArgsBean methodArgsBean = httpClientBeanFactory.getMethodArgsBean(name);
        if (Objects.nonNull(HttpBootStrap.getPredRequest())) {
            HttpBootStrap.getPredRequest().handler(methodArgsBean);
        }
        HttpMethod httpMethod = methodArgsBean.getRequestMethod();
        final boolean form = methodArgsBean.isForm();
        final Parameter[] parameters = methodArgsBean.getUrlParameters();
        String url = methodArgsBean.getUrl();
        final Class<?> returnType = methodArgsBean.getReturnType();
        final Type typeArgument = methodArgsBean.getTypeArgument();
        final String interceptorClassName = methodArgsBean.getInterceptorClassName();
        final HttpClientInterceptor interceptor = Objects.isNull(interceptorClassName) ? null : HttpBootStrap.getHttpClientInterceptor(interceptorClassName);
        Map<String, String> headMap = methodArgsBean.getHeadMap();
        Map<String, String> paramMap = MapUtil.newHashMap();
        Map<String, String> paramPath = MapUtil.newHashMap();
        final HttpClient httpClient = HttpBootStrap.getHttpClient(methodArgsBean.getSourceHttpClientName());
        Object bodyObject = null;
        MultipartData multipartData = null;
        /* 构建请求参数*/
        if (ObjectUtil.isNotNull(parameters)) {
            for (int i = 0; i < parameters.length; i++) {
                Param param = parameters[i].getAnnotation(Param.class);
                if (ObjectUtil.isNotNull(param)) {
                    paramMap.put(param.value(), (String) args[i]);
                    continue;
                }
                PathParam pathParam = parameters[i].getAnnotation(PathParam.class);
                if (ObjectUtil.isNotNull(pathParam)) {
                    paramPath.put(pathParam.value(), (String) args[i]);
                    continue;
                }
                Body body = parameters[i].getAnnotation(Body.class);
                if (ObjectUtil.isNotNull(body)) {
                    bodyObject = args[i];
                    continue;
                }
                Heads heads = parameters[i].getAnnotation(Heads.class);
                if (ObjectUtil.isNotNull(heads) && args[i] instanceof HeadWrap headWrap) {
                    headWrap.getHeads().forEach(head -> headMap.put(head.getKey(), head.getValue()));
                }
                if (args[i] instanceof MultipartData mData) {
                    multipartData = mData;
                }
            }
            if (!paramPath.isEmpty()) {
                url = StrFormatter.format(url, paramPath, true);
            }
        }
        return httpClientProcessor.handler(httpMethod, headMap, returnType, typeArgument, form, url, paramMap,
                bodyObject, interceptor, multipartData, httpClient);
    }

    /**
     * 获取代理对象
     *
     * @param interfaceClass 接口
     * @return T
     */
    <T> T getInstance(Class<T> interfaceClass);
}
