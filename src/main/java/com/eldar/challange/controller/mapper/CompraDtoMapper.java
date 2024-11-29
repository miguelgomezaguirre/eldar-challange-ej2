package com.eldar.challange.controller.mapper;

import com.eldar.challange.controller.dto.CompraDto;
import com.eldar.challange.service.model.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.ERROR)
public interface CompraDtoMapper {

    @Mapping(target = "tarjetaCompra", ignore = true)
    @Mapping(target = "titularTarjeta", ignore = true)
    Compra toCompra(CompraDto compraDto);
}
