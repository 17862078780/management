package com.sunniwell;


import com.mongodb.MongoClient;
import com.sunniwell.common.utils.IdWorker;
import com.sunniwell.common.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }


    public @Bean
    BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public @Bean
    JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    public @Bean
    MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "management");
    }

    public @Bean
    IdWorker idWorker() {
        return new IdWorker();
    }
}
