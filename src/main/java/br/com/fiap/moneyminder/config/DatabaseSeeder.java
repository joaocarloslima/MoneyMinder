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
                        Categoria.builder().id(3L).nome("lazer").icone("bus").build(),
                        Categoria.builder().id(4L).nome("alimentação").icone("apple").build()));

        movimentacaoRepository.saveAll(
                List.of(
                        Movimentacao.builder()
                                .id(1L)
                                .descricao("Luz")
                                .data(LocalDate.now())
                                .valor(new BigDecimal(100))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(3L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(2L)
                                .descricao("Cinema")
                                .data(LocalDate.now().minusDays(1))
                                .valor(new BigDecimal(80))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(3L)
                                .descricao("Mc Donalds")
                                .data(LocalDate.now())
                                .valor(new BigDecimal(90))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(4L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(4L)
                                .descricao("Aluguel")
                                .data(LocalDate.now())
                                .valor(new BigDecimal(1000))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(1L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(5L)
                                .descricao("Livros")
                                .data(LocalDate.now().minusDays(3))
                                .valor(new BigDecimal(250))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(1L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(6L)
                                .descricao("Água")
                                .data(LocalDate.now().minusWeeks(1))
                                .valor(new BigDecimal(50))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(2L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(7L)
                                .descricao("Mercado")
                                .data(LocalDate.now().minusWeeks(4))
                                .valor(new BigDecimal(400))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(3L).get())
                                .build(),
                        Movimentacao.builder()
                                .id(8L)
                                .descricao("Mensalidade")
                                .data(LocalDate.now().minusMonths(2))
                                .valor(new BigDecimal(500))
                                .tipo("SAIDA")
                                .categoria(categoriaRepository.findById(1L).get())
                                .build()
                        )
        );

    }

}
