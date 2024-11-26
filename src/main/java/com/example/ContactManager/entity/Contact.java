package com.example.ContactManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "\\+373\\d{8}",
            message = "Phone number must start with +373 and be followed by 8 digits"
    )
    @Column(nullable = false)
    private String phone;

    @Email(message = "Email must be a valid email address")
    @NotBlank(message = "Email cannot be blank")
    @Column(unique = true, nullable = false)
    private String email;

    @Column()
    private String note;

    public Contact() {
    }

    public Contact(String name, String phone, String email, String note) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }
}
