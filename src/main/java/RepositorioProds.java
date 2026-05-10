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

    public Produto buscarProdID(int id) {
        List<Produto> produtosAnalisados = listarProds();

        for (Produto produto : produtosAnalisados) {
            if (produto.getId() == id) {
                return produto;
            }
        }

        return null;
    }
}
