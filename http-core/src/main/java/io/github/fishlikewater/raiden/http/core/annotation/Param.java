/*
 * Copyright © 2024 ${owner} (fishlikewater@126.com)
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
package io.github.fishlikewater.raiden.http.core.annotation;

import java.lang.annotation.*;

/**
 * 适用于url上的拼接参数
 * <pre>
 *     {@code
 *     @GET("https://www.baidu.com")
 *     String baidu3(@Param("wd") String keyWord);
 *     }
 * </pre>
 *
 * @author fishlikewater@126.com
 * @version 1.0.0
 * @since 2023年09月28日 20:30
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {

    String value() default "";
}
