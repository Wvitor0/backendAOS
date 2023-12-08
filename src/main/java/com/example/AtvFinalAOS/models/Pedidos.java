package com.example.AtvFinalAOS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;
    private Double total;

    @OneToMany(mappedBy = "pedidos")
    private List<Produtos> produtos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
