package br.com.caelum.apigateway;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@FeignClient(name = "monolito", fallback = RestauranteClientFallback.class)
public interface RestauranteCliente {


    @GetMapping("/restaurantes/{id}")
    Map<String, Object> buscaRestaurantePorId(@PathVariable("id") Long restauranteId);

}



@Component
class RestauranteClientFallback implements RestauranteCliente {

    @Override
    public Map<String, Object> buscaRestaurantePorId(Long restauranteId) {
        HashMap<String, Object> retornoFallback = new HashMap<>();
        retornoFallback.put("id", restauranteId);
        return retornoFallback;
    }
}