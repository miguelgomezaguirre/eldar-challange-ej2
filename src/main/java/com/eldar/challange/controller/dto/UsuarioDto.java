package com.eldar.challange.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record UsuarioDto(Long dni,
                         String nombre,
                         String apellido,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
                         LocalDate fechaNacimiento,
                         @Email
                         String email) {
}
