package Objetos;

import java.time.LocalDate;

public abstract class Utilizador {
    private String username,pass,nome;
    private LocalDate dataRegisto;

    public Utilizador(String username, String pass, String nome) {
        this.username = username;
        this.pass = pass;
        this.nome = nome;
        dataRegisto.now();
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public boolean verificaLoginPass(String pass) {
        if (this.pass.equals(pass)) {
            return true;
        } else
            return false;
    }

    public String getNome() {
        return nome;
    }
}
