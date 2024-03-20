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
package com.github.fishlikewater.raidencore.interceptor;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * <p>
 *  请求拦截器
 * </p>
 *
 * @author fishlikewater@126.com
 * @date 2023年09月22日 19:07
 * @since 1.0.0
 **/
public interface HttpClientInterceptor {

    /**
     *
     * @param httpRequest 请求数据
     * @date 2023/9/22 19:59
     * @author fishlikewater@126.com
     */
    HttpRequest requestBefore(HttpRequest httpRequest);

    /**
     *
     * @param response 响应
     * @date 2023/9/22 19:59
     * @author fishlikewater@126.com
     */
    <T> HttpResponse<T> requestAfter(HttpResponse<T> response);
}
