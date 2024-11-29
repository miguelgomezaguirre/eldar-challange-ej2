package com.eldar.challange.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TarjetaEntity {
    @Id
    private String pan;
    private LocalDate fechaVencimiento;
    private String nombreTitular;
    private String marca;
    private String cvv;
    @ManyToOne
    @JoinColumn(referencedColumnName = "dni")
    private UsuarioEntity titularTarjeta;

}
