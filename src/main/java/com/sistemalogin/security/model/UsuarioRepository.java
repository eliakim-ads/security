package com.sistemalogin.security.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
// Interface de repositório para acessar os dados dos usuários
// Extende JpaRepository para fornecer métodos CRUD e consultas personalizadas
// O método FindByNome é definido para encontrar um usuário pelo nome, retornando um Optional<Usuario>
public interface UsuarioRepository 
    extends JpaRepository<Usuario, Long> {
    // Método para encontrar um usuário pelo nome
    //Query derivada: Spring cria utomaticamente o SQL abaixo.
    //SELECT * FROM usuarios WHERE nome = ?;
    //Optional: Protetor contra NullPointerException, indicando que o resultado pode ser vazio (sem usuário encontrado)
    Optional<Usuario> FindByNome(String nome); // Método para encontrar um usuário pelo nome
}
