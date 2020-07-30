package alefh.fj33.stranglerexample;

public class ProductRest {
    ProductDatabase productDatabase = new ProductDatabase();

    Produto getProdutosV1(int i) {
        return productDatabase.getProdutoV1(i);
    }

    Produto getProdutosV2(int i) {
        return productDatabase.getProdutoV2(i);
    }
}
