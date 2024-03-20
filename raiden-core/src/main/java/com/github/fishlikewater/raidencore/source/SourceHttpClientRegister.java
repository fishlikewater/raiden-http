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
package com.github.fishlikewater.raidencore.source;

/**
 * <p>
 *     HttpClient 注册
 * </p>
 *
 * @author fishlikewater@126.com
 * @date 2023年09月23日 10:14
 * @since 1.0.0
 **/
public interface SourceHttpClientRegister {

    /**
     * 向#{@link SourceHttpClientRegistry}注册数据
     *
     * @param registry SourceHttpClientRegistry
     */
    void register(SourceHttpClientRegistry registry);
}
