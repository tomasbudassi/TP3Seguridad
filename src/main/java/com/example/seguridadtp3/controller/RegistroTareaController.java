package com.example.seguridadtp3.controller;

import com.example.seguridadtp3.model.Tarea;
import com.example.seguridadtp3.service.ITareaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@AllArgsConstructor
public class RegistroTareaController {

    private ITareaService tareaService;

    @PostMapping("/registrar_tarea")
    public ResponseEntity<Tarea> agregarTarea(@RequestBody Tarea tarea) {
        return new ResponseEntity<>(tareaService.agregarTarea(tarea), HttpStatus.CREATED);
    }

    @GetMapping("/obtener_tareas")
    public ResponseEntity<List<Tarea>> obtenerTareas() throws Exception {
        return new ResponseEntity<>(tareaService.obtenerTareas(), HttpStatus.OK);
    }
}
