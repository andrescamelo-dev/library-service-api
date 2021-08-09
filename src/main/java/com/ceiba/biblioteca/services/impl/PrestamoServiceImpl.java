package com.ceiba.biblioteca.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ceiba.biblioteca.dtos.ConsultaPrestamoRespuestaDTO;
import com.ceiba.biblioteca.dtos.ErrorResponseDTO;

import com.ceiba.biblioteca.dtos.PrestamoRequestDTO;
import com.ceiba.biblioteca.dtos.PrestamoRespuestaDTO;
import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.model.Usuario;
import com.ceiba.biblioteca.repository.PrestamoRepository;
import com.ceiba.biblioteca.repository.UsuarioRepository;
import com.ceiba.biblioteca.services.PrestamoService;
import com.ceiba.biblioteca.utils.Utilidades;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private static final Logger LOG = LoggerFactory.getLogger(PrestamoServiceImpl.class);
    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Object guardarPrestamo(PrestamoRequestDTO prestamoDTO) {
        Prestamo prestamo = null;
        String mensaje = "";
        String fechaMaximaDevolucion = "";
        PrestamoRespuestaDTO prestamoRespuestaDto = null;
        try {

            if (!Utilidades.existeTipoUsuario(prestamoDTO.getTipoUsuario())) {
                mensaje = "Tipo de usuario no permitido en la biblioteca";
                return new ErrorResponseDTO(mensaje);
            }

            Prestamo existePrestamo = prestamoRepository
                    .findPrestamoByUsuarioIdAndFechaPrestamo(prestamoDTO.getIdentificacionUsuario(), new Date());
            if (existePrestamo != null) {
                mensaje = String.format(
                        "El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo",
                        prestamoDTO.getIdentificacionUsuario());
                return new ErrorResponseDTO(mensaje);
            }

            prestamo = convertirPrestamoDtoAPrestamoModel(prestamoDTO);
            prestamoRepository.save(prestamo);
            fechaMaximaDevolucion = Utilidades.formatDate(prestamo.getFechaMaximaDevolucion(), "dd/MM/yyyy");
            prestamoRespuestaDto = PrestamoRespuestaDTO.builder().id(prestamo.getId())
                    .fechaMaximaDevolucion(fechaMaximaDevolucion).build();
        } catch (Exception e) {
            LOG.error("Ocurrio un error creando el prestamo", e);
        }

        return prestamoRespuestaDto;
    }

    @Override
    public ConsultaPrestamoRespuestaDTO consultaPrestamoXIdprestamo(Integer idPrestamo){
        ConsultaPrestamoRespuestaDTO result = null;
        try {
            Optional<Prestamo> prestamo = prestamoRepository.findById(idPrestamo);
            if (prestamo.isPresent()) {
                result = convertirPrestamoModelAConsultaPrestamoDTO(prestamo.get());
            }
        } catch (Exception e) {
            LOG.error("Ocurrio un error consultado un prestamo", e);
        }
        return result;
    }

    private ConsultaPrestamoRespuestaDTO convertirPrestamoModelAConsultaPrestamoDTO(Prestamo prestamo)
            throws Exception {
         String  fechaMaximaDevolucion = Utilidades.formatDate(prestamo.getFechaMaximaDevolucion(), "dd/MM/yyyy");
        return ConsultaPrestamoRespuestaDTO.builder().id(prestamo.getId()).isbn(prestamo.getIsbn())
                .identificacionUsuario(prestamo.getUsuarioId().getCedula())
                .tipoUsuario(prestamo.getUsuarioId().getTipoUsuarioId().getId()).fechaMaximaDevolucion(fechaMaximaDevolucion).build();
    }

    private Prestamo convertirPrestamoDtoAPrestamoModel(PrestamoRequestDTO prestamoDTO) throws Exception {
        Usuario usuarioId = usuarioRepository.findUsuarioBynumeroCedula(prestamoDTO.getIdentificacionUsuario(),prestamoDTO.getTipoUsuario());
        Date fechaMaximaDevolucion = Utilidades.calculaFechaPrestamo(prestamoDTO.getTipoUsuario());
        Prestamo prestamo = Prestamo.builder().isbn(prestamoDTO.getIsbn()).usuarioId(usuarioId)
                .fechaMaximaDevolucion(fechaMaximaDevolucion).build();
        return prestamo;
    }
}
