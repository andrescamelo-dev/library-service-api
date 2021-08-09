package com.ceiba.biblioteca.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConsultaPrestamoRespuestaDTO {
    private Integer id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private String fechaMaximaDevolucion;
}
