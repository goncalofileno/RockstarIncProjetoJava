package Objetos;

import java.util.ArrayList;

public class RockstarInc {
    private ArrayList<Artista> artistasList;
    private ArrayList<Cliente> clientesList;
    private ArrayList<Playlist> playlistsList;
    private ArrayList<Album> albunsList;
    private ArrayList<Musica> musicasList;
    private Utilizador utilizadorAtual;

    public RockstarInc() {
        artistasList=new ArrayList<>();
        clientesList=new ArrayList<>();
        playlistsList=new ArrayList<>();
        albunsList=new ArrayList<>();
        musicasList=new ArrayList<>();
    }


    public Utilizador verificarUtilizador(String username){
        for (Artista artista:artistasList){
            if (artista.getUsername().equals(username)){
                return artista;
            }
        }
        for (Cliente cliente:clientesList){
            if (cliente.getUsername().equals(username)){
                return cliente;
            }
        }
        return null;

    }


    /*public boolean verificarExistenciaUser(String username){
        for (Artista artista:artistasList){
            if (artista.getUsername().equals(username)){
                return true;
            }
        }
        for (Cliente cliente:clientesList){
            if (cliente.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }*/

    public boolean verificaLoginPass (Utilizador utilizador, String pass){
        if (utilizador.getPass().equals(pass)){
            return true;
        }
        else return false;
    }

    public boolean verificarLoginPin(Artista artista,String pin){
        if (artista.getPin().equals(pin)){
            return true;
        }
        return false;
    }
}
