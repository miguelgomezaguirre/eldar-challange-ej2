package com.eldar.challange.controller.mapper;

import com.eldar.challange.controller.dto.UsuarioDto;
import com.eldar.challange.service.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsuarioDtoMapper {

    Usuario toUsuario(UsuarioDto usuarioDto);

}
