package Objetos;

import java.util.ArrayList;

public class Artista extends Utilizador {
    private int pin;
    private ArrayList<Album> albuns;
    private ArrayList<Musica> singles;

    public Artista(String username, String pass, String nome, int pin) {
        super(username, pass, nome);
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }
}
