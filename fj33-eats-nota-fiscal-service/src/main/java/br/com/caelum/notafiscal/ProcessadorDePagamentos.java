package br.com.caelum.notafiscal;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import br.com.caelum.notafiscal.pedido.PedidoDto;
import br.com.caelum.notafiscal.pedido.PedidoRestClient;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
class ProcessadorDePagamentos {

	private GeradorDeNotaFiscal notaFiscal;
	private PedidoRestClient pedidos;

	@StreamListener(RabbitNotaFiscalConfig.PagamentosSink.PAGAMENTOS_CONFIRMADOS)
	void processaPagamento(PagamentoConfirmado pagamento) {
		PedidoDto pedido = pedidos.detalhaPorId(pagamento.getPedidoId());
		String nota = notaFiscal.geraNotaPara(pedido);
		System.out.println(nota); // TODO: enviar XML para SEFAZ
	}
}
