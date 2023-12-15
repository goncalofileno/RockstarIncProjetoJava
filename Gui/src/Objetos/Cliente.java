package Objetos;

public class Cliente {
    private String nome,pass;

    public Cliente(String nome, String pass) {
        this.nome = nome;
        this.pass = pass;
    }

    public String getNome() {
        return nome;
    }

    public String getPass() {
        return pass;
    }
}
