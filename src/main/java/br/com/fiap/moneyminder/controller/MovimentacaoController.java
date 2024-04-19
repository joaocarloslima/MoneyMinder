package br.com.fiap.moneyminder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneyminder.model.Movimentacao;
import br.com.fiap.moneyminder.repository.MovimentacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("movimentacao")
@Slf4j
public class MovimentacaoController {

    @Autowired
    MovimentacaoRepository repository;

    @GetMapping
    public List<Movimentacao> index(@RequestParam(required = false) String categoria){
        log.info("buscar :" + categoria);
        if (categoria != null){
            return repository.findByCategoriaNome(categoria);
        }
        return repository.findAll();
    }


    @PostMapping
    public Movimentacao create(@RequestBody @Valid Movimentacao movimentacao){
        return repository.save(movimentacao);
    }
    
}
