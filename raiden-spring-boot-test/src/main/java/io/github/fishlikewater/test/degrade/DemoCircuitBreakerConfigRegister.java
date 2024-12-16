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
package io.github.fishlikewater.test.degrade;

import io.github.fishlikewater.raiden.http.core.degrade.resilience4j.CircuitBreakerConfigRegister;
import io.github.fishlikewater.raiden.http.core.degrade.resilience4j.CircuitBreakerConfigRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * {@code DemoCircuitBreakerConfigRegister}
 *
 * @author zhangxiang
 * @since 2024/12/12
 */
@Component
public class DemoCircuitBreakerConfigRegister implements CircuitBreakerConfigRegister {
    @Override
    public void register(CircuitBreakerConfigRegistry registry) {
        registry.register("test", CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // 当失败率达到50%时打开断路器
                .waitDurationInOpenState(Duration.ofSeconds(10)) // 断路器打开状态持续时间
                .slidingWindowSize(10) // 滑动窗口大小
                .minimumNumberOfCalls(3) // 最少调用次数以激活断路器逻辑
                .build());
    }
}
