package com.example.AtvFinalAOS.controllers;

import com.example.AtvFinalAOS.models.Contato;
import com.example.AtvFinalAOS.models.Endereco;
import com.example.AtvFinalAOS.repository.ContatoRepository;
import com.example.AtvFinalAOS.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping("/create/endereco")
    public Endereco createContato(@RequestBody Endereco newEndereco){
        return enderecoRepository.save(newEndereco);
    }

    @GetMapping("/get/endereco")
    public List<Endereco> getContatos(){
        return enderecoRepository.findAll();
    }

    @GetMapping("/get/endereco/key={id}")
    public Endereco getEnderecoById(@PathVariable Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Endereco não encontrado com o id: " + id));
    }

    @PutMapping("/update/endereco/key={id}")
    public Endereco updateEndereco(@RequestBody Endereco newEndereco, @PathVariable Long id){
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setBairro(newEndereco.getBairro());
                    endereco.setRua(newEndereco.getRua());
                    endereco.setCidade(newEndereco.getCidade());
                    endereco.setNumero(newEndereco.getNumero());
                    endereco.setComplemento(newEndereco.getComplemento());
                    return enderecoRepository.save(endereco);
                }).orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Endereco não encontrado com o id: " + id));
    }

    @DeleteMapping("/delete/endereco/key={id}")
    public String deleteEndereco(@PathVariable Long id){
        if(!enderecoRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Endereco não encontrado com o id: " + id);
        }
        enderecoRepository.deleteById(id);
        return "Endereco com o  id " + id + " foi deletado com sucesso!";
    }
}
