package Objetos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class RockstarInc {
    private ArrayList<Artista> artistasList;
    private ArrayList<Cliente> clientesList;
    private ArrayList<Playlist> playlistsList;
    private ArrayList<Album> albunsList;
    private ArrayList<Musica> musicasList;
    private Utilizador utilizadorAtual;

    public RockstarInc() {
        artistasList = new ArrayList<>();
        clientesList = new ArrayList<>();
        playlistsList = new ArrayList<>();
        albunsList = new ArrayList<>();
        musicasList = new ArrayList<>();

        //valores de testes
        addCliente("admin","admin", "admin");

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
        Musica voltei = new Musica("Voltei para ela", zecabra, "Popular", 0.00);
        musicasList.add(voltei);
        Musica aqui = new Musica("Aqui só para ti", marialeal, "Pop Popular", 0.00);
        musicasList.add(aqui);
        Musica traidora = new Musica("Traidora", marialeal, "Pop Popular", 0.00);
        musicasList.add(traidora);
        Musica turbinada = new Musica("Turbinada", anamalhoa, "Pop Popular", 2.5);
        musicasList.add(turbinada);
        Musica elamexe = new Musica("Ela mexe", anamalhoa, "Pop Popular", 1.5);
        musicasList.add(elamexe);
        Musica bacalhau = new Musica("Deixa-me cheirar teu bacalhau", quimbarreiros, "Popular", 3.00);
        musicasList.add(bacalhau);
        Musica casar = new Musica("Qual o melhor dia para casar", quimbarreiros, "Popular", 2.5);
        musicasList.add(casar);
        Musica chupo = new Musica("Eu chupo", rosinha, "Popular", 0.00);
        musicasList.add(chupo);
        Musica pacote = new Musica("Eu levo no pacote", rosinha, "Popular", 1.5);

        ArrayList musicaPopPopular = new ArrayList<Musica>();
        musicaPopPopular.add(aqui);
        musicaPopPopular.add(traidora);

        ArrayList musicaPopular = new ArrayList<Musica>();
        musicaPopular.add(deixei);
        musicaPopular.add(casar);

        Playlist PopPopular = new Playlist(musicaPopPopular, "Musicas de Pop Popular", true);
        Playlist Popular = new Playlist(musicaPopular, "Musica Popular", true);

        clientesList.get(0).addPlaylist(PopPopular);
        clientesList.get(0).addPlaylist(Popular);

        clientesList.get(0).addBiblioteca(aqui);
        clientesList.get(0).addBiblioteca(traidora);
        clientesList.get(0).addBiblioteca(deixei);
        clientesList.get(0).addBiblioteca(casar);
        clientesList.get(0).addBiblioteca(turbinada);
    }

    public Utilizador verificarUtilizador(String username) {
        for (int i=0;i<artistasList.size();i++) {
            System.out.println(artistasList.get(i).getUsername());
            if (artistasList.get(i).getUsername().equals(username)) {
                return artistasList.get(i);
            }
        }
        for (int i=0;i<clientesList.size();i++) {
                if (clientesList.get(i).getUsername().equals(username)) {
                    return clientesList.get(i);
                }
        }
        return null;
    }

   /* public boolean verificarExistenciaUser(String username) {
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
    }*/

    public boolean verificarUsername(String username){
        if (!verificarExistenciaUser(username)){
            if (username.length()>4){
                return true;
            }
            else return false;
        }
        else return false;
    }
    public boolean verificarPass(String pass){
        if (pass.length()>4){
            return true;
        }
        else return false;
    }
    public boolean verificarPin(String pin) {
        if (pin.length()==4 && pin.matches("\\d+")) {
            return true;
        }
        else return false;
    }
    public boolean verificarExistenciaUser(String username){
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
    public boolean verificarLoginPin(Artista artista,String pin){
        if (artista.getPin().equals(pin)){
            return true;
        }
        return false;
    }
    public void addArtista(String username, String pass, String nome, String pin){
        artistasList.add(new Artista(username,pass, nome, pin));
    }
    public void addCliente(String username, String pass, String nome){
        clientesList.add(new Cliente(username, pass,  nome));
    }
    public ArrayList<Artista> getArtistasList() {
        return artistasList;
    }










        /*     public boolean verificaLoginParaCliente (String username, String pass){
                    for (Cliente cliente : clientesList) {
                        if (cliente.getUsername().equals(username)) {
                            if (cliente.getPass().equals(pass)) {
                                utilizadorAtual = cliente;
                                return true;
                            }

                        }
                    }
                    return false;
                }*/
}


