package com.encurtador.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.encurtador.model.LinkModel;

public interface LinkRepository extends JpaRepository<LinkModel, Long> {
    Optional<LinkModel> findByCodigo(String codigo);
}