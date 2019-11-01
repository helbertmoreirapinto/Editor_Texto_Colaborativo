package client;

public abstract class Pessoa {

    private String nome;

    /**
     * Construtor da classe Pessoa(String).
     *
     * @param nome
     */
    public Pessoa(String nome) {
        this.nome = nome;
    }

    /* GETTER AND SETTER */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
