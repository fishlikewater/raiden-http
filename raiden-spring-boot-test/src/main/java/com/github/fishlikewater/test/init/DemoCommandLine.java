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
package com.github.fishlikewater.test.init;

import com.github.fishlikewater.raidencore.MultipartData;
import com.github.fishlikewater.test.remote.DemoFile;
import com.github.fishlikewater.test.remote.DemoLocal;
import com.github.fishlikewater.test.remote.DemoRemote;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * {@code DemoCommandLine}
 * 测试接口调用
 *
 * @author fishlikewater@126.com
 * @date 2024/03/20
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class DemoCommandLine implements CommandLineRunner {

    private final DemoRemote demoRemote;
    private final DemoLocal demoLocal;
    private final DemoFile demoFile;


    @Override
    public void run(String... args) throws InterruptedException {
        //this.testRemote();
        //this.testLocal();
        this.testFile();
        Thread.sleep(2000);
        System.exit(0);
    }

    private void testFile() {
        // 文件下载
        //MultipartData fileDownload = MultipartData.ofFileDownload(Path.of("/file/1.png"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        //demoFile.download(fileDownload);

        // 文件上传
        MultipartData multipartData = MultipartData.ofFileUpload("E:\\file\\1.png");
        demoFile.uploadFile(multipartData);
    }

    private void testRemote() {
        // 测试百度访问
        String s = demoRemote.baidu();
        System.out.println(s);

        //测试 https
        String s2 = demoRemote.baidu2();
        System.out.println(s2);

        // 测试路径参数
        String java1 = demoRemote.baidu3("java");
        System.out.println(java1);
        String java2 = demoRemote.baidu4("java");
        System.out.println(java2);

        //测试异步调用
        demoRemote.baidu5().thenAccept(System.out::println);
    }


    private void testLocal() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("username", "zs");
        map.put("password", "xxx");
       /* String demo = demoLocal.demo(map);
        System.out.println(demo);

        HeadWrap headWrap = new HeadWrap();
        headWrap.of("customer", "headWrap");
        demoLocal.demo(map, headWrap);*/

        // 测试form
        System.out.println(demoLocal.form(map));
    }
}