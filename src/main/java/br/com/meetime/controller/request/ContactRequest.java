package br.com.meetime.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    private String email;
    private String firstName;
    private String lastName;
}
