package com.ceiba.biblioteca.services;

import com.ceiba.biblioteca.dtos.ConsultaPrestamoRespuestaDTO;
import com.ceiba.biblioteca.dtos.PrestamoRequestDTO;

public interface PrestamoService {

    public Object guardarPrestamo(PrestamoRequestDTO employeeDTO) throws Exception;

    public ConsultaPrestamoRespuestaDTO consultaPrestamoXIdprestamo(Integer idPrestamo) throws Exception;

}
