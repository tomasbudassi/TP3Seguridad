package com.example.seguridadtp3.controller;

import com.example.seguridadtp3.dto.TareaDTO;
import com.example.seguridadtp3.service.ITareaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrar_tarea")
@AllArgsConstructor
public class RegistroTareaController {

    private ITareaService tareaService;

    @PostMapping()
    public ResponseEntity<TareaDTO> agregarTarea(@RequestBody TareaDTO tarea) {
        return new ResponseEntity<>(tareaService.agregarTarea(tarea), HttpStatus.CREATED);
    }
}
