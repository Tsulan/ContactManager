package com.example.ContactManager.service;

import com.example.ContactManager.entity.Contact;
import com.example.ContactManager.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact with id " + id + " not found"));
    }

    public Contact createContact(Contact contact) {
        if (contactRepository.findByEmail(contact.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, Contact contactDetails) {

        Contact existingContact = getContactById(id);

        if (!existingContact.getEmail().equals(contactDetails.getEmail())) {
            contactRepository.findByEmail(contactDetails.getEmail()).ifPresent(contact -> {
                if (!contact.getId().equals(id)) {
                    throw new IllegalArgumentException("Email is already in use by another user");
                }
            });
        }

        existingContact.setName(contactDetails.getName());
        existingContact.setPhone(contactDetails.getPhone());
        existingContact.setEmail(contactDetails.getEmail());
        existingContact.setNote(contactDetails.getNote());

        return contactRepository.save(existingContact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}