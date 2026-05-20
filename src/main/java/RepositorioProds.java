import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

// Classe para realizar acesso e tratamento do JSON.
public class RepositorioProds {
    // pegar path relativo do dados/produtos.json
   private static final String PATH = buscarCaminhoArquivo();

    private static String buscarCaminhoArquivo() {
    // Tenta o caminho direto (raiz)
    File raiz = new File("dados/produtos.json");
    if (raiz.exists()) return raiz.getPath();
    
    // Tenta dentro da pasta do projeto (comum no VS Code)
    File subpasta = new File("carrinho2.0-master/dados/produtos.json");
    if (subpasta.exists()) return subpasta.getPath();

    // Se nada funcionar, retorna o padrão para evitar erro de compilação
    return "dados/produtos.json";
}
    // instanciar o mapper para ler o .json
    private final ObjectMapper mapper = new ObjectMapper();

    private final SimuAPI api_repo = new SimuAPI();

    public List<Produto> listarProds() {
        try {
            File arquivo = new File(PATH);
            // caso não exista o arquivo produtos.json
            if (!arquivo.exists()) {
                return new ArrayList<>();
            }

            return mapper.readValue(arquivo, new TypeReference<List<Produto>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu algum erro ao ler produtos.json", e);
        }
    }

    public void mostrarprodutos() {

        try {
            File arquivo = new File(PATH);

            List <Produto> produtos = mapper.readValue(arquivo, new TypeReference<List<Produto>>() {});
                for (Produto produto : produtos) {
                    System.out.println(produto);
            }
        } catch (Exception e) {
            throw new RuntimeException("erro ao mostrar produto", e);
        }
    }

    public String AddProduto(Produto produto_add){
        try {

            List<Produto> produtoList = listarProds();
            // caso o produto não exista
            if(buscarProdID(produto_add.getId()) == null){
                // adiciona o produto na lista
                produtoList.add(produto_add);
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), produtoList);
                return "Produto adicionado com sucesso";
            } else if (buscarProdID(produto_add.getId()) != null) {
                return "Produto ja existente";
            }

        }
        catch (Exception e){
            throw new RuntimeException("Erro ao contatar o repositório", e);
        } return null;
    }

    public void RemoveProduto(Produto produto_deletado){
        try{
            List<Produto> produtoList = listarProds();
            // for para analisar cada produto
            for(int i = 0; i < produtoList.size(); i++){
                Produto produto_analisado = produtoList.get(i);
                // verifica se os IDs são iguais
                if(produto_analisado.getId() == produto_deletado.getId()) {
                    // remove pelo indice do for
                    produtoList.remove(i);
                    break;
                }
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), produtoList);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar remover o produto do repositório", e);
        }
    }


    public Produto buscarProdID(int id) {
        List<Produto> produtosAnalisados = listarProds();
        try {
            for (Produto produto : produtosAnalisados) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        } catch (Exception e) {
            throw new RuntimeException("Produto nao encontrado", e);
        }
        return null;
    }

    public void atualizar_produto(int id, String nome, Double preco, int qtd) {
        try {
            List<Produto> produtoList = listarProds();
            
            for (Produto p : produtoList) {
                if (p.getId() == id) {
                    p.setNome(nome);
                    p.setPreco(preco);
                    p.setQuantidade(qtd);

                    mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), produtoList);
                }
            }
            } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o repositório", e);}
    }



}
