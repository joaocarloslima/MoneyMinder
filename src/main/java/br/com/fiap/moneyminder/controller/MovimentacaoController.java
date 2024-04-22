package br.com.fiap.moneyminder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
    public Page<Movimentacao> index(
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) Integer mes,
        @PageableDefault(sort = "data", direction = Direction.DESC) Pageable pageable

    ){

        if (mes != null && categoria != null){
            return repository.findByCategoriaNomeAndMes(categoria, mes, pageable);
        }

        if (mes != null){
            return repository.findByMes(mes, pageable);
        }

        if (categoria != null){
            return repository.findByCategoriaNomeIgnoreCase(categoria, pageable);
        }
       
        return repository.findAll(pageable);
    }


    @PostMapping
    public Movimentacao create(@RequestBody @Valid Movimentacao movimentacao){
        return repository.save(movimentacao);
    }
    
}
