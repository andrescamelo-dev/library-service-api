package com.ceiba.biblioteca.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class PrestamoRespuestaDTO {
    private Integer id;
    private String fechaMaximaDevolucion;

    @Tolerate
    public PrestamoRespuestaDTO() {
      super();
    }
}
