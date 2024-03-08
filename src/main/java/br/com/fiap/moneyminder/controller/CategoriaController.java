package br.com.fiap.moneyminder.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.moneyminder.model.Categoria;
import br.com.fiap.moneyminder.repository.CategoriaRepository;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    Logger log = LoggerFactory.getLogger(getClass());

    List<Categoria> repository = new ArrayList<>();

    @Autowired // CDI // TODO explicar
    CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> index() {
        return categoriaRepository.findAll();
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

    // @GetMapping("{id}")
    // public ResponseEntity<Categoria> get(@PathVariable Long id) {
    //     log.info("Buscar por id: {}", id);

    //     var optionalCategoria = buscarCategoriaPorId(id);

    //     if (optionalCategoria.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     return ResponseEntity.ok(optionalCategoria.get());
    // }



    // @DeleteMapping("{id}")
    // public ResponseEntity<Object> destroy(@PathVariable Long id) {
    //     log.info("apagando categoria {}", id);

    //     var optionalCategoria = buscarCategoriaPorId(id);

    //     if (optionalCategoria.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     repository.remove(optionalCategoria.get());

    //     return ResponseEntity.noContent().build();

    // }

    // @PutMapping("{id}")
    // public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Categoria categoria){
    //     log.info("atualizando categoria id {} para {}", id, categoria);
        
    //     var optionalCategoria = buscarCategoriaPorId(id);

    //     if (optionalCategoria.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     var categoriaEncontrada = optionalCategoria.get();
    //     var categoriaAtualizada = new Categoria(id, categoria.nome(), categoria.icone());
    //     repository.remove(categoriaEncontrada);
    //     repository.add(categoriaAtualizada);

    //     return ResponseEntity.ok().body(categoriaAtualizada);
    // }

    // private Optional<Categoria> buscarCategoriaPorId(Long id) {
    //     var optionalCategoria = repository
    //             .stream()
    //             .filter(c -> c.id().equals(id))
    //             .findFirst();
    //     return optionalCategoria;
    // }

}
