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

import io.github.fishlikewater.raiden.http.core.constant.DefaultConstants;
import io.github.fishlikewater.raiden.http.core.degrade.DefaultFallbackFactory;
import io.github.fishlikewater.raiden.http.core.degrade.FallbackFactory;
import io.github.fishlikewater.raiden.http.core.enums.DegradeType;

import java.lang.annotation.*;

/**
 * {@code Degrade}
 * 熔断注解
 *
 * @author zhangxiang
 * @version 1.1.0
 * @since 2024/12/10
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Degrade {

    DegradeType type() default DegradeType.RESILIENCE4J;

    String circuitBreakerConfigName() default DefaultConstants.DEFAULT_CIRCUIT_BREAKER_CONFIG;

    Class<? extends FallbackFactory<?>> fallback() default DefaultFallbackFactory.class;
}