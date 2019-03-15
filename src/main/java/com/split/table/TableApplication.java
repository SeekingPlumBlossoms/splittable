package com.split.table;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yebin
 */
@SpringBootApplication
@MapperScan(basePackages = "com.split.table.mapper")
public class TableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TableApplication.class, args);
    }

}
