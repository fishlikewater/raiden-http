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
package io.github.fishlikewater.test.remote;

import io.github.fishlikewater.raiden.http.core.annotation.*;
import io.github.fishlikewater.test.interceptor.MyInterceptor;

import java.util.concurrent.CompletableFuture;

/**
 * {@code DemoRemote}
 *
 * @author fishlikewater@126.com
 * @version 1.0.0
 * <p>
 * 这里使用自定义httpclient 如不设置 则会使用默认的httpclient 默认的httpclient 访问https时会校验证书
 * @since 2024/03/15
 */
@HttpServer(sourceHttpClient = "customer")
@Interceptor(MyInterceptor.class)
public interface DemoRemote {

    /**
     * 测试访问百度 如不加协议 默认是http
     *
     * @return {@code String}
     */
    @GET("www.baidu.com")
    String baidu();

    /**
     * 测试访问百度 https
     *
     * @return {@code String}
     */
    @GET("https://www.baidu.com")
    String baidu2();

    /**
     * 测试添加url参数
     *
     * @param keyWord {@code String}
     * @return {@code String}
     */
    @GET("https://www.baidu.com")
    String baidu3(@Param("wd") String keyWord);

    /**
     * 测试添加url 占位参数
     *
     * @param keyWord {@code String}
     * @return {@code String}
     */
    @GET("https://www.baidu.com?wd={wd}")
    String baidu4(@PathParam("wd") String keyWord);

    /**
     * 测试异步访问
     *
     * @return {@code String}
     */
    @GET("https://www.baidu.com")
    CompletableFuture<String> baidu5();
}
