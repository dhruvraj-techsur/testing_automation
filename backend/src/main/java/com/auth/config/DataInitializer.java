package com.auth.config;

import com.auth.entity.User;
import com.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Create admin user if not exists
        if (!userRepository.existsByEmail("admin@example.com")) {
            User adminUser = new User();
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("password123"));
            adminUser.setFirstName("John");
            adminUser.setLastName("Doe");
            
            userRepository.save(adminUser);
            System.out.println("Demo user created: admin@example.com / password123");
        }
        
        // Create test users for behave tests if not exists
        if (!userRepository.existsByEmail("user@example.com")) {
            User testUser1 = new User();
            testUser1.setEmail("user@example.com");
            testUser1.setPassword(passwordEncoder.encode("valid123"));
            testUser1.setFirstName("Test");
            testUser1.setLastName("User");
            
            userRepository.save(testUser1);
            System.out.println("Test user created: user@example.com / valid123");
        }
        
        if (!userRepository.existsByEmail("test.user@domain.com")) {
            User testUser2 = new User();
            testUser2.setEmail("test.user@domain.com");
            testUser2.setPassword(passwordEncoder.encode("password1"));
            testUser2.setFirstName("Test");
            testUser2.setLastName("User");
            
            userRepository.save(testUser2);
            System.out.println("Test user created: test.user@domain.com / password1");
        }
    }
}