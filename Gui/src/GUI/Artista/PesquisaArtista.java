package GUI.Artista;

import GUI.Cliente.InterfaceCliente;
import GUI.Cliente.TabelaCliente;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisaArtista extends JPanel {
    private TabelaArtista tabelaArtista;
    private RockstarInc rockstar;
    private InterfaceArtista interfaceArtista;
    private JLabel lblPesquisa;
    private JTextField txtPesquisa;
    private JRadioButton radioTitulo, radioAlbum;
    private JButton btnPesquisa;

    public PesquisaArtista(TabelaArtista tabelaArtista, RockstarInc rockstar, InterfaceArtista interfaceArtista){
        this.tabelaArtista=tabelaArtista;
        this.rockstar=rockstar;
        this.interfaceArtista=interfaceArtista;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(180),resizeHeight(80));
        setLayout(null);
        mudarCorRGB(this,155,223,243);

        Font font=new Font("SansSerif",Font.BOLD,12);
        lblPesquisa=new JLabel("Pesquisa:");
        lblPesquisa.setFont(font);
        lblPesquisa.setBounds(resizeWidth(10),resizeHeight(15),resizeWidth(100),resizeHeight(15));
        add(lblPesquisa);

        txtPesquisa=new JTextField(30);
        txtPesquisa.setFont(font);
        txtPesquisa.setBounds(lblPesquisa.getX(),lblPesquisa.getY()+lblPesquisa.getHeight()+resizeHeight(5),resizeWidth(125),resizeHeight(22));
        add(txtPesquisa);

        radioTitulo=new JRadioButton("Título");
        radioTitulo.setFont(font);
        radioTitulo.setBounds(txtPesquisa.getX(),txtPesquisa.getY()+txtPesquisa.getHeight()+resizeHeight(10),resizeWidth(70),resizeHeight(20));
        radioTitulo.setOpaque(false);
        radioTitulo.setSelected(true);
        add(radioTitulo);

        radioAlbum=new JRadioButton("Álbum");
        radioAlbum.setFont(font);
        radioAlbum.setBounds(radioTitulo.getX()+radioTitulo.getWidth()+resizeWidth(5),radioTitulo.getY(),radioTitulo.getWidth(),radioTitulo.getHeight());
        radioAlbum.setOpaque(false);
        add(radioAlbum);

        ButtonGroup grupoBtn=new ButtonGroup();
        grupoBtn.add(radioAlbum);
        grupoBtn.add(radioTitulo);

        btnPesquisa=new JButton("\uD83D\uDD0E");
        btnPesquisa.setBounds(txtPesquisa.getX()+txtPesquisa.getWidth()+resizeWidth(5),txtPesquisa.getY(),resizeWidth(50),resizeHeight(22));
        add(btnPesquisa);

        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioTitulo.isSelected()){
                    tabelaArtista.setListaMusicasAtual(rockstar.pesquisaTitulo(tabelaArtista.getListaMusicasAtual(),txtPesquisa.getText()));
                    tabelaArtista.printMusicas(tabelaArtista.getListaMusicasAtual());
                    tabelaArtista.setAlbum(null);
                }
                else if(radioAlbum.isSelected()){
                    tabelaArtista.setListaMusicasAtual(rockstar.pesquisaAlbum(tabelaArtista.getListaMusicasAtual(),txtPesquisa.getText()));
                    tabelaArtista.printMusicas(tabelaArtista.getListaMusicasAtual());
                    tabelaArtista.setAlbum(null);
                }
            }
        });

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
    private void mudarCorRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

}
