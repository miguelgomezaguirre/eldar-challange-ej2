package com.eldar.challange.controller.mapper;

import com.eldar.challange.controller.dto.TarjetaDto;
import com.eldar.challange.service.model.tarjeta.MarcaTarjeta;
import com.eldar.challange.service.model.tarjeta.Tarjeta;
import com.eldar.challange.service.model.tarjeta.TarjetaFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TarjetaDtoMapper {

    @Mapping(target = "dniTitular", source = "tarjeta.titularTarjeta.dni")
    TarjetaDto toTarjetaDto(Tarjeta tarjeta);

    @Mapping(target = "marca", source = "marca", qualifiedByName = "stringToMarcaTarjeta")
    @Mapping(target = "pan", source = "pan")
    @Mapping(target = "fechaVencimiento", source = "fechaVencimiento")
    @Mapping(target = "nombreTitular", source = "nombreTitular")
    @Mapping(target = "cvv", source = "cvv")
    @Mapping(target = "dniTitular", ignore = true)
    default Tarjeta toTarjeta(TarjetaDto tarjetaDto) {
        return TarjetaFactory.createTarjeta(
                stringToMarcaTarjeta(tarjetaDto.marca()),
                tarjetaDto.pan(),
                tarjetaDto.nombreTitular(),
                tarjetaDto.fechaVencimiento(),
                tarjetaDto.cvv()
        );
    }

    @Named("stringToMarcaTarjeta")
    default MarcaTarjeta stringToMarcaTarjeta(String marca) {
        try {
            return MarcaTarjeta.valueOf(marca.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Marca de tarjeta no válida");
        }
    }

    List<TarjetaDto> toTarjetasDto(List<Tarjeta> tarjetas);

}
