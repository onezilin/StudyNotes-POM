package com.studynotes.java99_other.demo02_date;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Description: 通过 LocalDateTime.of() 方法创建 LocalDateTime 对象
 */
@Slf4j
public class Demo07_LocalDateTime {
    @Test
    void test() {
        LocalDateTime yesterday = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(yesterday, now);
        System.out.println(duration.toHours());
    }

}
