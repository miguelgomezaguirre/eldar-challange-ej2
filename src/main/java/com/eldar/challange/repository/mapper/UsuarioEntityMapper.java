package com.eldar.challange.repository.mapper;

import com.eldar.challange.service.model.Usuario;
import com.eldar.challange.repository.entity.UsuarioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {
    UsuarioEntity toUsuarioEntity(Usuario usuario);
    Usuario toUsuario(UsuarioEntity usuarioEntity);
    List<Usuario> toUsuarios(List<UsuarioEntity> usuariosDto);
}
