package ru.example.springsecurity.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.example.springsecurity.models.Role;
import ru.example.springsecurity.models.User;
import ru.example.springsecurity.repository.RoleRepo;
import ru.example.springsecurity.repository.UserRepo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(RoleRepo roleRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        return args -> {

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepo.save(adminRole);


            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepo.save(userRole);

            User admin = new User();
            admin.setFirstName("admin");
            admin.setLastName("adminovich");
            admin.setAge((byte) 25);
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setEmail("admin@gmail.com");
            admin.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
            userRepo.save(admin);

            User user = new User();
            user.setFirstName("user");
            user.setLastName("userovich");
            user.setAge((byte) 18);
            user.setPassword(passwordEncoder.encode("user"));
            user.setEmail("user@mail.ru");
            user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
            userRepo.save(user);
        };
    }
}
