package com.eldar.challange.repository.mapper;

import com.eldar.challange.repository.entity.CompraEntity;
import com.eldar.challange.service.model.Compra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CompraEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tarjetaCompra", ignore = true)
    @Mapping(target = "titularTarjeta", ignore = true)
    CompraEntity toCompraEntity(Compra compra);
}
