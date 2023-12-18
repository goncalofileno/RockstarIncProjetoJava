package Objetos;

import java.util.ArrayList;

public class Cliente extends Utilizador{
    private ArrayList<Musica> carrinhoDeCompras;
    private ArrayList<Playlist> playlistsProprias;
    private ArrayList<Compra> comprasEfetuadas;
    private double saldo;

    public Cliente(String username, String pass, String nome) {
        super(username, pass, nome);
    }
}
