package com.elektra.orchestrator_service.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.elektra.orchestrator_service.exception.DomainException;
import com.elektra.orchestrator_service.model.dtos.UserRequest;
import com.elektra.orchestrator_service.model.dtos.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainService {

    private final WebClient.Builder webClientBluider;

    public UserResponse addUser(UserRequest userRequest) throws DomainException {
        try {
            // SEND MS DOMAIN
            UserResponse response = this.webClientBluider.build()
                    .post()
                    .uri("http://localhost:8082/api/user")
                    .bodyValue(userRequest)
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .block();
            if (response != null) {
                return response;
            } else {
                throw new DomainException("Usuario no se guardo");
            }
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }
}
