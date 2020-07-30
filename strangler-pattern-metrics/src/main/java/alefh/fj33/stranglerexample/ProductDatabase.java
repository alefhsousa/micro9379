package alefh.fj33.stranglerexample;

public class ProductDatabase {


    public Produto getProdutoV1(int id) {
        if (id == 1) {
            return new Produto(1, "Produto1");
        } else {
            return new Produto(10, "Produto10");
        }
    }


    public Produto getProdutoV2(int id) {
        try {
            int randomNumber = (int) (Math.random() * 100 + 1);

            Thread.sleep(randomNumber);

            if (id == 1) {
                return new Produto(1, "Produto1");
            } else if (id == 2) {
                return new Produto(2, "Produto2");
            } else {
                return new Produto(10, "Produto10");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new Produto(0, "Sem Produto");
        }

    }
}
