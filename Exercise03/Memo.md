2019.03.02	19:55 -- 21:00

# 环境配置

教材用的是 Java FX，先安装 Java FX 环境。

参考了 https://openjfx.io/openjfx-docs/ ，使用以下两种方法配置 Java FX 开发环境。

### 使用构建工具 Gradle

由于我并不是很想下载 JavaFX SDK (虽然最后还是下了)，于是首先使用 gradle 进行构建。

需要在 gradle 脚本中对 javafxplugin 进行配置。配置完成后直接使用 gradle run 运行 JavaFX程序。

`build.gradle` 配置文件如下

```groovy
plugins {
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.0.7'
}

group 'moe.gogo'
version '0.0.0'

repositories {
    mavenCentral()
}

javafx {
    modules = ['javafx.controls']
}
mainClassName = "HelloWorldFX"

dependencies {
}

```

### 使用 JavaFX SDK 

首先下载 JavaFX SDK https://gluonhq.com/products/javafx/ 并配置环境变量 
`PATH_TO_FX="path\to\javafx-sdk-11.0.2\lib"`

参考文档，需要使用如下命令进行编译和运行 JavaFX 程序
```shell
javac --module-path %PATH_TO_FX% --add-modules=javafx.controls HelloFX.java
```

```shell
java --module-path %PATH_TO_FX% --add-modules=javafx.controls HelloFX
```

因此需要在 Idea 的 Run Configuration 中添加 VM options 
`--module-path "path\to\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls`

由于对 Java9 新增的 module 了解甚少，接着去查了一些 module 相关资料，理解了 module 大概是个什么东西，以及一些常见的用法。

### 遗留问题

若使用 gradle 工具运行 JavaFX 程序，则每次更换程序时都需要修改 `mainClassName` 参数以切换主函数。

若不使用 gradle ，则每个新的运行项都需要配置一遍 Run Configuration 添加 VM options 。

两种方法都比较麻烦，希望找到简化的方法，使每个 JavaFX 程序不需要额外配置就能运行。

网上查到可以使用 gradle 的插件 application ，通过配置 `applicationDefaultJvmArgs ` 添加默认运行参数。
继续往下看发现 application 插件本身便是使用 `gradle run` 运行 `mainClassName` 。
推断 javafxplugin 插件只是对 application 进行补充，添加了 javafx 默认配置项而已。

所以还是没找到简便的方法，看来模块化带来的不只有好事。

------

