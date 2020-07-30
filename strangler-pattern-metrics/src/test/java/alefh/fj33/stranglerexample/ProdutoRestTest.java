package alefh.fj33.stranglerexample;

import com.github.rawls238.scientist4j.Experiment;
import com.github.rawls238.scientist4j.metrics.DropwizardMetricsProvider;
import io.dropwizard.metrics5.ConsoleReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProdutoRestTest {
    ProductRest productRest = new ProductRest();
    private Experiment<Produto> experiment;
    private ConsoleReporter reporter;

    @BeforeEach
    public void setup() {
        final DropwizardMetricsProvider provider = new DropwizardMetricsProvider();
        experiment = new Experiment<>("teste_novo_servico_produto", provider);

        reporter = ConsoleReporter.forRegistry(provider.getRegistry())
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }


    @Test
    void experimento_cientifico_para_definir_se_posso_ou_nao_mudar_para_um_novo_servico() {
        for (int i = 0; i < 100; i++) {
            int randomNumber = (int) (Math.random() * 5 + 1);

            Callable<Produto> oldCodePath = () -> productRest.getProdutosV1(randomNumber);
            Callable<Produto> newCodePath = () -> productRest.getProdutosV2(randomNumber);

            try {
                Produto experimentResult = experiment.runAsync(oldCodePath, newCodePath);
                assertEquals(experimentResult, productRest.getProdutosV1(randomNumber));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        reporter.report();
    }
}