package com.example.AtvFinalAOS.repository;

import com.example.AtvFinalAOS.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
