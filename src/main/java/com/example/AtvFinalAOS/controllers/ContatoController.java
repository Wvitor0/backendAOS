package com.example.AtvFinalAOS.controllers;

import com.example.AtvFinalAOS.models.Contato;
import com.example.AtvFinalAOS.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;

    @PostMapping("/contatos")
    public Contato createContato(@RequestBody Contato newContato){
        return contatoRepository.save(newContato);
    }

    @GetMapping("/contatos")
    public List<Contato> getContatos(){
        return contatoRepository.findAll();
    }

    @GetMapping("/contatos/key={id}")
    public Contato getContatoById(@PathVariable Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Contato não encontrado com o id: " + id));
    }

    @PutMapping("/update/contato/key={id}")
    public Contato updateContato(@RequestBody Contato newContato, @PathVariable Long id){
        return contatoRepository.findById(id)
                .map(contato -> {
                    contato.setEmail(newContato.getEmail());
                    contato.setTelefone(newContato.getTelefone());
                    return contatoRepository.save(contato);
                }).orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Contato não encontrado com o id: " + id));
    }

    @DeleteMapping("/delete/contato/key={id}")
    public String deleteContato(@PathVariable Long id){
        if(!contatoRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Contato não encontrado com o id: " + id);
        }
        contatoRepository.deleteById(id);
        return "Contato com o  id " + id + " foi deletado com sucesso!";
    }
}
