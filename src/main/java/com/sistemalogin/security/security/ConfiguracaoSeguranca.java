package com.sistemalogin.security.security;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.SecurityFilterChain;

import com.sistemalogin.security.model.Usuario;
import com.sistemalogin.security.model.UsuarioRepository;

@Configuration
public class ConfiguracaoSeguranca {
    // 1 - AUTENTICAÇÃO - Configuração de segurança para autenticação de usuários
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository repository) {
        return username -> {
            Usuario usuario = repository.FindByNome(username)
                    .orElseThrow(() -> new RuntimeException("Não encontrado"));
            return new User(
                    usuario.getNome(),
                    usuario.getSenha(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        };
    }

    // 2 - AUTORIZAÇÃO - Configuração de segurança para as rotas da aplicação do que
    // o usuario pode acessar (ROLE_USER, ROLE_ADMIN)
    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/cadastro", "/css/**").permitAll() // Permite acesso a estas rotas sem autenticação
                        .anyRequest().authenticated()) // Exige autenticação para todas as outras rotas
                .formLogin(login -> login
                        .loginPage("/login") // Especifica a página de login personalizada
                        .defaultSuccessUrl("/area-logada", true) // Redireciona para a área logada após login bem-sucedido
                        .permitAll()) // Permite acesso à página de login sem autenticação
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redireciona para a página inicial após logout
                        .permitAll()) // Permite logout para todos os usuários
                .build();
    }

}
