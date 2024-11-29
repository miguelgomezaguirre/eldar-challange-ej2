package com.eldar.challange.repository.mapper;

import com.eldar.challange.repository.entity.TarjetaEntity;
import com.eldar.challange.service.model.tarjeta.MarcaTarjeta;
import com.eldar.challange.service.model.tarjeta.Tarjeta;
import com.eldar.challange.service.model.tarjeta.TarjetaFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = UsuarioEntityMapper.class)
public interface TarjetaEntityMapper {

    TarjetaEntity toTarjetaEntity(Tarjeta tarjeta);

    @Mapping(target = "marca", source = "marca", qualifiedByName = "stringToMarcaTarjeta")
    @Mapping(target = "pan", source = "pan")
    @Mapping(target = "fechaVencimiento", source = "fechaVencimiento")
    @Mapping(target = "nombreTitular", source = "nombreTitular")
    @Mapping(target = "cvv", source = "cvv")
    default Tarjeta toTarjeta(TarjetaEntity tarjetaEntity, UsuarioEntityMapper usuarioEntityMapper) {
        Tarjeta tarjeta = TarjetaFactory.createTarjeta(
                stringToMarcaTarjeta(tarjetaEntity.getMarca()),
                tarjetaEntity.getPan(),
                tarjetaEntity.getNombreTitular(),
                tarjetaEntity.getFechaVencimiento(),
                tarjetaEntity.getCvv());
        tarjeta.setTitularTarjeta(usuarioEntityMapper.toUsuario(tarjetaEntity.getTitularTarjeta()));
        return tarjeta;
    }

    @Named("stringToMarcaTarjeta")
    default MarcaTarjeta stringToMarcaTarjeta(String marca) {
        try {
            return MarcaTarjeta.valueOf(marca.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Marca de tarjeta no válida");
        }
    }

    default List<Tarjeta> toTarjetas(List<TarjetaEntity> tarjetasEntity, UsuarioEntityMapper usuarioEntityMapper) {
        List<Tarjeta> tarjetas = new ArrayList<>();
        for (TarjetaEntity tarjeta : tarjetasEntity) {
            tarjetas.add(toTarjeta(tarjeta, usuarioEntityMapper));
        }
        return tarjetas;
    }
}
