package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Compra {
    private Cliente cliente;
    private ArrayList<Musica> listaMusicas;
    private LocalDate dataCompra;
    private double valorCompra;

    public Compra(Cliente cliente, ArrayList<Musica> listaMusicas) {
        this.cliente = cliente;

        this.listaMusicas = cliente.getCarrinhoDeCompras();

        cliente.setCarrinhoDeCompras(new ArrayList<Musica>());

        this.dataCompra = LocalDate.now();

        double total = 0;

        for(Musica musica: listaMusicas){
            total = total + musica.getPrecoMusica();
        }
        this.valorCompra = total;
        
        cliente.setComprasEfetuadas(this);
    }

    //Getters
    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public double getValorCompra() {
        return valorCompra;
    }
}


