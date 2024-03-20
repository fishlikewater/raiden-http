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
package com.github.fishlikewater.raidencore.processor;

import com.github.fishlikewater.raidencore.MethodArgsBean;
import com.github.fishlikewater.raidencore.interceptor.HttpClientInterceptor;
import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author fishlikewater@126.com
 * @date 2023年09月23日 20:25
 * @since 1.0.0
 **/
public interface HttpClientBeanFactory {

    MethodArgsBean getMethodArgsBean(String methodName);

    void cacheMethod(Method method);

    <T> T getProxyObject(Class<T> tClass);

    void cacheProxyObject(String className,  Object proxyObject);

    HttpClientInterceptor getInterceptor(String interceptorName);

    void setHttpClientInterceptor(HttpClientInterceptor httpClientInterceptor);
}
