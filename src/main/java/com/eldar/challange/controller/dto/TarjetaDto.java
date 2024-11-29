package com.eldar.challange.controller.dto;

import java.time.LocalDate;

public record TarjetaDto(String pan,
                         LocalDate fechaVencimiento,
                         String nombreTitular,
                         String marca,
                         String cvv,
                         Long dniTitular) {
}