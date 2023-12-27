package Objetos;

import java.util.ArrayList;

public class Artista extends Utilizador {
    ///////////////////////////////////ATRIBUTOS////////////////////////////////////////////////////////////////////////
    private String pin;
    private ArrayList<Album> albuns;
    private ArrayList<Musica> singles;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////CONSTRUTORES//////////////////////////////////////////////////////////////
    public Artista(String username, String pass, String nome, String pin) {
        super(username, pass, nome);
        this.albuns = new ArrayList<>();
        this.singles = new ArrayList<>();
        this.pin = pin;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////GETTTERS//////////////////////////////////////////////////////////////////
    public String getPin() {
        return pin;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////ADDERS///////////////////////////////////////////////////
    public void addAlbum(Album album) {
        albuns.add(album);
    }

    public void addSingles(Musica musica) {
        singles.add(musica);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////METODOS DIVERSOS///////////////////////////////////////////////////

    /**
     * Metodo criado para receber um pin em formato String e verificar se o mesmo equivale ao artista em questão, devolve
     * variavel booleana, true se corresponder e false se não corresponder
     */
    public boolean verificarLoginPin(String pin) {
        if (this.pin.equals(pin)) {
            return true;
        }
        return false;
    }

    public ArrayList<Album> getAlbuns() {
        return albuns;
    }

    public boolean verificarAlbum(String titulo){
        for (int i=0;i<albuns.size();i++){
            if(albuns.get(i).getNome().equals(titulo)){
                return true;
            }
        }
        return false;
    }

    public boolean verificarMusica(String nomeMusica){
        for (int i=1;i<albuns.size();i++){
            for(int j=0;j<albuns.get(i).getMusicas().size();j++) {
                if (albuns.get(i).getMusicas().get(j).equals(nomeMusica)){
                    return true;
                }
            }
        }

        for(int i=0;i<singles.size();i++){
            if(singles.get(i).getTitulo().equals(nomeMusica)){
                return true;
            }
        }
        return false;
    }

    public String[] titulosAlbuns(){
        String[] titulos=new String[albuns.size()+1];

        titulos[0]="Singles";

        for(int i=0;i<albuns.size();i++){
            titulos[i+1]=albuns.get(i).getNome();
        }
        return titulos;
    }

}
