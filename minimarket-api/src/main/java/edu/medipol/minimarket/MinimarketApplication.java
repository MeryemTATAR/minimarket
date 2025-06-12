package edu.medipol.minimarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@EnableAsync  
public class MinimarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinimarketApplication.class, args);
    }
}