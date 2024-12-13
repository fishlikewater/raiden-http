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
package io.github.fishlikewater.test.controller;

import io.github.fishlikewater.raiden.http.core.MultipartData;
import io.github.fishlikewater.test.remote.DemoFile;
import io.github.fishlikewater.test.remote.DemoRemote;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * {@code DemoRemoteController}
 *
 * @author zhangxiang
 * @since 2024/12/13
 */
@RestController
@RequiredArgsConstructor
public class DemoRemoteController {

    private final DemoRemote demoRemote;
    private final DemoFile demoFile;

    @GetMapping("/remote")
    public String remote() {
        return demoRemote.baidu();
    }

    @GetMapping("/remote2")
    public String remote2() {
        return demoRemote.baidu3("test");
    }

    @GetMapping("/remote3")
    public String remote3() throws ExecutionException, InterruptedException {
        return demoRemote.baidu5().get();
    }

    @GetMapping("/remote/upload")
    public String remoteUpload() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("1.png");
        assert resource != null;
        File file = new File(resource.toURI());
        return demoFile.uploadFile(MultipartData.ofFileUpload(file));
    }
}
