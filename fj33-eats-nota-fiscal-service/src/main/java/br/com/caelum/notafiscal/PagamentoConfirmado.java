package br.com.caelum.notafiscal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class PagamentoConfirmado {

	private Long pagamentoId;
	private Long pedidoId;

}
