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
package io.github.fishlikewater.test.interceptor;

import io.github.fishlikewater.raiden.http.core.Response;
import io.github.fishlikewater.raiden.http.core.interceptor.HttpInterceptor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author fishlikewater@126.com
 * @since 2023年09月23日 13:41
 **/
@Component
public class MyInterceptor implements HttpInterceptor {

    @Override
    public Response intercept(Chain chain) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public int order() {
        return 0;
    }
}
