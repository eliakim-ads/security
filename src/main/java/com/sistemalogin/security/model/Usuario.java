package com.sistemalogin.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Gera getters, setters, equals, hashCode e toString
@Entity // Marca a classe como entidade JPA
@NoArgsConstructor // Gera um construtor sem argumentos
@Table(name = "usuarios") // Especifica o nome da tabela no banco de dados
public class Usuario {

    @Id // Marca o campo como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Gera o valor automaticamente
    private Long id;
    
    @Column(nullable = false, unique = true) // Especifica que o campo é único e não pode ser nulo
    private String nome;
   
    @Column(nullable = false) // Especifica que o campo não pode ser nulo
    private String senha;

    @Column(nullable = false) // Especifica que o campo não pode ser nulo
    private String role;

    
}
