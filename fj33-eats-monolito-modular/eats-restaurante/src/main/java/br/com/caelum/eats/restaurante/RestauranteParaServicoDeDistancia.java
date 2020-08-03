package br.com.caelum.eats.restaurante;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class RestauranteParaServicoDeDistancia {

  private Long id;
  private String cep;
  private Long tipoDeCozinhaId;

  RestauranteParaServicoDeDistancia(Restaurante restaurante){
    this(restaurante.getId(), restaurante.getCep(), restaurante.getTipoDeCozinha().getId());
  }

}