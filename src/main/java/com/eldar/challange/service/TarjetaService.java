package com.eldar.challange.service;

import com.eldar.challange.repository.TarjetaRepository;
import com.eldar.challange.repository.mapper.TarjetaEntityMapper;
import com.eldar.challange.repository.mapper.UsuarioEntityMapper;
import com.eldar.challange.service.exception.MarcaNoValidaException;
import com.eldar.challange.service.exception.TarjetaNotFoundException;
import com.eldar.challange.service.model.Usuario;
import com.eldar.challange.service.model.tarjeta.MarcaTarjeta;
import com.eldar.challange.service.model.tarjeta.Tarjeta;
import com.eldar.challange.service.model.tarjeta.TarjetaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

@Service
public class TarjetaService {

    private static final Logger logger = LogManager.getLogger(TarjetaService.class);
    private static final String EMAIL_SUBJECT = "Tienes una nueva tarjeta";
    private static final String EMAIL_BODY = """
            Tu tarjeta ha sido creada con éxito.
            A continuación te mostramos los datos de tu tarjeta:
            Número de tarjeta: %s
            CVV: %s
            Gracias por confiar en nosotros.
            """;

    @Autowired
    private TarjetaRepository tarjetaRepository;
    @Autowired
    private TarjetaEntityMapper mapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EncryptService encryptService;
    @Autowired
    private UsuarioEntityMapper usuarioEntityMapper;

    public void guardarTarjeta(Tarjeta tarjeta, Usuario titularTarjeta) {
        // seteamos el titular para tener la relacion
        tarjeta.setTitularTarjeta(titularTarjeta);

        // guardamos los datos limpios para en caso de ser exitoso el guardado
        // enviamos el mail al usuario con estos datos limpios
        String cleanPan = tarjeta.getPan();
        String cleanCvv = tarjeta.getCvv();

        // guardamos los datos encriptados en la bd
        encriptarDatosSensibles(tarjeta);

        // guardamos la tarjeta con en la db con los datos encriptados
        tarjetaRepository.save(mapper.toTarjetaEntity(tarjeta));

        //enviamos el mail
        emailService.sendEmail(titularTarjeta.getEmail(), EMAIL_SUBJECT, EMAIL_BODY.formatted(cleanPan, cleanCvv));
    }

    public List<Tarjeta> listarTarjetas() {
        return mapper.toTarjetas(tarjetaRepository.findAll(), usuarioEntityMapper);
    }

    public double obtenerTasaDeOperacion(String marca, double importe) {
        try {
            Tarjeta tarjeta = TarjetaFactory.createTarjeta(MarcaTarjeta.valueOf(marca));
            double tasaDeServicio = tarjeta.calcularTasaDeServicio(LocalDate.now());

            if (tasaDeServicio <= 0.3) {
                tasaDeServicio = 0.3;
            } else if (tasaDeServicio >= 5) {
                tasaDeServicio = 5;
            }

            return (tasaDeServicio*importe) / 100;
        } catch (IllegalArgumentException e) {
            throw new MarcaNoValidaException("La marca de la tarjeta no es válida");
        }
    }

    public Tarjeta obtenerTarjetaPorPan(String pan) {
        //encriptamos el pan de la tarjeta para buscarla
        String panEncrypted = null;
        try {
            panEncrypted = encryptService.encrypt(pan);
        } catch (Exception e) {
            logger.error("Error al encriptar el PAN", e);
        }

        return mapper.toTarjeta(tarjetaRepository.findById(panEncrypted)
                .orElseThrow(() -> new TarjetaNotFoundException("Tarjeta no encontrada")), usuarioEntityMapper);
    }

    private void encriptarDatosSensibles(Tarjeta tarjeta) {
        String panEncrypted = "";
        String cvvEncrypted = "";
        try {
            panEncrypted = encryptService.encrypt(tarjeta.getPan());
            cvvEncrypted = encryptService.encrypt(tarjeta.getCvv());
        } catch (Exception e) {
            logger.error("Error al encriptar el datos sensibles", e);
        }
        tarjeta.setPan(panEncrypted);
        tarjeta.setCvv(cvvEncrypted);
    }
}
