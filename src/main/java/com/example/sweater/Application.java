package com.example.sweater;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserService userService) {
        return args -> {
            try {
                userService.loadUserByUsername("admin");


            } catch (UsernameNotFoundException e) {
                e.printStackTrace();

                User user1 = new User();
                user1.setUsername("admin");
                user1.setPassword("123");
                user1.setActive(true);
                user1.setEmail("admin@mail.com");
                user1.setActivationCode("none");

                userService.addAdmin(user1);
            }
        };
    }
}
