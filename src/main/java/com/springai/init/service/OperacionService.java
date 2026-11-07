package com.springai.init.service;


import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springai.init.model.Operacion;
import com.springai.init.repository.OperacionRepository;

@Service
public class OperacionService {

    private final OperacionRepository operacionRepository;

    public OperacionService(OperacionRepository operacionRepository) {
        this.operacionRepository = operacionRepository;
    }

    @Transactional
    public Operacion registrarOperacion(Operacion operacion) {

        return operacionRepository.save(operacion);
    }
}