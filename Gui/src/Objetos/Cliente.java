package Objetos;

import java.util.ArrayList;

public class Cliente extends Utilizador{
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private ArrayList<Musica> carrinhoDeCompras;
    private ArrayList<Musica> biblioteca;
    private ArrayList<Playlist> playlistsProprias;
    private ArrayList<Compra> comprasEfetuadas;
    private double saldo;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    public Cliente(String username, String pass, String nome) {
        super(username, pass, nome);
        carrinhoDeCompras=new ArrayList<>();
        playlistsProprias=new ArrayList<>();
        comprasEfetuadas=new ArrayList<>();
        biblioteca=new ArrayList<>();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////GETTTERS//////////////////////////////////////////////////////////////////
    public ArrayList<Musica> getCarrinhoDeCompras() {
        return carrinhoDeCompras;
    }

    public ArrayList<Playlist> getPlaylistsProprias() {
        return playlistsProprias;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////SETTERS//////////////////////////////////////////////////////////////////
    public void setCarrinhoDeCompras(ArrayList<Musica> carrinhoDeCompras) {
        this.carrinhoDeCompras = carrinhoDeCompras;
    }
    public void setComprasEfetuadas(Compra compra){
        this.comprasEfetuadas.add(compra);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////ADDERS//////////////////////////////////////////////////////////////////
    public void addPlaylist(Playlist playlist){
        playlistsProprias.add(playlist);
    }
    public void addBiblioteca(Musica musica){
        biblioteca.add(musica);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////METODOS DIVERSOS////////////////////////////////////////////////////////

    /**
     * Método criado para receber um nome de uma playlist, uma quantidade músicas a adicionar, um genero e visibilidade
     * Metodo percorre a arraylist de musicas adquiridas do utilizador e seleciona as primeiras que encontrar do mesmo
     * genero, até não encontrar mais ou até o tamanho ser atingido.
     */
    public Playlist criaPlaylistAI(String nome, int quantidade, String genero, boolean visibilidade){
        Playlist playlistAI = new Playlist(nome, visibilidade);

        for(Musica musica: biblioteca){
            if(musica.getGenero().equals(genero) && (playlistAI.getMusicas().size() < quantidade)) {
                playlistAI.addMusica(musica);
            }
        }
        return playlistAI;
    }

    public boolean verificarQtdMusicas (Playlist playlist,int quantidade){
        if (playlist.getMusicas().size()<quantidade){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<Musica> getBiblioteca() {
        return biblioteca;
    }

    public void comprarMusica(Musica musica){
            if (musica.getPrecoMusica() != 0) {
                carrinhoDeCompras.add(musica);

            } else {
                biblioteca.add(musica);

            }

    }

    public boolean verificarBiblioteca(Musica musica){
        for (int i=0;i<biblioteca.size();i++){
            if (biblioteca.get(i).equals(musica)){
                return true;
            }
        }
        return false;
    }
}
