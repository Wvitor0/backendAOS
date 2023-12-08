package com.example.AtvFinalAOS.controllers;

import com.example.AtvFinalAOS.models.Categoria;

import com.example.AtvFinalAOS.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/categoria")
    public Categoria createCategoria(@RequestBody Categoria newCategoria){
        return categoriaRepository.save(newCategoria);
    }

    @GetMapping("/categoria")
    public List<Categoria> getCategoria(){
        return categoriaRepository.findAll();
    }

    @GetMapping("/categoria/key={id}")
    public Categoria getCategoriaById(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Categoria não encontrado com o id: " + id));
    }
}