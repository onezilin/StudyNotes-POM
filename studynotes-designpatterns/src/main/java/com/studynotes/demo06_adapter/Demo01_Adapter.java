package com.studynotes.demo06_adapter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.studynotes.demo06_adapter.AdapterEntity.*;

/**
 * Description: 适配器模式：可以将原始类的方法转换成目标类
 */
@Slf4j
public class Demo01_Adapter {

    @Test
    public void test() {
        // 客户端只知道 Target 的 request()方法，需要将现有的 specificRequest() 转换成 request()
        Target target = new Adapter(new Source());
        target.request();
    }
}
