package br.com.fiap.moneyminder.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.moneyminder.model.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    Page<Movimentacao> findByCategoriaNomeIgnoreCase(String categoria, Pageable pageable);

    //JPQL - Java Persistence Query Language
    @Query("SELECT m FROM Movimentacao m WHERE MONTH(m.data) = :mes")
    Page<Movimentacao> findByMes(@Param("mes") Integer mes, Pageable pageable);

    @Query("SELECT m FROM Movimentacao m WHERE m.categoria.nome = :categoria AND MONTH(m.data) = :mes")
    Page<Movimentacao> findByCategoriaNomeAndMes(@Param("categoria") String categoria, @Param("mes") Integer mes, Pageable pageable);

    // @Query("SELECT m FROM Movimentacao m ORDER BY m.id LIMIT ?2 OFFSET ?1")
    // List<Movimentacao> findAllPageable(int offset, int size);
    
}
