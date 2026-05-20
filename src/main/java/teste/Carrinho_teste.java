import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Carrinho_teste {
    private RepositorioProds repositorio  = new RepositorioProds();;
    
    // quando teste_buscarprodutos for acionada, os dos dados dos produtos serão alterados, logo será difrente do resultado esperado.
    @ParameterizedTest
    @CsvSource({                    
    "2, 'Produto{id_prod=2, nome=Teclado mecânico, preco=149.9, quant=3'}",
    "1, 'Produto{id_prod=1, nome=Teclado membrana, preco=19.9, quant=10'}",
    "3, 'Produto{id_prod=3, nome=Mouse gamer, preco=99.99, quant=4'}",
    "4, 'Produto{id_prod=4, nome=Monitor, preco=599.99, quant=2'}",
    "5, 'Produto não encontrado'"



    })
    public void teste_buscarprodutos(int id, String esperado) {
        String saida = "";
        try {
            Object resultado = repositorio.buscarProdID(id);
            if (resultado != null) {
                saida = resultado.toString();
            } else {
                saida = "Produto não encontrado";
            }
        } catch (Exception e) {
            saida = e.getMessage();
        }
        
        assertEquals(esperado, saida);
    }

    //quando acionado, esse metodo vai alterar os valores dos produtos, (necessario para que ao finalizar a compra os valores batam)

    @ParameterizedTest
    @CsvSource({
    "2, Teclado mecânico, 179.9, 3, 'Produto{id_prod=2, nome=Teclado mecânico, preco=179.9, quant=3'}",
    "1, Teclado membrana, 19.9, 8, 'Produto{id_prod=1, nome=Teclado membrana, preco=19.9, quant=8'}",
    "3, Mouse generico, 19.9, 15, 'Produto{id_prod=3, nome=Mouse generico, preco=19.9, quant=15'}",
    "4, Monitor, 499.99, 4, 'Produto{id_prod=4, nome=Monitor, preco=499.99, quant=4'}"
    })


    public void alterar_cadastro(int id, String nome, Double preco, int qtd, String esperado) {
        repositorio.atualizar_produto(id, nome, preco, qtd);
        String saida = "";
            try {
                Object resultado = repositorio.buscarProdID(id);
                if (resultado != null) {
                    saida = resultado.toString();
                } else {
                    saida = "Produto não encontrado";
                }
            } catch (Exception e) {
                saida = e.getMessage();
            }
            
            assertEquals(esperado, saida);

    }



    @ParameterizedTest
    @CsvSource({
    "Murilo, 22, 2000.00, 10, 0, Cartão, 2, 1, 1, 1, 'Compra aprovada total da compra : 188,81'",

    "Joao, 10, 1500.00, 0, 0, PIX, 1, 3, 2, 1, 'Compra aprovada total da compra : 56,71'",
        
    "Maria, 15, 1000.00, 0, 0, Dinheiro, 4, 1, 1, 1, 'Pagamento em especie total da compra : 467,90'",
    
    "Carlos, 30, 5000.00, 0, 15, Cartão, 2, 4, 1, 1, 'Compra aprovada total da compra : 820,97'",

    "Ana, 18, 1000.00, 0, 0, PIX, 3, 1, 2, 2, 'Compra aprovada total da compra : 75,62'",

    "Pedro, 50, 50.00, 0, 0, Cartão, 4, 2, 1, 1, 'Saldo insuficiente, compra reprovada total da compra : 713,88'",

    "Lucas, 70, 300.00, 5, 0, PIX, 1, 3, 3, 4, 'Compra aprovada total da compra : 125,72'",

    // ERRO: juros e desconto ao mesmo tempo
    "Murilo, 1, 1000.00, 10, 5, PIX, 1, 2, 1, 1, 'Não pode ter juros e desconto ao mesmo tempo'",

    // ERRO: saldo insuficiente
    "Carlos, 2, 50.00, 0, 0, Cartão, 4, 2, 1, 1, 'Saldo insuficiente, compra reprovada total da compra : 713,88'",

    // ERRO: produto inexistente/nulo
    "Joao, 3, 500.00, 0, 0, PIX, 99, 1, 1, 1, 'Produto nao pode ser nulo'"


    })

    public void realizarCompra(String nome, int id, Double saldo, Double desconto, Double jurus, String forma_pagamento, int idproduto1, int idproduto2, int qtd1, int qtd2, String esperado) {

        String saida = "";
        
        try { 

        Cliente cliente = new Cliente(nome, id, saldo);
        Carrinho carrinho = new Carrinho(desconto, jurus, forma_pagamento, cliente);

        carrinho.addProdutoCarrinho(repositorio.buscarProdID(idproduto1),qtd1);
        carrinho.addProdutoCarrinho(repositorio.buscarProdID(idproduto2),qtd2);

        saida = carrinho.realizarCompra();

        } catch (IllegalArgumentException e) {
            saida = e.getMessage();
        }
        assertEquals(esperado, saida);
    }





    }





