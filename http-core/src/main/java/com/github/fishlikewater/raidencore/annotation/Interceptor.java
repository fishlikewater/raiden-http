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
package com.github.fishlikewater.raidencore.annotation;

import com.github.fishlikewater.raidencore.interceptor.HttpClientInterceptor;

import java.lang.annotation.*;

/**
 * <p>
 *  拦截器注解
 * </p>
 *
 * @author fishlikewater@126.com
 * @date 2023年09月28日 20:30
 * @since 1.0.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Interceptor {

    /** 拦截器类*/
    Class<? extends HttpClientInterceptor> value();
}
