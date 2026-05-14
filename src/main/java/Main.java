public class Main {
    public static void main(String[] args) {
        // cria-se os produtos
        Produto mouse = new Produto(3,"Mouse gamer", 99.99, 4);
        Produto monitor = new Produto(4, "Monitor", 599.99, 2);
        // cadastro dos produtos no repositorio
        RepositorioProds repositorio = new RepositorioProds();
        repositorio.AddProduto(mouse);
        repositorio.AddProduto(monitor);
        System.out.println(repositorio.listarProds());
        // abre o carrinho
        Carrinho carrinho_atual = new Carrinho(0, 10, "Cartão");
        carrinho_atual.addProdutoCarrinho(monitor);
        carrinho_atual.addProdutoCarrinho(mouse);
        carrinho_atual.removeProdutoCarrinho(monitor);
        // finaliza a compra do carrinho
        System.out.println(carrinho_atual.realizarCompra());
        carrinho_atual.mostrarCarrinho();
        // retira o produto do estoque depois da venda
        repositorio.RemoveProduto(monitor);
        repositorio.RemoveProduto(mouse);
        System.out.println(repositorio.listarProds());




    }
}
