package com.ceiba.biblioteca.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

/**
 *
 * @author Andres Camelo <br>
 * @date 16/06/2021
 */

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "prestamo")
@Data
@Builder
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "isbn")
    @Size(min = 1, max = 10)
    private String isbn;
    @JoinColumn(name = "usuarioId", nullable = false, referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuarioId;    
    @Column(name = "fechaMaximaDevolucion")
    private Date fechaMaximaDevolucion;
    
  @Tolerate
  public Prestamo() {
    super();
  }
}
