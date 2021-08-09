package com.ceiba.biblioteca.model;
/**
 *
 * @author Andres Camelo <br>
 * @date 16/06/2021
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "tipo_usuario")
@Data
public class TipoUsuario {
    @Id
    @Column(name = "id")
    @Size(min = 1, max = 1)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
}
