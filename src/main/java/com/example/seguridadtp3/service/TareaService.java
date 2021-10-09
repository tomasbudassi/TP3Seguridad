package com.example.seguridadtp3.service;

import com.example.seguridadtp3.dto.TareaDTO;
import com.example.seguridadtp3.model.Tarea;
import com.example.seguridadtp3.repository.TareaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TareaService implements ITareaService {

    TareaRepository tareaRepository;

    @Override
    @Transactional
    public TareaDTO agregarTarea(TareaDTO tareaDTO) {

        Tarea tarea = new Tarea(tareaDTO.getTitulo(), tareaDTO.getDescripcion());
        tareaRepository.save(tarea);
        return tareaDTO;
    }
}
