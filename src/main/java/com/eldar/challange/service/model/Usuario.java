package com.eldar.challange.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String email;

    public Usuario(Long dni) {
        this.dni = dni;
    }
}
