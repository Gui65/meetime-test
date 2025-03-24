package br.com.meetime.controller;

import br.com.meetime.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Autenticação no HubSpot")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/url")
    public ResponseEntity<String> getAutorizationUrl(){
        String authUrl = authService.getAuthorizationUrl();
        return ResponseEntity.ok(authUrl);
    }

    @GetMapping("/callback")
    public ResponseEntity<String> hadleOAuthCallback(@RequestParam("code") String code){
        authService.exchangeAuthorizationCode(code);
        return ResponseEntity.ok("Autenticação concluída com sucesso!");
    }
}
