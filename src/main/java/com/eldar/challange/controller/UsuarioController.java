package com.eldar.challange.controller;

import com.eldar.challange.controller.api.UsuariosApi;
import com.eldar.challange.controller.dto.UsuarioDto;
import com.eldar.challange.controller.mapper.UsuarioDtoMapper;
import com.eldar.challange.service.exception.EmailNotValidException;
import com.eldar.challange.service.model.Usuario;
import com.eldar.challange.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController implements UsuariosApi {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioDtoMapper mapper;

    @Override
    public void registrarUsuario(UsuarioDto usuarioDto) {
        if (!isValidEmail(usuarioDto.email())) {
            throw new EmailNotValidException("Email inválido");
        }
        usuarioService.registrarUsuario(mapper.toUsuario(usuarioDto));
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioService.getUsuarios();
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }
}
