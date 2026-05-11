public class Main {
    public static void main(String[] args) {
        RepositorioProds repositorio = new RepositorioProds();
        Produto produtoTeste = repositorio.buscarProdID(2);
        SimuAPI api_teste = new SimuAPI();

        Produto mouse = new Produto(3,"Mouse gamer", 99.99, 4);

        if (produtoTeste == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }

        repositorio.AddProduto(mouse);

        Carrinho carrinhoTeste = new Carrinho(produtoTeste.getPreco(), 3, "PIX");
        System.out.println(carrinhoTeste.realizarCompra());

        System.out.println(repositorio.listarProds());

        repositorio.RemoveProduto(mouse);
        System.out.println(repositorio.listarProds());

    }
}
