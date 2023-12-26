package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class RockstarInc {

    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private ArrayList<Artista> artistasList;
    private ArrayList<Cliente> clientesList;
    private ArrayList<Playlist> playlistsList;
    private ArrayList<Album> albunsList;
    private ArrayList<Musica> musicasList;
    private Utilizador utilizadorAtual;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    public RockstarInc() {
        artistasList = new ArrayList<>();
        clientesList = new ArrayList<>();
        playlistsList = new ArrayList<>();
        albunsList = new ArrayList<>();
        musicasList = new ArrayList<>();

        //////////////////////////////////////VALORES PARA TESTE DA APP/////////////////////////////////////////////////
        addCliente("admin", "admin", "admin");
        Artista as = new Artista("as", "as", "as", "1111");
        addArtista("as", "as", "as", "1111");

        Album album1 = new Album("Rock in Rio", "Rock", as);

        Artista zecabra = new Artista("zecabra", "zecabra", "Zé Cabra", "1234");
        artistasList.add(zecabra);
        Artista marialeal = new Artista("marialeal", "marialeal", "Maria Leal", "1234");
        artistasList.add(marialeal);
        Artista anamalhoa = new Artista("anamalhoa", "anamalhoa", "Ana Malhoa", "1234");
        artistasList.add(anamalhoa);
        Artista quimbarreiros = new Artista("quimbarreiros", "quimbarreiros", "Quim Barreiros", "1234");
        artistasList.add(quimbarreiros);
        Artista rosinha = new Artista("rosinha", "rosinha", "Rosinha", "1234");
        artistasList.add(rosinha);

        Musica deixei = new Musica("Deixei tudo por ela", zecabra, "Popular", 0.00);
        musicasList.add(deixei);
        deixei.novoPreco(1.5, LocalDate.now());
        deixei.novoPreco(2.5, LocalDate.now());
        Musica voltei = new Musica("Voltei para ela", zecabra, "Popular", 0.00);
        musicasList.add(voltei);
        Musica aqui = new Musica("Aqui só para ti", marialeal, "Pop Popular", 0.00);
        musicasList.add(aqui);
        Musica traidora = new Musica("Traidora", marialeal, "Pop Popular", 0.00);
        musicasList.add(traidora);
        Musica turbinada = new Musica("Turbinada", anamalhoa, "Pop Popular", 2.5);
        musicasList.add(turbinada);
        turbinada.novoPreco(1.5, LocalDate.now());
        turbinada.novoPreco(1, LocalDate.now());
        Musica elamexe = new Musica("Ela mexe", anamalhoa, "Pop Popular", 1.5);
        musicasList.add(elamexe);
        Musica bacalhau = new Musica("Deixa-me cheirar teu bacalhau", quimbarreiros, "Popular", 3.00);
        musicasList.add(bacalhau);
        Musica casar = new Musica("Qual o melhor dia para casar", quimbarreiros, "Popular", 2.5);
        musicasList.add(casar);
        Musica chupo = new Musica("Eu chupo", rosinha, "Popular", 0.00);
        musicasList.add(chupo);
        Musica pacote = new Musica("Eu levo no pacote", rosinha, "Popular", 1.5);
        musicasList.add(pacote);

        ArrayList musicaPopPopular = new ArrayList<Musica>();
        musicaPopPopular.add(aqui);
        musicaPopPopular.add(traidora);

        ArrayList musicaPopular = new ArrayList<Musica>();
        musicaPopular.add(deixei);
        musicaPopular.add(casar);

        Album album2 = new Album(musicaPopular, "Popular in Rio", "Popular", as);

        Playlist PopPopular = new Playlist(musicaPopPopular, "Musicas de Pop Popular", true);
        Playlist Popular = new Playlist(musicaPopular, "Musica Popular", true);

        clientesList.get(0).addPlaylist(PopPopular);
        clientesList.get(0).addPlaylist(Popular);

        clientesList.get(0).addBiblioteca(aqui);
        clientesList.get(0).addBiblioteca(traidora);
        clientesList.get(0).addBiblioteca(deixei);
        clientesList.get(0).addBiblioteca(casar);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////ADDERS////////////////////////////////////////////////////////////////////
    public void addArtista(String username, String pass, String nome, String pin) {
        artistasList.add(new Artista(username, pass, nome, pin));
    }

    public void addCliente(String username, String pass, String nome) {
        clientesList.add(new Cliente(username, pass, nome));
    }

    public void addPlaylist(Playlist playlist) {
        playlistsList.add(playlist);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////MÉTODOS DIVERSOS//////////////////////////////////////////////////////////

    /**
     * Método verifica a existencia de utilizador através de username e caso o utilizador exista devolve o objeto do mesmo,
     * devolve NULL caso nao encontre.
     */
    public Utilizador verificarUtilizador(String username) {
        for (int i = 0; i < artistasList.size(); i++) {
            System.out.println(artistasList.get(i).getUsername());
            if (artistasList.get(i).getUsername().equals(username)) {
                return artistasList.get(i);
            }
        }
        for (int i = 0; i < clientesList.size(); i++) {
            if (clientesList.get(i).getUsername().equals(username)) {
                return clientesList.get(i);
            }
        }
        return null;
    }

    public boolean verificarUsername(String username) {
        if (!verificarExistenciaUser(username)) {
            if (username.length() > 4) {
                return true;
            } else return false;
        } else return false;
    }

    public boolean verificarPass(String pass) {
        if (pass.length() > 4) {
            return true;
        } else return false;
    }

    public boolean verificarPin(String pin) {
        if (pin.length() == 4 && pin.matches("\\d+")) {
            return true;
        } else return false;
    }

    public boolean verificarExistenciaUser(String username) {
        for (Artista artista : artistasList) {
            if (artista.getUsername().equals(username)) {
                return true;
            }
        }
        for (Cliente cliente : clientesList) {
            if (cliente.getUsername().equals(username)) {
                return true;

            }
        }
        return false;
    }

    public Musica musicaSelecionada(String username, String titulo) {
        for (int i = 0; i < musicasList.size(); i++) {
            if (musicasList.get(i).getTitulo().equals(titulo) && musicasList.get(i).getCompositor().getUsername().equals(username)) {
                return musicasList.get(i);
            }
        }

        return null;
    }

    public void removerPlaylist(Playlist playlist) {
        playlistsList.remove(playlist);
    }

    public ArrayList<Musica> getMusicasList() {
        return musicasList;
    }

    public ArrayList<Musica> pesquisaTitulo(ArrayList<Musica> listaDeMusicas, String titulo) {
        ArrayList listaPesquisada = new ArrayList<Musica>();
        for (Musica musica : listaDeMusicas) {
            if (musica.getTitulo().contains(titulo)) {
                listaPesquisada.add(musica);
            }
        }
        return listaPesquisada;
    }

    public ArrayList<Musica> pesquisaArtista(ArrayList<Musica> listaDeMusicas, String artista) {
        ArrayList listaPesquisada = new ArrayList<Musica>();
        for (Musica musica : listaDeMusicas) {
            if (musica.getCompositor().getNome().contains(artista)) {
                listaPesquisada.add(musica);
            }
        }
        return listaPesquisada;
    }

    public ArrayList<Musica> musicasVisiveis(){
        ArrayList<Musica> musicasVisiveis=new ArrayList<>();
        for (int i=0;i<musicasList.size();i++){
            if(musicasList.get(i).isEstadoAtividade()){
                musicasVisiveis.add(musicasList.get(i));
            }
        }
        return musicasVisiveis;
    }


}


