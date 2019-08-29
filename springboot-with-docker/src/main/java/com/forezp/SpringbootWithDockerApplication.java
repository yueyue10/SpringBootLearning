package com.forezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootWithDockerApplication {

	@RequestMapping("/")
	public String home() {
		return "Hello Docker World";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWithDockerApplication.class, args);
	}
}
//java -jar target/springboot-with-docker-0.0.1-SNAPSHOT.jar
//docker inspect -f {{.NetworkSettings.IPAddress}} 39545fd88c10
//docker exec 39545fd88c10 ip addr
//docker run -p 127.0.0.1:80:8080/tcp my_image:latest