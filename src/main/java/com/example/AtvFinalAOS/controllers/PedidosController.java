package com.example.AtvFinalAOS.controllers;

import com.example.AtvFinalAOS.models.Pedidos;
import com.example.AtvFinalAOS.models.Produtos;
import com.example.AtvFinalAOS.repository.PedidosRepository;
import com.example.AtvFinalAOS.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PedidosController {

    @Autowired
    private PedidosRepository pedidosRepository;

    @PostMapping("/pedido")
    public Pedidos createPedido(@RequestBody Pedidos newPedido){
        return pedidosRepository.save(newPedido);
    }

    @GetMapping("/pedido")
    public List<Pedidos> getPedidos(){
        return pedidosRepository.findAll();
    }

    @GetMapping("/pedido/key={id}")
    public Pedidos getPedidosById(@PathVariable Long id) {
        return pedidosRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido não encontrado com o id: " + id));
    }

    @PutMapping("/pedido/key={id}")
    public Pedidos updatePedido(@RequestBody Pedidos newPedido, @PathVariable Long id){
        return pedidosRepository.findById(id)
                .map(pedido -> {
                    pedido.setQuantidade(newPedido.getQuantidade());
                    pedido.setTotal(newPedido.getTotal());
                    return pedidosRepository.save(pedido);
                }).orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido não encontrado com o id: " + id));
    }

    @DeleteMapping("/pedido/key={id}")
    public String deletePedido(@PathVariable Long id){
        if(!pedidosRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Pedido não encontrado com o id: " + id);
        }
        pedidosRepository.deleteById(id);
        return "Pedido com o  id " + id + " foi deletado com sucesso!";
    }
}