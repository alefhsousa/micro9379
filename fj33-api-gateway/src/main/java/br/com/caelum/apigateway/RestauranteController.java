package br.com.caelum.apigateway;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class RestauranteController {

    private DistanciaCliente distanciaCliente;
    private RestauranteCliente restauranteCliente;

    @GetMapping("/restaurantes-com-distancia/{cep}/restaurante/{id}")
    public Map<String, Object> buscaPorCep(@PathVariable("cep") String cep, @PathVariable("id") Long restauranteId) {
        Map<String, Object> dadosDistancia = distanciaCliente.buscaDistanciaPorRestauranteIdECep(cep, restauranteId);
        Map<String, Object> dadosRestaurante = restauranteCliente.buscaRestaurantePorId(restauranteId);

        dadosDistancia.putAll(dadosRestaurante);

        return dadosDistancia;

    }
}
