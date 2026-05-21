import java.util.ArrayList;
import java.util.List;

public class Carrinho {


    protected double preco;
    protected int quant_itens;
    protected double desconto;
    protected double juros;
    protected String f_pagamento;
    protected List<Produto> produtos_carrinho;
    protected Cliente cliente;
    private  SimuAPI api_banco = new SimuAPI();

    public Carrinho(double p_desconto, double p_juros , String p_f_pagamento, Cliente cliente){
        this.preco = 0;
        this.quant_itens = 0;
        this.desconto = p_desconto;
        this.juros = p_juros;
        this.f_pagamento = p_f_pagamento;
        this.cliente = cliente;
        this.produtos_carrinho = new ArrayList<>();


        // verificação do construtor para juros e desconto
        if(p_juros > 0 && p_desconto > 0){
            throw new IllegalArgumentException("Não pode ter juros e desconto ao mesmo tempo");
        }
    }



    public void addProdutoCarrinho(Produto produto_add, int quantidade){
        if(produto_add == null){
            throw new IllegalArgumentException("Produto nao pode ser nulo");
        }
        produto_add.setquantidadecarrinho(quantidade);
        this.produtos_carrinho.add(produto_add);
    }

    public void removeProdutoCarrinho(Produto produto_rmv){
        if(produto_rmv == null){
            throw new IllegalArgumentException("Produto nao pode ser nulo");
        }

        for(int i=0; i < this.produtos_carrinho.size(); i++){
            Produto produto_analisado = this.produtos_carrinho.get(i);

            if(produto_analisado.getId() == produto_rmv.getId()){
                this.produtos_carrinho.remove(i);
                break;
            }
            else {
                System.out.println("Não foi encontrado o produto no carrinho");
            }
        }

    }

    public String realizarCompra(){
        Double total = 0.0;
        String saida ="";

        if(this.produtos_carrinho.isEmpty()){
            total = this.preco * this.quant_itens;
        } else {
            int total_itens = 0;

            for(Produto produto : this.produtos_carrinho){
                total += produto.getPreco() * produto.getquantidadecarrinho();
                total_itens += produto.getquantidadecarrinho();
            }

            this.quant_itens = total_itens;
        }
        // processo de desconto
        total = total * ((100 - desconto) / 100);
        // processo de juros
        total = total * (1 + this.juros / 100);
        // desconto pix, cartão e dinheiro
        if(this.f_pagamento.equals("PIX")){
            total = total * 0.95;
        } else if (this.f_pagamento.equals("Cartão")) {
            total = total * 1.05;
        } else if (this.f_pagamento.equals("Dinheiro")) {
            total = total * 0.9;
        }

        this.preco = total;
        String valorfinal = String.format("%.2f", preco);
        if (!f_pagamento.equals("Dinheiro")) {
            if (api_banco.API_Request(total, cliente) == 200) {
                saida = "Compra aprovada";
            } else if (api_banco.API_Request(total, cliente) == 404) {
                saida = "Saldo insuficiente, compra reprovada";
            }
        } else if (f_pagamento.equals("Dinheiro")) {
            saida = "Pagamento em especie";
        }

        return (saida + " total da compra : " + valorfinal);



    }

    public String mostrarCarrinho() {
    String resultado = "";
    
    for (int i = 0; i < produtos_carrinho.size(); i++) {
        Produto p = produtos_carrinho.get(i);
        
        resultado += "'Produto{" +
                     "id_prod=" + p.getId() +
                     ", nome=" + p.getNome() +
                     ", preco=" + p.getPreco() +
                     ", quant=" + p.getQuantidade() +
                     "'}";
    
    }
    
    return resultado.isEmpty() ? "Carrinho vazio" : resultado;
}


}

