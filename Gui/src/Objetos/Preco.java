package Objetos;

import java.time.LocalDate;

public class Preco {
    private double valor;
    private LocalDate dataPreco;

    public Preco(double valor, LocalDate dataPreco) {
        this.valor = valor;
        this.dataPreco = dataPreco;
    }

    public double getValor() {
        return valor;
    }
}
