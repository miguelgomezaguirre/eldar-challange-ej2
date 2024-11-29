package com.eldar.challange.controller.api;

import com.eldar.challange.controller.dto.TarjetaDto;
import com.eldar.challange.controller.dto.TasaOperacionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/tarjetas")
public interface TarjetaApi {

    @PostMapping
    void guardarTarjeta(@RequestBody TarjetaDto tarjeta);

    @GetMapping
    List<TarjetaDto> listarTarjetas();

    @GetMapping("/tasas/operacion")
    TasaOperacionDto obtenerTasaDeOperacion(@RequestParam String marca,
                                            @RequestParam double importe);
}
