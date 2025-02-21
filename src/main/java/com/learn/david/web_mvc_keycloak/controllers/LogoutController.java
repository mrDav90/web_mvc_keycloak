package com.learn.david.web_mvc_keycloak.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    private final ClientRegistrationRepository clientRegistrationRepository;


    public LogoutController(ClientRegistrationRepository clientRegistrationRepository
    ) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }


    @PostMapping
    public String logout() {
        SecurityContextHolder.clearContext();
        String logoutUrl = clientRegistrationRepository.findByRegistrationId("keycloak")
                .getProviderDetails().getConfigurationMetadata().get("logout-uri").toString();

        return "redirect:" + logoutUrl + "?redirect_uri=http://localhost:8081";

    }
}