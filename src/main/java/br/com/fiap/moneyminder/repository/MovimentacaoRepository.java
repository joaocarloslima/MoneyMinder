package br.com.fiap.moneyminder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.moneyminder.model.Movimentacao;

public interface MovimentacaoRepository 
            extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByCategoriaNome(String categoria);
    
}
