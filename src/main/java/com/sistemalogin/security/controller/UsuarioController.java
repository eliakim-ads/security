package com.sistemalogin.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sistemalogin.security.model.Usuario;
import com.sistemalogin.security.model.UsuarioRepository;


//
@Controller  
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository; // injeção automática

    // (A) Página inicial — rota pública
    @GetMapping("/")
    public String index() {
        return "index"; // → templates/index.html
    }

    // (B) Exibe o formulário de cadastro
    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        model.addAttribute("usuario", new Usuario()); // objeto vazio para o form
        return "cadastro"; // → templates/cadastro.html
    }

    // (C) Processa o formulário de cadastro
    @PostMapping("/cadastro")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario); // persiste no banco
        return "redirect:/login"; // padrão PRG — evita resubmissão
    }

    // (D) Área protegida — só usuários logados chegam aqui
    @GetMapping("/area-logada")
    public String areaLogada(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("usuarioLogado", user.getUsername()); // passa nome ao template
        return "area-logada"; // → templates/area-logada.html
    }
}
