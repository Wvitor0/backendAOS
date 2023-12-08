package com.example.AtvFinalAOS.controllers;

import com.example.AtvFinalAOS.models.Usuario;
import com.example.AtvFinalAOS.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/usuario")
    public Usuario createUsuario(@RequestBody Usuario newUsuario){
        return usuarioRepository.save(newUsuario);
    }

    @GetMapping("/usuario")
    public List<Usuario> getUsuario(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuario/key={id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario não encontrado com o id: " + id));
    }

    @PutMapping("/usuario/key={id}")
    public Usuario updateUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id){
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(newUsuario.getNome());
                    usuario.setLogin(newUsuario.getLogin());
                    usuario.setSenha(newUsuario.getSenha());
                    return usuarioRepository.save(usuario);
                }).orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario não encontrado com o id: " + id));
    }

    @DeleteMapping("/usuario/key={id}")
    public String deleteUsuario(@PathVariable Long id){
        if(!usuarioRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Usuario não encontrado com o id: " + id);
        }
        usuarioRepository.deleteById(id);
        return "Usuario com o  id " + id + " foi deletado com sucesso!";
    }
}
