package com.eldar.challange.controller.api;

import com.eldar.challange.controller.dto.CompraDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/compras")
public interface CompraApi {

    @PostMapping
    void registrarCompra(@RequestBody CompraDto compraDto);
}
