package com.example.seguridadtp3.controller;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class ViewController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/tareas")
    public String obtenerTareas() {
        return "tareas";
    }

    @GetMapping(value = "/registro")
    public String registrarTarea() {
        return "registro";
    }
}
