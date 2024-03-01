package br.com.fiap.moneyminder.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneyminder.model.Categoria;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> repository = new ArrayList<>();

    @GetMapping
    public List<Categoria> index() {
        return repository;
    }

    @PostMapping
    // @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        log.info("cadastrando categoria: {}", categoria);
        repository.add(categoria);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoria);
    }

    @GetMapping("{id}")
    public ResponseEntity<Categoria> get(@PathVariable Long id) {
        log.info("Buscar por id: {}", id);

        // for (Categoria categoria : repository ){
        // if (categoria.id().equals(id)){
        // return ResponseEntity
        // .status(HttpStatus.OK)
        // .body(categoria);
        // }
        // }

        var optionalCategoria = repository
                .stream()
                .filter(c -> c.id().equals(id))
                .findFirst();

        if (optionalCategoria.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(optionalCategoria.get());
    }

}
