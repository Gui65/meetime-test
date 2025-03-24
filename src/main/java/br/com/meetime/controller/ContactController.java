package br.com.meetime.controller;

import br.com.meetime.controller.request.ContactRequest;
import br.com.meetime.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createContact(@RequestBody ContactRequest contact) {

        ResponseEntity<String> response = contactService.createContact(contact);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(201).body("Contato criado com sucesso.");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Erro ao criar o contato.");
        }
    }
}
