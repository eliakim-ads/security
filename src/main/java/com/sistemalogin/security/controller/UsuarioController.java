package com.sistemalogin.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sistemalogin.security.model.UsuarioRepository;

@Controller

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Get --> pagina  Index
    // Get --> pagina  de Cadastro
    // Post --> Casdastro de Usuario 
    // Get --> pagina de Logada
}
