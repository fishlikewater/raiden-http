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
package io.github.fishlikewater.test.fallback;

import io.github.fishlikewater.raiden.http.core.degrade.FallbackFactory;
import io.github.fishlikewater.test.remote.DemoRemote;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * {@code DemoFallback}
 *
 * @author zhangxiang
 * @since 2024/12/12
 */
@Component
public class DemoFallback implements FallbackFactory<DemoRemote> {
    @Override
    public DemoRemote create(Throwable cause) {
        return new DemoRemote() {
            @Override
            public String baidu() {
                return "熔断了";
            }

            @Override
            public String baidu2() {
                return "熔断了";
            }

            @Override
            public String baidu3(String keyWord) {
                return "熔断了";
            }

            @Override
            public String baidu4(String keyWord) {
                return "熔断了";
            }

            @Override
            public CompletableFuture<String> baidu5() {
                return CompletableFuture.completedFuture("熔断了");
            }
        };
    }
}
