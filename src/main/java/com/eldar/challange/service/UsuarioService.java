package com.eldar.challange.service;

import com.eldar.challange.repository.mapper.UsuarioEntityMapper;
import com.eldar.challange.service.exception.UsuarioNotFoundException;
import com.eldar.challange.service.model.Usuario;
import com.eldar.challange.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioEntityMapper mapper;
    @Autowired
    private UsuarioRepository repository;

    public void registrarUsuario(Usuario usuario) {
        repository.save(mapper.toUsuarioEntity(usuario));
    }

    public List<Usuario> getUsuarios() {
        return mapper.toUsuarios(repository.findAll());
    }

    public Usuario getUsuarioById(Long id) {
        return mapper.toUsuario(repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("usuario no encontrado con dni: " + id))
        );
    }
}
