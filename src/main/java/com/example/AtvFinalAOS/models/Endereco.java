package com.example.AtvFinalAOS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
