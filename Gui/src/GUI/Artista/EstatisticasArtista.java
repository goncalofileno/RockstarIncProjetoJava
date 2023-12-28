package GUI.Artista;

import Objetos.Artista;
import Objetos.Musica;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EstatisticasArtista extends JPanel {
    private RockstarInc rockstar;
    private Artista utilizadorAtual;
    private JLabel lblEstatisticas;
    private JPanel panelEstatisticas;
    private JScrollPane scroll,scrollTopMusicas,scrollTopArtistas;
    private JButton btnTops;
    private JDialog frmTops;
    private JPanel panelTopsMusicas,panelTopsArtistas, panelTops;
    public EstatisticasArtista(RockstarInc rockstar, Artista utilizadorAtual){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;

        mudarCorRGB(this,155,223,243);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(200),resizeHeight(600));


        Font font=new Font("SansSerif",Font.BOLD,12);
        Font font1=new Font("SansSerif",Font.BOLD,13);
        lblEstatisticas=new JLabel("Estatísticas da Rockstar:");
        lblEstatisticas.setFont(font1);
        lblEstatisticas.setBounds(resizeWidth(10),resizeHeight(0),resizeWidth(180),resizeHeight(30));
        add(lblEstatisticas);

        panelEstatisticas=new JPanel();

        panelEstatisticas.setLayout(new BoxLayout(panelEstatisticas,BoxLayout.Y_AXIS));

        panelEstatisticas.setFont(font);

        scroll=new JScrollPane(panelEstatisticas);
        scroll.setBounds(lblEstatisticas.getX(),lblEstatisticas.getY()+resizeHeight(30),resizeWidth(180),resizeHeight(270));
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scroll);

        updateEstatisticas();

        btnTops=new JButton("Ver Tops");
        btnTops.setBounds(scroll.getX()+resizeWidth(40),scroll.getY()+scroll.getHeight()+resizeHeight(15),resizeWidth(100),resizeHeight(30));
        btnTops.setFont(font);
        btnTops.setBorder(BorderFactory.createLineBorder(Color.black));
        btnTops.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFrmTops();
            }
        });
        add(btnTops);

    }

    public void updateEstatisticas(){
        panelEstatisticas.removeAll();
        panelEstatisticas.revalidate();

        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Total de utilizadores: "+rockstar.getTotalUtil()));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Total de músicas: "+rockstar.getTotalMusi()));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Valor total das músicas: "+rockstar.getTotalValor()+" €"));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Valor total das vendas: "+rockstar.getFaturacaoTotal()+" €"));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));

        int[] albunsPorGenero=rockstar.getAlbunsPorGenero();


        panelEstatisticas.add(new JLabel(" ----------------------------------------- "));

        Font font1=new Font("SansSerif",Font.BOLD,13);
        JLabel totalAlbuns=new JLabel(" Total de álbuns por género:");
        totalAlbuns.setFont(font1);
        panelEstatisticas.add(totalAlbuns);

        panelEstatisticas.add(new JLabel(" Rock: "+albunsPorGenero[0]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Rap: "+albunsPorGenero[1]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Pop: "+albunsPorGenero[2]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Clássica: "+albunsPorGenero[3]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Jazz: "+albunsPorGenero[4]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Metal: "+albunsPorGenero[5]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Popular: "+albunsPorGenero[6]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));
        panelEstatisticas.add(new JLabel(" Eletrónica: "+albunsPorGenero[7]));
        panelEstatisticas.add(Box.createRigidArea(new Dimension(0,3)));

    }

    private void setFrmTops(){
        frmTops=new JDialog();
        frmTops.setLayout(null);
        frmTops.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmTops.setSize(resizeWidth(350),resizeHeight(350));
        frmTops.setLocationRelativeTo(null);

        panelTops=new JPanel();
        panelTops.setBounds(0,0,frmTops.getWidth(),frmTops.getHeight());
        panelTops.setLayout(null);
        mudarCorRGB(panelTops,155,223,243);

        panelTopsMusicas=new JPanel();
        panelTopsMusicas.setLayout(new BoxLayout(panelTopsMusicas,BoxLayout.Y_AXIS));

        panelTopsArtistas=new JPanel();
        panelTopsArtistas.setLayout(new BoxLayout(panelTopsArtistas,BoxLayout.Y_AXIS));

        scrollTopArtistas=new JScrollPane(panelTopsArtistas);
        scrollTopMusicas=new JScrollPane(panelTopsMusicas);
        scrollTopMusicas.setBounds(resizeWidth(10),resizeHeight(40),resizeWidth(165),resizeHeight(290));
        scrollTopArtistas.setBounds(resizeWidth(185),scrollTopMusicas.getY(),scrollTopMusicas.getWidth(),scrollTopMusicas.getHeight());

        Font font = new Font("SansSerif", Font.BOLD, 12);

        ArrayList<Musica> topMusicas= utilizadorAtual.getTop5MusicasVendidas();
        ArrayList<Artista> topArtistas=rockstar.getTopArtistasVendidos();

        for (int i=0;i<topArtistas.size();i++){
            panelTopsArtistas.add(new JLabel((i+1)+" - "+topArtistas.get(i).getNome()+" - "+topArtistas.get(i).getVendasTotal()));
            panelTopsArtistas.add(Box.createRigidArea(new Dimension(0,3)));
            panelTopsMusicas.add(new JLabel((i+1)+" - "+topMusicas.get(i).getTitulo()+" - "+topMusicas.get(i).getVendas()));
            panelTopsMusicas.add(Box.createRigidArea(new Dimension(0,3)));
        }


        panelTops.add(scrollTopArtistas);
        panelTops.add(scrollTopMusicas);

        frmTops.add(panelTops);

        frmTops.setVisible(true);

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
