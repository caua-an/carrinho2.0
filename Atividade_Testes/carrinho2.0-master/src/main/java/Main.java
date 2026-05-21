public class Main {
    public static void main(String[] args) {
        // cria-se os produtos
        Produto mouse = new Produto(3,"Mouse gamer", 99.99, 4);
        Produto monitor = new Produto(4, "Monitor", 599.99, 2);
    
        // cadastro dos produtos no repositorio
        RepositorioProds repositorio = new RepositorioProds();
        repositorio.AddProduto(mouse);
        repositorio.AddProduto(monitor);
        Cliente murilo = new Cliente("Murilo", 22, 1550.00);
        //System.out.println(repositorio.listarProds());
        // abre o carrinho
        Carrinho carrinho_atual = new Carrinho(0, 10, "Cartão", murilo);
        carrinho_atual.addProdutoCarrinho(repositorio.buscarProdID(2),2);
    
        
        

        // finaliza a compra do carrinho
        //System.out.println(carrinho_atual.realizarCompra());
        //carrinho_atual.mostrarCarrinho();
        // retira o produto do estoque depois da venda
        //System.out.println(repositorio.listarProds());

        //repositorio.listarProds();
        



    }
}
