package com.fengxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描mybatis mapper包路径
@MapperScan("com.fengxin.mapper")


// 扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages= {"com.fengxin","org.n3r","com.fengxin.service","com.fengxin.mapper"})
public class Application {
    public static void main(String[] args) {
        System.out.println("Start");
        SpringApplication.run(Application.class, args);
        System.out.println("END");


    }
}