public class Carrinho {


    protected double preco;
    protected int quant_itens;
    protected double desconto;
    protected double juros;
    protected String f_pagamento;

    public Carrinho(double p_preco, int p_quant_itens, double p_desconto, double p_juros , String p_f_pagamento){
        this.preco = p_preco;
        this.quant_itens = p_quant_itens;
        this.desconto = p_desconto;
        this.juros = p_juros;
        this.f_pagamento = p_f_pagamento;


        // verificação do construtor para juros e desconto
        if(p_juros > 0 && p_desconto > 0){
            throw new IllegalArgumentException("Não pode ter juros e desconto ao mesmo tempo");
        }
    }

    public Carrinho(double p_preco, int p_quant_itens, String p_f_pagamento){
        this.preco = p_preco;
        this.quant_itens = p_quant_itens;
        this.f_pagamento = p_f_pagamento;
    }

    public String realizarCompra(){
        //preco por itens
        this.preco = this.preco * quant_itens;
        // processo de desconto
        this.preco = ((this.preco) * (((100 - desconto))/100));
        // processo de juros
        this.preco = ((this.preco) * (1+ this.juros/100));
        // desconto pix, cartão e dinheiro
        if(this.f_pagamento.equals("PIX")){
            this.preco = (this.preco * 0.95);
        } else if (this.f_pagamento.equals("Cartão")) {
            this.preco = (this.preco * 1.05);
        } else if (this.f_pagamento.equals("Dinheiro")) {
            this.preco = (this.preco * 0.9);
        }

        String valorfinal = String.format("%.2f", preco);
        return ("total da compra : " + valorfinal);


    }

    public String mostrarCarrinho(){
        return System.out.printf("Preco:%f \nQuantidade:%d \nDesconto:%f \nJuros:%f \nForma de pagamento:%s\n", this.preco, this.quant_itens, this.desconto, this.juros, this.f_pagamento).toString();
    }


}

