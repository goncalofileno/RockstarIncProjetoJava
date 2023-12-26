package GUI.Artista;

import Objetos.Artista;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;

public class InterfaceArtista extends JPanel {
    private RockstarInc rockstar;
    private Artista utilizadorAtual;
    private ArtistaAlbuns panelAlbuns;
    private JLabel lblUser,lblTabela;

    public InterfaceArtista(RockstarInc rockstar, Artista utilizadorAtual){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;

        mudarCorRGB(this,51,153,153);
        setLayout(null);

        panelAlbuns=new ArtistaAlbuns(rockstar,utilizadorAtual);

        panelAlbuns.setBounds(resizeWidth(10),resizeHeight(50),resizeWidth(200),resizeHeight(500));
        add(panelAlbuns);

        Font font3=new Font("SansSerif",Font.BOLD,13);
        lblTabela=new JLabel("As suas Músicas:");
        lblTabela.setFont(font3);
        lblTabela.setBounds(panelAlbuns.getX()+panelAlbuns.getWidth()+resizeWidth(25),resizeHeight(20),resizeWidth(400),resizeHeight(20));
        add(lblTabela);

        Font font4=new Font("SansSerif",Font.BOLD ,13);
        lblUser=new JLabel("Artista: "+utilizadorAtual.getUsername());
        lblUser.setFont(font4);
        lblUser.setBounds(panelAlbuns.getX()+resizeWidth(10),lblTabela.getY(),resizeWidth(150),lblTabela.getHeight());
        add(lblUser);

    }

    private int resizeWidth(int width ){
        Dimension ecra=Toolkit.getDefaultToolkit().getScreenSize();
        int widthAtualizada;
        widthAtualizada=(int)(width*ecra.getWidth())/1536;
        return widthAtualizada;
    }

    private int resizeHeight(int height){
        Dimension ecra=Toolkit.getDefaultToolkit().getScreenSize();
        int heightAtualizada;
        heightAtualizada=(int)(height*ecra.getHeight())/864;
        return heightAtualizada;
    }

    private void mudarCorRGB(Component componente, int red, int green, int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }
}
