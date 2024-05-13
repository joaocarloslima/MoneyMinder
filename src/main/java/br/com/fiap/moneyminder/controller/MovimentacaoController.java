package br.com.fiap.moneyminder.controller;



import static org.springframework.http.HttpStatus.CREATED;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneyminder.model.Movimentacao;
import br.com.fiap.moneyminder.repository.MovimentacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("movimentacao")
@Slf4j
public class MovimentacaoController {

    record TotalPorCategoria (String categoria, BigDecimal valor){} 

    @Autowired
    MovimentacaoRepository repository;

    @Autowired
    PagedResourcesAssembler<Movimentacao> pageAssembler;

    @GetMapping
    public PagedModel<EntityModel<Movimentacao>> index(
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) Integer mes,
        @ParameterObject @PageableDefault(sort = "data", direction = Direction.DESC) Pageable pageable

    ){

        Page<Movimentacao> page = null;

        if (mes != null && categoria != null){
            page = repository.findByCategoriaNomeAndMes(categoria, mes, pageable);
        }

        if (mes != null){
            page = repository.findByMes(mes, pageable);
        }

        if (categoria != null){
            page = repository.findByCategoriaNomeIgnoreCase(categoria, pageable);
        }
       
        if(page == null){
            page = repository.findAll(pageable);
        }

        return pageAssembler.toModel(page, Movimentacao::toEntityModel);

    }

    @GetMapping("maior")
    public Movimentacao getMaior(@PageableDefault(size = 1, sort = "valor", direction = Direction.DESC) Pageable pageable){
        return repository.findAll(pageable).getContent().get(0);
    }

    @GetMapping("ultima")
    public Movimentacao getUltima(){
        var pageable = PageRequest.of(0, 1, Direction.DESC, "data");
        return repository.findAll(pageable).getContent().get(0);
    }

    @GetMapping("menor")
    public Movimentacao getMenor(){
        return repository.findFirstByOrderByValor();
    }

    @GetMapping("total-por-categoria")
    public List<TotalPorCategoria> getTotalPorCategoria(){
        var movimentacoes = repository.findAll();

        Map<String, BigDecimal> collect = movimentacoes.stream()
            .collect(
                Collectors.groupingBy(
                    m -> m.getCategoria().getNome(),
                    Collectors.reducing( BigDecimal.ZERO, Movimentacao::getValor, BigDecimal::add)
                )
            );

        return collect
            .entrySet()
            .stream()
            .map(e -> new TotalPorCategoria(e.getKey(), e.getValue()) )
            .toList();

   }


    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Movimentacao> create(@RequestBody @Valid Movimentacao movimentacao){
        repository.save(movimentacao);

        return ResponseEntity.created(
                    movimentacao.toEntityModel().getLink("self").get().toUri()
                ).body(movimentacao);
    }

    @GetMapping("{id}")
    public EntityModel<Movimentacao> get(@PathVariable Long id){
        var movimentacao = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("movimentação não encontrada")
        );

        return movimentacao.toEntityModel();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("movimentação não encontrada")
        );

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    
}
