package com.eldar.challange.controller.api;

import com.eldar.challange.controller.dto.UsuarioDto;
import com.eldar.challange.service.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/usuarios")
public interface UsuariosApi {

    @PostMapping
    void registrarUsuario(@RequestBody UsuarioDto usuarioDto);

    @GetMapping
    List<Usuario> listarUsuarios();
}
