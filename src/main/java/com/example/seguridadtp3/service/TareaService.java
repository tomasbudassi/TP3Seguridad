package com.example.seguridadtp3.service;

import com.example.seguridadtp3.model.Tarea;
import com.example.seguridadtp3.repository.TareaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TareaService implements ITareaService {

    TareaRepository tareaRepository;

    @Override
    @Transactional
    public Tarea agregarTarea(Tarea tarea) throws Exception {
        try {
            tareaRepository.save(tarea);
            return tarea;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Tarea> obtenerTareas() throws Exception {

        try {
            List<Tarea> tareas = tareaRepository.findAll();
            return tareas;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
