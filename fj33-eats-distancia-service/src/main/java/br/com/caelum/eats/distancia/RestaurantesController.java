package br.com.caelum.eats.distancia;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
class RestaurantesController {

  private RestauranteRepository repo;

  @PostMapping("/restaurantes")
  ResponseEntity<Restaurante> adiciona(@RequestBody RestauranteRequest request, UriComponentsBuilder uriBuilder) {
    log.info("Insere novo restaurante: " + request);
    Restaurante salvo = repo.insert(request.paraUmRestaurante());
    UriComponents uriComponents = uriBuilder.path("/restaurantes/{id}").buildAndExpand(salvo.getId());
    URI uri = uriComponents.toUri();
    return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(salvo);
  }

  @PutMapping("/restaurantes/{id}")
  Restaurante atualiza(@PathVariable("id") Long id, @RequestBody Restaurante restaurante) {
    if (!repo.existsById(id)) {
      throw new ResourceNotFoundException();
    }
    log.info("Atualiza restaurante: " + restaurante);
    return repo.save(restaurante);
  }

}
