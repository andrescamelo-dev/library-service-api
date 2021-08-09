package com.ceiba.biblioteca.repository;

import com.ceiba.biblioteca.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("UsuarioRepository")
public interface UsuarioRepository  extends CrudRepository<Usuario, Integer>  {

    @Query(value = "select usu from Usuario usu, TipoUsuario tu WHERE usu.tipoUsuarioId.id = tu.id AND usu.cedula = ?1  AND tu.id = ?2 ", nativeQuery = false)
    Usuario findUsuarioBynumeroCedula(String numeroCedula, int tipoUsuarioId);
    
}
