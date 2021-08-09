package com.ceiba.biblioteca.dtos;

import lombok.Data;


@Data
public class PrestamoRequestDTO {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
}
