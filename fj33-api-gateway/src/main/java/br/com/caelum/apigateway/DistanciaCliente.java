package br.com.caelum.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DistanciaCliente {

    private String distanciaServiceUrl;
    private RestTemplate restTemplate;

    DistanciaCliente(RestTemplate restTemplate,
                        @Value("${zuul.routes.distancia.url}") String distanciaServiceUrl) {
        this.distanciaServiceUrl = distanciaServiceUrl;
        this.restTemplate = restTemplate;
    }


    public Map<String, Object> buscaDistanciaPorRestauranteIdECep(String cep, Long restauranteId) {
        final String url = distanciaServiceUrl + "/restaurantes/" + cep + "/restaurante/" + restauranteId;
        return restTemplate.getForObject(url, Map.class);
    }

}
