package com.example.AtvFinalAOS.controllers;

import com.example.AtvFinalAOS.models.Produtos;
import com.example.AtvFinalAOS.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutosController {

    @Autowired
    private ProdutosRepository produtosRepository;

    @PostMapping("/create/produto")
    public Produtos createProduto(@RequestBody Produtos newProduto){
        return produtosRepository.save(newProduto);
    }

    @GetMapping("/get/produto")
    public List<Produtos> getProdutos(){
        return produtosRepository.findAll();
    }

    @GetMapping("/get/produto/key={id}")
    public Produtos getProdutosById(@PathVariable Long id) {
        return produtosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado com o id: " + id));
    }

    @PutMapping("/update/produto/key={id}")
    public Produtos updateProduto(@RequestBody Produtos newProduto, @PathVariable Long id){
        return produtosRepository.findById(id)
                .map(produto -> {
                    produto.setCategoria(newProduto.getCategoria());
                    produto.setDescricao(newProduto.getDescricao());
                    produto.setNome(newProduto.getNome());
                    produto.setValor(newProduto.getValor());
                    return produtosRepository.save(produto);
                }).orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto não encontrado com o id: " + id));
    }

    @DeleteMapping("/delete/produto/key={id}")
    public String deleteProduto(@PathVariable Long id){
        if(!produtosRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Produto não encontrado com o id: " + id);
        }
        produtosRepository.deleteById(id);
        return "Produto com o  id " + id + " foi deletado com sucesso!";
    }
}

