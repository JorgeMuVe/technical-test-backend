package com.elektra.orchestrator_service.controllers;

import com.elektra.orchestrator_service.exception.DomainException;
import com.elektra.orchestrator_service.model.dtos.UserRequest;
import com.elektra.orchestrator_service.model.dtos.UserResponse;
import com.elektra.orchestrator_service.services.DomainService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class OrchestratorController {

    private final DomainService domainService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest userRequest) throws DomainException {
        UserResponse result = this.domainService.addUser(userRequest);
        if (result != null) {
            return new ResponseEntity<UserResponse>(result, HttpStatus.CREATED);
        } else {
            throw new DomainException("Ocurrio un error");
        }
    }
}