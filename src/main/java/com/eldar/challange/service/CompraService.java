package com.eldar.challange.service;

import com.eldar.challange.repository.CompraRepository;
import com.eldar.challange.repository.TarjetaRepository;
import com.eldar.challange.repository.mapper.CompraEntityMapper;
import com.eldar.challange.service.exception.CvvIncorrectoException;
import com.eldar.challange.service.exception.TarjetaVencidaException;
import com.eldar.challange.service.model.Compra;
import com.eldar.challange.service.model.tarjeta.Tarjeta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    private static final Logger logger = LogManager.getLogger(CompraService.class);
    private static final String EMAIL_SUBJECT = "Realizaste una compra";
    private static final String EMAIL_BODY = """
            Hola %s, te informamos que realizaste una compra por un monto de $%s.
            Detalle de la compra: %s
            Muchas gracias por elegirnos.
            """;

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private CompraEntityMapper compraEntityMapper;
    @Autowired
    private TarjetaRepository tarjetaRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    private EncryptService encryptService;

    public void registrarCompra(Compra compra, Tarjeta tarjetaCompra) {
        //verificamos que la tarjeta no este vencida
        if (tarjetaCompra.getFechaVencimiento().isBefore(LocalDate.now())) {
            throw new TarjetaVencidaException("Tarjeta vencida");
        }

        //verificamos que el cvv sea correcto
        verificarCvv(tarjetaCompra, compra.getCvv());

        compra.setTarjetaCompra(tarjetaCompra);
        compra.setTitularTarjeta(tarjetaCompra.getTitularTarjeta());
        compraRepository.save(compraEntityMapper.toCompraEntity(compra));

        //enviamos un email al usuario
        emailService.sendEmail(compra.getTitularTarjeta().getEmail(),
                EMAIL_SUBJECT,
                String.format(EMAIL_BODY,
                        compra.getTitularTarjeta().getNombre(),
                        compra.getImporte(),
                        compra.getDetalle()));

    }

    private void verificarCvv(Tarjeta tarjetaCompra, String cvv) {
        try {
            if (!tarjetaCompra.getCvv().equals(encryptService.encrypt(cvv))) {
                throw new CvvIncorrectoException("CVV incorrecto");
            }
        } catch (Exception e) {
            logger.error("Error al verificar el CVV", e);
        }
    }
}
