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

    public ArrayList<Musica> getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    //Setters

    public void setCarrinhoDeCompras(ArrayList<Musica> carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }

    public void setComprasEfetuadas(Compra compra){
        this.comprasEfetuadas.add(compra);
    }
}
