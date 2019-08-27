package com.forezp;

import com.forezp.service.Service;
import com.forezp.service.DataConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Import(DataConfiguration.class)
@SpringBootApplication
public class Application {

    @Autowired
    Service service;

    @GetMapping("/")
    public String home() {
        return service.message();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
