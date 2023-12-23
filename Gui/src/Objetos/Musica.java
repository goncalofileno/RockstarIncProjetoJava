package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;

public class Musica {
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////

    /**
     * Construtor cria musicas sem album atribuido (singles)
     */
    public Musica(String titulo, Artista compositor, String genero, double valor) {

        this.titulo = titulo;
        this.compositor = compositor;
        this.genero = genero;

        listaPrecos = new ArrayList<>();
        listaPrecos.add(new Preco(valor, LocalDate.now()));

        listaRatings = new ArrayList<>();

        this.dataAdicionada = LocalDate.now();
        this.estadoAtividade = true;

        this.vendas = 0;
        this.faturacao = 0.00;

        compositor.addSingles(this);
    }

    /**
     * Construtor cria musica com album (musica pertencente a album)
     */
    public Musica(String titulo, Artista compositor, String genero, double valor, Album album) {
        this.titulo = titulo;
        this.compositor = compositor;
        this.genero = genero;

        listaPrecos = new ArrayList<>();
        listaPrecos.add(new Preco(valor, LocalDate.now()));

        listaRatings = new ArrayList<>();

        this.dataAdicionada = LocalDate.now();
        this.estadoAtividade = true;

        this.album = album;
        this.vendas = 0;
        this.faturacao = 0.00;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////GETTERS//////////////////////////////////////////////////////////////////////
    public String getTitulo() {
        return titulo;
    }

    public Artista getCompositor() {
        return compositor;
    }

    public String getGenero() {
        return genero;
    }

    public double getPrecoMusica() {

        return listaPrecos.get(listaPrecos.size() - 1).getValor();
    }

    public ArrayList<Rating> getListaRatings() {
        return listaRatings;
    }

    public ArrayList<Preco> getListaPrecos() {
        return listaPrecos;
    }

    public double getRatingMedio() {
        int totalRatings = 0;
        for (Rating rating : listaRatings) {
            totalRatings += rating.getAvaliacao();
        }
        if (totalRatings > 0) {
            return (double) (totalRatings / listaRatings.size());
        } else {
            return 0;
        }
    }

    public int getVendas() {
        return vendas;
    }

    public double getFaturacao() {
        return faturacao;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////SETTERS///////////////////////////////////////////////////////////////////////

    /**
     * Adiciona um novo preço à música
     */
    public void novoPreco(double valor, LocalDate dataPreco) {
        listaPrecos.add(new Preco(valor, dataPreco));
    }

    /**
     * Adiciona um novo rating à musica
     */
    public void atribuiRating(Cliente cliente, int valor) {
        listaRatings.add(new Rating(cliente, this, valor));
    }

    /**
     * Altera o titulo da música.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Altera a visibilidade de uma musica.
     */
    public void setEstadoAtividade(boolean estadoAtividade) {
        this.estadoAtividade = estadoAtividade;
    }

    /**
     * Incrementa as vendas.
     */
    public void setVendas(int vendas) {
        this.vendas = this.vendas + vendas;
    }

    /**
     * Incrementará a faturação.
     */
    public void setFaturacao(double faturacao) {
        this.faturacao = this.faturacao + faturacao;
    }

    public Rating getRatingCliente(Cliente cliente){
        Rating ratingCliente = null;
        for(Rating rating: listaRatings){
            if(rating.getCliente().equals(cliente)){
                ratingCliente = rating;
            }
        }
        return ratingCliente;
    }

    public void addRating(Cliente cliente, int avaliacao){
        listaRatings.add(new Rating(cliente, this, avaliacao));
    }

}
