package com.example.ContactManager;

import com.example.ContactManager.entity.Contact;
import com.example.ContactManager.repository.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(ContactRepository contactRepository) {
        return args -> {
            System.out.println("\nInitializing data...\n");

            Contact contact1 = new Contact();
            contact1.setName("John Doe");
            contact1.setPhone("+37368000111");
            contact1.setEmail("john.doe@gmail.com");
            contact1.setNote("First note");

            Contact contact2 = new Contact();
            contact2.setName("Jane Doe");
            contact2.setPhone("+37368000112");
            contact2.setEmail("jane.doe@gmail.com");
            contact2.setNote("Second note");

            contactRepository.save(contact1);
            contactRepository.save(contact2);
            System.out.println("Initial students: " + contactRepository.findAll());
        };
    }
}
