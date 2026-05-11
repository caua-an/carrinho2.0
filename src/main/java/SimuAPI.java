import java.util.List;

public class SimuAPI {

    public int API_Request(RepositorioProds repo){
        // caso a lista esteja vazia, API retorna 404
        List<Produto> produtos_analisados = repo.listarProds();
        if(produtos_analisados.isEmpty()){
            return 404;
        }
        // caso exista informações
        return 200;
    }


}
