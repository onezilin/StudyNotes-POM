package com.studynotes.java13_spi;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

/**
 * Description:
 */
public class Demo04_SPITest {

    /**
     * 测试步骤：
     * 1. 在 resources 目录下创建 META-INF/services 目录
     * 2. 在 resources/META-INF/services 目录下创建文件，文件名就是接口全路径名，例如：com.studynotes.springboot.demo03.Demo01_Search
     * 3. 在 com.studynotes.springboot.demo03.Demo01_Search 文件中写入要加载的实现类的全路径，如：com.studynotes.springboot.demo03
     * .Demo02_FileSearch
     * 4. 运行测试方法，查看控制台输出
     * <p>
     * 预期：
     * 1. 运行测试方法，执行了 Demo02_FileSearch 的 searchDoc() 方法
     * 2. 修改文件内容为 com.studynotes.springboot.demo03.Demo03_DatabaseSearch
     * 3. 运行测试方法，执行了 Demo03_DatabaseSearch 的 searchDoc() 方法
     */
    @Test
    void spiTest() {
        // ServiceLoader.load() 方法，可以获取接口对应在 META-INF/services/ 目录下以接口全限定名命名的文件，然后读取文件中的内容，加载实现类
        ServiceLoader<Demo01_Search> serviceLoader = ServiceLoader.load(Demo01_Search.class);
        for (Demo01_Search search : serviceLoader) {
            search.searchDoc("hello");
        }
    }
}
