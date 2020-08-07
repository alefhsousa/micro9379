package br.com.caelum.notafiscal;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(RabbitNotaFiscalConfig.PagamentosSink.class)
@Configuration
public class RabbitNotaFiscalConfig {

    static interface PagamentosSink {

        String PAGAMENTOS_CONFIRMADOS = "pagamentosConfirmado";

        @Input
        SubscribableChannel pagamentosConfirmado();
    }
}
