package br.com.meetime.controller;

import br.com.meetime.controller.request.ContactRequest;
import br.com.meetime.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@Tag(name = "Contatos", description = "Gerenciamento de contatos no HubSpot")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }


    @PostMapping("/create")
    @Operation(summary = "Cria um contato", description = "Cadastra um novo contato no HubSpot.")
    public ResponseEntity<String> createContact(@RequestBody ContactRequest contact) {

        ResponseEntity<String> response = contactService.createContact(contact);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(201).body("Contato criado com sucesso.");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Erro ao criar o contato.");
        }
    }

    @GetMapping("/list")
    @Operation(summary = "Lista contatos", description = "Obtém a lista de contatos cadastrados no HubSpot.")
    public ResponseEntity<String> listContact() {
        ResponseEntity<String> response = contactService.listContacts();

        return ResponseEntity.status(response.getStatusCode())
                .body(response.getBody() != null ? response.getBody() : "Erro ao listar contatos.");
    }
}
