package br.com.fiap.moneyminder.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.moneyminder.model.Categoria;
import br.com.fiap.moneyminder.model.Movimentacao;
import br.com.fiap.moneyminder.repository.CategoriaRepository;
import br.com.fiap.moneyminder.repository.MovimentacaoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Override
    public void run(String... args) throws Exception {

        categoriaRepository.saveAll(
                List.of(
                        Categoria.builder().id(1L).nome("educação").icone("book").build(),
                        Categoria.builder().id(2L).nome("transporte").icone("bus").build(),
                        Categoria.builder().id(3L).nome("alimentação").icone("apple").build()));

        movimentacaoRepository.saveAll(
                List.of(
                        Movimentacao.builder()
                                .id(1L)
                                .descricao("Luz")
                                .data(LocalDate.now())
                                .valor(new BigDecimal(100))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build())
        );

    }

}
