package Objetos;

import java.util.ArrayList;

public class Artista extends Utilizador {
    private String pin;
    private ArrayList<Album> albuns;
    private ArrayList<Musica> singles;

    public Artista(String username, String pass, String nome, String pin) {
        super(username, pass, nome);
        this.pin = pin;
    }



    public String getPin() {
        return pin;
    }

    public boolean verificarLoginPin(Artista artista, String pin) {
        if (artista.getPin().equals(pin)) {
            System.out.println("3º-"+pin);
            return true;
        }
        return false;
    }


}
