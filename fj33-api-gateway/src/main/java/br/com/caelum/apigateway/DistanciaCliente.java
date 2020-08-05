package br.com.caelum.apigateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class DistanciaCliente {

    private String distanciaServiceUrl;
    private RestTemplate restTemplate;
    private CacheManager cacheManager;

    DistanciaCliente(RestTemplate restTemplate,
                        @Value("${configuracao.distancia.service.url}") String distanciaServiceUrl) {
        this.distanciaServiceUrl = distanciaServiceUrl;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "buscaDistanciaPorRestauranteIdECepFallback")
    public Map<String, Object> buscaDistanciaPorRestauranteIdECep(String cep, Long restauranteId) {
        final String url = distanciaServiceUrl + "/restaurantes/" + cep + "/restaurante/" + restauranteId;
        return restTemplate.getForObject(url, Map.class);
    }


    public Map<String, Object> buscaDistanciaPorRestauranteIdECepFallback(String cep, Long restauranteId) {
        log.info("chamando metodo de fallback");
        HashMap<String, Object> retornoFallback = new HashMap<>();

        retornoFallback.put("restauranteId", restauranteId);
        retornoFallback.put("distancia", BigDecimal.ZERO);
        return retornoFallback;
    }

}
