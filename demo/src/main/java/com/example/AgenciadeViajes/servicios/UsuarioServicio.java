package com.example.AgenciadeViajes.servicios;

import lombok.RequiredArgsConstructor;
import com.example.AgenciadeViajes.modelo.Usuario;
import com.example.AgenciadeViajes.repositorio.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;

    public String autenticarUsuario(String nombreUsuario, String contraseña) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByNombreUsuario(nombreUsuario);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.isCuentaBloqueada()) {
                return "Cuenta bloqueada. Intente más tarde.";
            }
            if (passwordEncoder.matches(contraseña, usuario.getContraseña())) {
                usuario.setIntentosFallidos(0);
                usuarioRepositorio.save(usuario);
                return "Autenticación exitosa";
            } else {
                usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
                if (usuario.getIntentosFallidos() >= 3) {
                    usuario.setCuentaBloqueada(true);
                }
                usuarioRepositorio.save(usuario);
                return "Usuario o contraseña incorrectos.";
            }
        }
        return "Usuario no encontrado.";
    }
}

