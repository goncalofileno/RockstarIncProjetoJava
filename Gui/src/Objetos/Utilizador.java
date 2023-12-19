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

    public boolean verificaLoginPass(Utilizador utilizador, String pass) {
        if (utilizador.getPass().equals(pass)) {
            return true;
        } else
            return false;
    }
}
