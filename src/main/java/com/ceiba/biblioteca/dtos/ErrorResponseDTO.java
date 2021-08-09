package com.ceiba.biblioteca.dtos;

import lombok.Data;


@Data
public class ErrorResponseDTO {
	  private String mensaje;


	  public ErrorResponseDTO(String mensaje){
		  this.mensaje = mensaje;
	  }
}
