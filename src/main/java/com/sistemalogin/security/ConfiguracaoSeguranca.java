package com.sistemalogin.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sistemalogin.security.model.Usuario;
import com.sistemalogin.security.model.UsuarioRepository;

@Configuration
public class ConfiguracaoSeguranca {
    // Configuração de segurança para autenticação de usuários
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository repository) {
        return username -> {
            Usuario usuario = repository.FindByNome(username)
                    .orElseThrow(() -> new RuntimeException("Não encontrado"));
            return new User(
                    usuario.getNome(),
                    usuario.getSenha(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            );
        };
    }
    
}
