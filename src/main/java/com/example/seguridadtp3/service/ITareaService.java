package com.example.seguridadtp3.service;

import com.example.seguridadtp3.model.Tarea;

import java.util.List;

public interface ITareaService {

    Tarea agregarTarea(Tarea tarea);
    List<Tarea> obtenerTareas() throws Exception;
}
