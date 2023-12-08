package com.example.AtvFinalAOS.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Endereco> endereco = new ArrayList<>();;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contato_id", unique = true)
    private Contato contato;

    @OneToMany(mappedBy = "usuario")
    private List<Pedidos> pedidos = new ArrayList<>();;
}
