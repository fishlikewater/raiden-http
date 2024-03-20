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
package com.github.fishlikewater.raidencore;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.nio.file.OpenOption;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * 文件数据
 *
 * @author fishlikewater@126.com
 * @date 2023年09月26日 16:13
 * @since 1.0.0
 **/
@Data
@Accessors(chain = true)
public class MultipartData {

    private String[] paths;

    private File[] files;

    private Path path;

    private OpenOption[] openOptions = new OpenOption[]{CREATE, WRITE};

    private MultipartData(String[] paths) {
        this.paths = paths;
    }

    private MultipartData(File[] files) {
        this.files = files;
    }

    private MultipartData(Path path, OpenOption... openOptions) {
        this.path = path;
        this.openOptions = openOptions;
    }

    public static MultipartData ofFileUpload(String... paths) {
        return new MultipartData(paths);
    }

    public static MultipartData ofFileUpload(File... files) {
        return new MultipartData(files);
    }

    public static MultipartData ofFileDownload(Path path, OpenOption... openOptions) {
        return new MultipartData(path, openOptions);
    }
}
