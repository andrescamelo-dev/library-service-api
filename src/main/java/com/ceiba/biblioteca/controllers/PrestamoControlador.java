package com.ceiba.biblioteca.controllers;

import com.ceiba.biblioteca.dtos.ErrorResponseDTO;
import com.ceiba.biblioteca.dtos.PrestamoRequestDTO;
import com.ceiba.biblioteca.dtos.PrestamoRespuestaDTO;
import com.ceiba.biblioteca.services.impl.PrestamoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
        RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.PUT, RequestMethod.PATCH })

@RestController
public class PrestamoControlador {

    @Autowired
    PrestamoServiceImpl prestamoService;

    @PostMapping("/prestamo")
    public ResponseEntity<Object> crearPrestamo(@RequestBody PrestamoRequestDTO prestamoDTO) {

        HttpStatus status = null;
        Object guardarPrestamo = null;

        guardarPrestamo = prestamoService.guardarPrestamo(prestamoDTO);

        if (guardarPrestamo instanceof PrestamoRespuestaDTO) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Headers", "content-type");
        responseHeaders.set("Access-Control-Allow-Methods", "POST");
        return new ResponseEntity<Object>(guardarPrestamo, responseHeaders, status);
    }


    @GetMapping("/prestamo/{id-prestamo}")
    public ResponseEntity<Object> consultaPrestamo(@PathVariable("id-prestamo") Integer idPrestamo) {
        Object prestamo = prestamoService.consultaPrestamoXIdprestamo(idPrestamo);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Headers", "content-type");
        responseHeaders.set("Access-Control-Allow-Methods", "GET");
        return new ResponseEntity<Object>(prestamo, responseHeaders, HttpStatus.OK);
    }

}
