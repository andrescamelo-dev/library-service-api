package com.ceiba.biblioteca.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 *
 * @author Andres Camelo <br>
 * @date 16/06/2021
 */

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
@Data
@Builder
public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  @Size(min = 1, max = 10)
  private Integer id;
  @Column(name = "cedula")
  @Size(min = 1, max = 10)
  private String cedula;
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "apellido")
  private String apellido;
  @Column(name = "direccion")
  private String direccion;
  @Column(name = "celular")
  private String celular;
  @JoinColumn(name = "tipo_usuario_id", nullable = false, referencedColumnName = "id")
  @ManyToOne(optional = false)
  private TipoUsuario tipoUsuarioId;

  @Tolerate
  public Usuario() {
    super();
  }
}
