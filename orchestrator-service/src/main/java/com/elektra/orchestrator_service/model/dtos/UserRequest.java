package com.elektra.orchestrator_service.model.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotNull(message = "Nombre no puede ser nulo")
    @NotBlank(message = "Nombre no debe estar vacío")
    private String name;

    @NotNull(message = "Correo no puede ser nulo")
    @NotBlank(message = "Correo no puede estar vacío")
    @Email(message = "Correo debe tener un formato válido")
    private String email;

    @NotNull(message = "Imagen no puede ser nulo")
    @NotBlank(message = "Imagen no puede estar vacío")
    private String image;

    @NotNull(message = "Contraseña no puede ser nulo")
    @NotBlank(message = "Contraseña no puede estar vacío")
    private String password;
}