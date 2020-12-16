package com.eholee.lobster.compiler;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import java.util.Map;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.tools.Diagnostic;
public abstract class BaseProcessor extends AbstractProcessor {

    /**
     * 用于在编译器打印消息的组件
     */
    Messager messager;

    /**
     * 语法树
     */
    JavacTrees trees;

    /**
     * 用来构造语法树节点
     */
    TreeMaker treeMaker;

    /**
     * 用于创建标识符的对象
     */
    Names names;
    Filer filer;

    public static final String MODULE_NAME_KEY = "LOBSTER_MODULE_NAME";
    public String classNameSuffix;

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    /**
     * 获取一些注解处理器执行处理逻辑时需要用到的一些关键对象
     *
     * @param processingEnv 处理环境
     */
    @Override
    public final synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);

        Map<String, String> options = processingEnv.getOptions();
        for (String key : options.keySet()) {
            if (key.equals(MODULE_NAME_KEY)) {
                classNameSuffix = options.get(key);
                break;
            }
        }
        if (classNameSuffix == null) {
            throw new IllegalArgumentException("使用了Lobster注解处理器，但未在build.gradle中配置annotationProcessorOptions参数 , 请添加LOBSTER_MODULE_NAME: project.getName()");
        }
        messager.printMessage(Diagnostic.Kind.NOTE , "moduleName:"+classNameSuffix);
    }
}