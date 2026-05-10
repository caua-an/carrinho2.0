import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto {
    // assinatura para linkar o atributo com a chave do json
    @JsonProperty("id_prod")
    protected int id_prod;
    protected String nome;
    protected double preco;
    // assinatura para linkar o atributo com a chave do json
    @JsonProperty("quant")
    protected int quant;

    public Produto() {
    }

    public Produto(int p_id, String p_nome, double p_preco, int p_quant) {
        this.id_prod = p_id;
        this.nome = p_nome;
        this.preco = p_preco;
        this.quant = p_quant;
    }

    public int getId() {
        return id_prod;
    }

    @JsonProperty("id_prod")
    public void setId(int id) {
        this.id_prod = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quant;
    }

    @JsonProperty("quant")
    public void setQuantidade(int quantidade) {
        this.quant = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id_prod=" + id_prod +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quant=" + quant +
                '}';
    }
}
