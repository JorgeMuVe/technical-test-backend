package com.elektra.domain_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elektra.domain_service.exception.DomainException;
import com.elektra.domain_service.model.dtos.UserRequest;
import com.elektra.domain_service.model.dtos.UserResponse;
import com.elektra.domain_service.model.entities.User;
import com.elektra.domain_service.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse addUser(UserRequest userRequest) throws DomainException {
        try {
            User user = User.builder()
                    .name(userRequest.getName())
                    .email(userRequest.getEmail())
                    .image(userRequest.getImage())
                    .password(userRequest.getPassword())
                    .build();
            User userCreated = this.userRepository.save(user);
            if (userCreated != null) {
                return mapToUserResponse(userCreated);
            }
            throw new DomainException("Usuario no se guardo");
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    public List<UserResponse> getAllUsers() throws DomainException {
        try {
            List<User> users = this.userRepository.findAll();
            return users.stream().map(this::mapToUserResponse).toList();
        } catch (Exception e) {
            throw new DomainException(e.getMessage());
        }
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .image(user.getImage())
                .password(user.getPassword())
                .build();
    }
}
