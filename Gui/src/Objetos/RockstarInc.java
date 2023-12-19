package Objetos;

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


