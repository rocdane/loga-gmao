package tech.loga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingServer {

    public static void main(String[] args) {
        SpringApplication.run(NamingServer.class, args);
    }

}