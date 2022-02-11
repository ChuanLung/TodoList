package com.linda.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TodoListApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(TodoListApplication.class, args);
        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//            System.out.println();
//        }
    }
}
