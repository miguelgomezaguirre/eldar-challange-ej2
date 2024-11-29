package com.eldar.challange.service;

import com.eldar.challange.repository.UsuarioRepository;
import com.eldar.challange.repository.entity.UsuarioEntity;
import com.eldar.challange.repository.mapper.UsuarioEntityMapper;
import com.eldar.challange.service.exception.UsuarioNotFoundException;
import com.eldar.challange.service.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock private UsuarioEntityMapper mapper;
    @Mock private UsuarioRepository repository;
    @InjectMocks private UsuarioService usuarioService;

    @Mock private Usuario usuario;
    @Mock private UsuarioEntity usuarioEntity;

    @Test
    void whenGetUsuarioByIdThenReturnUsuario() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(usuarioEntity));
        when(mapper.toUsuario(any())).thenReturn(usuario);
        Usuario result = usuarioService.getUsuarioById(1L);
        assertEquals(Usuario.class, result.getClass());
    }

    @Test
    void whenGetUsuarioByIdThenThrowException() {
        when(repository.findById(anyLong())).thenThrow(new UsuarioNotFoundException("Usuario no encontrado"));
        assertThrows(UsuarioNotFoundException.class, () -> usuarioService.getUsuarioById(1L));
    }
}