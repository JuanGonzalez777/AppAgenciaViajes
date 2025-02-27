package com.example.AgenciadeViajes.controlador;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.AgenciadeViajes.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api/autenticacion")
@RequiredArgsConstructor
public class RegistroControlador {
    private final UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public String login(@RequestParam String nombreUsuario, @RequestParam String contraseña) {
        return usuarioServicio.autenticarUsuario(nombreUsuario, contraseña);
    }
}
