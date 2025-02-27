package com.example.AgenciadeViajes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AgenciadeViajes.modelo.Usuario;
import java.util.Optional;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
