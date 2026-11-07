package com.springai.init.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springai.init.model.Operacion;

@Repository
public interface OperacionRepository extends JpaRepository<Operacion, Long> {


}