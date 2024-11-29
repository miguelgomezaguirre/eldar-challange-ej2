package com.eldar.challange.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    private double importe;
    @ManyToOne
    @JoinColumn(referencedColumnName = "pan")
    private TarjetaEntity tarjetaCompra;
    @ManyToOne
    @JoinColumn(referencedColumnName = "dni")
    private UsuarioEntity titularTarjeta;
}
