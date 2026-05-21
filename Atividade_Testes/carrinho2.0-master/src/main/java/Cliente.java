public class Cliente {
    private String nome;
    private Integer id;
    private Double saldo;

    public Cliente(String nome, Integer id, Double saldo ) {
        this.nome = nome;
        this.id = id;
        this.saldo = saldo;
    };

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double valor) {saldo = valor;}
}
