package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Musica {

    private String titulo;
    private Artista compositor;
    private String genero;
    private ArrayList<Preco> listaPrecos;
    private ArrayList<Rating> listaRatings;
    private LocalDate dataAdicionada;
    private boolean estadoAtividade;
    private Album album;
    private int vendas;
    private double faturacao;

    //Construtor que cria musicas sem album atribuido
    public Musica(String titulo, Artista compositor, String genero, double valor, LocalDate dataPreco, LocalDate dataAdicionada, int vendas, double faturacao) {

        this.titulo = titulo;
        this.compositor = compositor;
        this.genero = genero;

        listaPrecos = new ArrayList<>();
        listaPrecos.add(new Preco(valor, dataPreco));

        listaRatings = new ArrayList<>();

        this.dataAdicionada = dataAdicionada;
        this.estadoAtividade = true;


        this.vendas = 0;
        this.faturacao = 0.00;
    }

    //Construtor que cria musicas com album atribuido
    public Musica(String titulo, Artista compositor, String genero, double valor, LocalDate dataPreco, LocalDate dataAdicionada, boolean estadoAtividade, Album album, int vendas, double faturacao) {
        this.titulo = titulo;
        this.compositor = compositor;
        this.genero = genero;


        listaPrecos = new ArrayList<>();
        listaPrecos.add(new Preco(valor, dataPreco));

        listaRatings = new ArrayList<>();

        this.dataAdicionada = dataAdicionada;
        this.estadoAtividade = estadoAtividade;

        this.album = album;
        this.vendas = 0;
        this.faturacao = 0.00;
    }

    //metodo para novo preco
    public void novoPreco(double valor, LocalDate dataPreco){
        listaPrecos.add(new Preco(valor, dataPreco));
    }
    //metodo para novo rating
    public void atribuiRating(Cliente cliente, int valor){
        listaRatings.add(new Rating(cliente, this, valor));
    }

    //metodo para novo titulo da musica
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    //metodo para visibilidade da musica
    public void setEstadoAtividade(boolean estadoAtividade) {
        this.estadoAtividade = estadoAtividade;
    }
    public void setVendas(int vendas) {
        this.vendas = this.vendas + vendas;
    }
    public void setFaturacao(double faturacao) {
        this.faturacao = this.faturacao + faturacao;
    }

    //Diversos Getters

    public String getTitulo() {
        return titulo;
    }

    public Artista getCompositor() {
        return compositor;
    }

    public String getGenero() {
        return genero;
    }

   // public double getPrecoMusica(){
   //     return listaPrecos.getLast().getValor();
   //}

    public ArrayList<Preco> getListaPrecos() {
        return listaPrecos;
    }

    public double getRatingMedio(){
        int totalRatings = 0;
        for(Rating rating: listaRatings){
            totalRatings += rating.getAvaliacao();
        }
        return (double) (totalRatings/listaRatings.size());
    }

    public int getVendas() {
        return vendas;
    }

    public double getFaturacao() {
        return faturacao;
    }
}
