package com.payu.payufinLogging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class PayufinLoggerApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PayufinLoggerApplication.class, args);
    }

}
