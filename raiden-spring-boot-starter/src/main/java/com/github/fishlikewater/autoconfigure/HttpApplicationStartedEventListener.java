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
package com.github.fishlikewater.autoconfigure;

import com.github.fishlikewater.raidencore.HttpBootStrap;
import com.github.fishlikewater.raidencore.interceptor.HttpClientInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.net.http.HttpClient;

/**
 * 接口注入
 *
 * @author fishlikewater@126.com
 * @date 2023年09月22日 11:12
 * @since 1.0.0
 **/
@Component
@Slf4j
public class HttpApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent  event) {
        final String[] namesForType = event.getApplicationContext().getBeanNamesForType(HttpClient.class);
        if (namesForType.length>0){
            final HttpClient httpClient = (HttpClient) event.getApplicationContext().getBean(namesForType[0]);
            HttpBootStrap.registerHttpClient("default", httpClient);
        }
        final String[] interceptors = event.getApplicationContext().getBeanNamesForType(HttpClientInterceptor.class);
        for (String interceptor : interceptors) {
            final HttpClientInterceptor httpClientInterceptor = (HttpClientInterceptor) event.getApplicationContext().getBean(interceptor);
            HttpBootStrap.setHttpClientInterceptor(httpClientInterceptor);
        }
    }
}