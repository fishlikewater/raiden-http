/*
 * Copyright (c) 2024 zhangxiang (fishlikewater@126.com)
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
package io.github.fishlikewater.raiden.http.core.processor;

import io.github.fishlikewater.raiden.http.core.RequestWrap;

/**
 * @author fishlikewater@126.com
 * @version 1.0.0
 * @since 2021年12月26日 18:42
 **/
public interface HttpClientProcessor {

    /**
     * 处理请求
     *
     * @param requestWrap {@code RequestWrap}
     * @return 响应数据
     */
    Object handler(RequestWrap requestWrap);
}
