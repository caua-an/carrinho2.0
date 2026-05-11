import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Classe para realizar acesso e tratamento do JSON.
public class RepositorioProds {
    // pegar path relativo do dados/produtos.json
    private static final String PATH = Paths.get("dados", "produtos.json").toString();
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

    public void AddProduto(Produto produto_add){
        try {

            List<Produto> produtoList = listarProds();
            // caso o produto não exista
            if(buscarProdID(produto_add.getId()) == null){
                // adiciona o produto na lista
                produtoList.add(produto_add);
                // escreve a lista no arquivo do PATH
                mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PATH), produtoList);
            }

        }
        catch (Exception e){
            throw new RuntimeException("Erro ao contatar o repositório", e);
        }
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
        // for each do produtos
        for (Produto produto : produtosAnalisados) {
            if (produto.getId() == id) {
                return produto;
            }
        }

        return null;
    }

    public boolean existeProdutos(){
        return api_repo.API_Request(this) == 200;
    }
}
