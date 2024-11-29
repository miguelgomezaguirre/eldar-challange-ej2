package com.eldar.challange.controller.dto;

public record CompraDto(double importe,
                        String detalle,
                        String numeroTarjeta,
                        String cvv) {
}
