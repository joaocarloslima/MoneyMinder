package br.com.fiap.moneyminder.model;

import java.util.Random;

public record Categoria(Long id, String nome, String icone) {

    public Categoria(Long id, String nome, String icone){
        var key = (id != null) ? id : Math.abs( new Random().nextLong() );
        this.id = key;
        this.icone = icone;
        this.nome = nome;
    }

} 