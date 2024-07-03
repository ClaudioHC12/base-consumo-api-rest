package com.api_rest.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long idCliente;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @Email
    private String email;
    private DireccionDTO direccion;
}
