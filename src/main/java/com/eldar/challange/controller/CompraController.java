package com.eldar.challange.controller;

import com.eldar.challange.controller.api.CompraApi;
import com.eldar.challange.controller.dto.CompraDto;
import com.eldar.challange.controller.mapper.CompraDtoMapper;
import com.eldar.challange.service.CompraService;
import com.eldar.challange.service.TarjetaService;
import com.eldar.challange.service.model.tarjeta.Tarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController implements CompraApi {

    @Autowired
    private CompraService compraService;
    @Autowired
    private CompraDtoMapper mapper;
    @Autowired
    private TarjetaService tarjetaService;

    @Override
    public void registrarCompra(CompraDto compraDto) {
        //verificamos que el monto no exceda los $10.000
        if (compraDto.importe() > 10000) {
            throw new RuntimeException("El monto no puede exceder los $10.000");
        }
        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorPan(compraDto.numeroTarjeta());
        compraService.registrarCompra(mapper.toCompra(compraDto), tarjeta);
    }

}
