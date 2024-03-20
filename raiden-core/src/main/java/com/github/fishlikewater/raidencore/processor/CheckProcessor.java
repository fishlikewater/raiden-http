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
package com.github.fishlikewater.raidencore.processor;

import com.github.fishlikewater.raidencore.HeadWrap;
import com.github.fishlikewater.raidencore.annotation.Heads;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * {@code CheckProcessor}
 * 编译检查
 *
 * @author zhangxiang
 * @date 2024/03/19
 * @since 1.0.0
 */
public class CheckProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> eleStrSet = roundEnv.getElementsAnnotatedWith(Heads.class);
        for (Element element : eleStrSet) {
            if (element.getKind() == ElementKind.PARAMETER) {
                VariableElement param = (VariableElement) element;
                TypeMirror typeMirror = param.asType();
                return this.valid(typeMirror, element);
            }
        }
        return true;
    }

    private boolean valid(TypeMirror typeMirror, Element element) {
        if (typeMirror instanceof DeclaredType declaredType) {
            List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
            if (declaredType.asElement().toString().equals(Map.class.getName())) {
                TypeMirror keyType = typeArguments.get(0);
                TypeMirror valueType = typeArguments.get(1);

                // 这里只是一个简单的判断示例，你可以根据实际需求进一步检查
                boolean isKeyTypeString = keyType.toString().equals(String.class.getName());
                boolean isValueTypeObject = valueType.toString().equals(String.class.getName());
                if (isKeyTypeString && isValueTypeObject) {
                    return true;
                } else {
                    this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "只支持Map<String, String>与HeadWrap参数类型", element);
                    return false;
                }
            }
            return declaredType.asElement().toString().equals(HeadWrap.class.getName());
        }
        return false;
    }


    /**
     * 这个方法虽然在父类当中不是 abstract的，但是我们也必须实现。
     * 因为该方法的作用是指定我们要处理哪些注解的，
     * 比如你想处理注解MyAnnotation,可是该处理器怎么知道你想处理MyAnnotation，而不是OtherAnnotation呢。
     * 所以你要在这里指明，你需要处理的注解的全称。
     * 返回值是一个字符串的集合，包含着本处理器想要处理的注解类型的合法全称。
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> strings = new TreeSet<>();
        strings.add("com.github.fishlikewater.raidencore.annotation.Heads");
        return strings;
    }

    /**
     * 本方法用来指明你支持的java版本，
     * 不过一般使用 SourceVersion.latestSupported() 就可以了。
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
