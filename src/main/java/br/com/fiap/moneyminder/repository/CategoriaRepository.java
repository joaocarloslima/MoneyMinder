package br.com.fiap.moneyminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.moneyminder.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}
