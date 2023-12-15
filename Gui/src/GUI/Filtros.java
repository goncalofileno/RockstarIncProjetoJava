package GUI;

import javax.swing.*;
import java.awt.*;
import java.security.GeneralSecurityException;

public class Filtros extends JPanel {

    private JLabel lblFiltros;
    private JComboBox comboBoxArtista, comboBoxGenero;

    public Filtros(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(180),resizeHeight(80));
        setLayout(null);

        float[] cor =new float[3];
        cor=Color.RGBtoHSB(155,223,243,cor);
        setBackground(Color.getHSBColor(cor[0],cor[1],cor[2]));

        Font font=new Font("SansSerif",Font.BOLD,12);
        lblFiltros=new JLabel("Filtrar:");
        lblFiltros.setFont(font);
        lblFiltros.setBounds(resizeWidth(10),resizeHeight(15),resizeWidth(50),resizeHeight(15));
        add(lblFiltros);

        String[] artistas={"Artistas","Flor","Eminem","Bispo","dop"};
        comboBoxArtista=new JComboBox(artistas);
        comboBoxArtista.setBounds(lblFiltros.getX()+resizeWidth(5),lblFiltros.getY()+lblFiltros.getHeight()+resizeHeight(15),resizeWidth(85),resizeHeight(30));
        add(comboBoxArtista);

        String[] genero={"Genero","Rock","Rap","Pop","Country","Blues"};
        comboBoxGenero=new JComboBox(genero);
        comboBoxGenero.setBounds(comboBoxArtista.getX()+comboBoxArtista.getWidth()+resizeWidth(10),comboBoxArtista.getY(),resizeWidth(85),resizeHeight(30));
        add(comboBoxGenero);

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
}
