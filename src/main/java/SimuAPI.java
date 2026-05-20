
public class SimuAPI {

    public Integer API_Request(Double valor_compra, Cliente cliente){
        // caso a lista esteja vazia, API retorna 404
        if (valor_compra > cliente.getSaldo()) {
            return 404;
        } else if (valor_compra <= cliente.getSaldo()) {
            cliente.setSaldo(cliente.getSaldo() - valor_compra);
            return 200;
        } return null;
  
    }


}
