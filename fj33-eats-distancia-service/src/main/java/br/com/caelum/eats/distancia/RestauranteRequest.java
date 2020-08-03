package br.com.caelum.eats.distancia;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteRequest {

    private Long id;

    private String cep;

    private Long tipoDeCozinhaId;


    public Restaurante paraUmRestaurante() {
        return new Restaurante(id, cep, tipoDeCozinhaId);
    }
}
