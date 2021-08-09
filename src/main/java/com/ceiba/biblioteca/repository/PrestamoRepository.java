package com.ceiba.biblioteca.repository;



import java.util.Date;

import com.ceiba.biblioteca.model.Prestamo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository("PrestamoRepository")
public interface PrestamoRepository  extends CrudRepository<Prestamo, Integer>  {

    @Query(value = "select p from Prestamo p, Usuario usu WHERE p.usuarioId.id = usu.id AND p.usuarioId.cedula = ?1 AND p.fechaMaximaDevolucion >= ?2 ", nativeQuery = false)
    Prestamo findPrestamoByUsuarioIdAndFechaPrestamo(String usuarioId, Date fechaPrestamo);
    
}
