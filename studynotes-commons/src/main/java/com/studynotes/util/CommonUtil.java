package com.studynotes.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Description: 通用工具类
 */
@Slf4j
public class CommonUtil {

    public static File getFile() {
        String fileName = "test.txt";
        String projectClassDirName = Objects.requireNonNull(CommonUtil.class.getResource("/")).getPath();
        return new File(projectClassDirName.substring(1) + fileName);
    }

    /**
     * Description: 推荐使用这种，上面的那种会有问题：
     * 在 test 测试包下，只会在 target/test-classes 下找到文件，而我们的文件是在 target/classes 下，所以会报错
     * 这种方式先在 target/test-classes 下找，找不到再去 target/classes 下找
     */
    public static File getFile2() throws IOException {
        String fileName = "test.txt";
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        return classPathResource.getFile();
    }

    public static File getFile(String fileName) {
        String projectClassDirName = Objects.requireNonNull(CommonUtil.class.getResource("/")).getPath();
        File file = new File(projectClassDirName.substring(1) + fileName);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    log.error("文件已存在，创建文件失败");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }
}
