package com.zhangzeyuan.learnarithmetic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author zeyuan zhang
 * @Date 2025/3/23 下午2:49
 * @Version 1.0
 */
@MapperScan("com.zhangzeyuan.learnarithmetic.mapper")
@SpringBootApplication
public class LearnAirthStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(LearnAirthStarterApp.class, args);
    }
}
