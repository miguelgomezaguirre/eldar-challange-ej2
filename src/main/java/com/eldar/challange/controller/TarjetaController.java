package com.eldar.challange.controller;

import com.eldar.challange.controller.api.TarjetaApi;
import com.eldar.challange.controller.dto.TarjetaDto;
import com.eldar.challange.controller.dto.TasaOperacionDto;
import com.eldar.challange.controller.mapper.TarjetaDtoMapper;
import com.eldar.challange.service.TarjetaService;
import com.eldar.challange.service.UsuarioService;
import com.eldar.challange.service.model.Usuario;
import com.eldar.challange.service.model.tarjeta.Tarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TarjetaController implements TarjetaApi {

    @Autowired
    private TarjetaService tarjetaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TarjetaDtoMapper mapper;

    @Override
    public void guardarTarjeta(TarjetaDto tarjetaDto) {
        //buscamos el usuario por dni
        Usuario usuario = usuarioService.getUsuarioById(tarjetaDto.dniTitular());
        Tarjeta tarjeta = mapper.toTarjeta(tarjetaDto);
        tarjetaService.guardarTarjeta(tarjeta, usuario);
    }

    @Override
    public List<TarjetaDto> listarTarjetas() {
        return mapper.toTarjetasDto(tarjetaService.listarTarjetas());
    }

    @Override
    public TasaOperacionDto obtenerTasaDeOperacion(String marca, double importe) {
        double tasa = tarjetaService.obtenerTasaDeOperacion(marca, importe);
        return new TasaOperacionDto(marca, tasa);
    }
}
