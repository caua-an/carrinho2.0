public class Main {
    public static void main(String[] args) {
        RepositorioProds repositorio = new RepositorioProds();
        Produto produtoTeste = repositorio.buscarProdID(2);

        if (produtoTeste == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        Carrinho carrinhoTeste = new Carrinho(produtoTeste.getPreco(), 3, "PIX");
        System.out.println(carrinhoTeste.realizarCompra());

        System.out.println(repositorio.listarProds());
    }
}
