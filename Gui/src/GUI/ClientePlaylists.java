package GUI;

import Objetos.Cliente;
import Objetos.Playlist;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ClientePlaylists extends JPanel implements MouseListener, ActionListener {
    private ArrayList<Playlist> playlists;
    private JButton[] btnListaPlaylists;
    private JPanel panelPlaylists;
    private JScrollPane scrollPanePlaylists;
    private JLabel lblPlaylists;
    private JButton btnCriarPlaylist, btnCriadorAI, btnBiblioteca;
    private Cliente utilizadorAtual;
    private CriarPlaylistPanel panelCriarPlaylist;
    private JFrame frameCriarPlaylist;

    private RockstarInc rockstar;

    public ClientePlaylists(RockstarInc rockstar,Cliente utilizadorAtual) {
        this.utilizadorAtual=utilizadorAtual;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(200), resizeHeight(300));
        setLayout(null);

        mudarCorRGB(this,155,223,243);

        Font font = new Font("SansSerif", Font.BOLD, 12);
        lblPlaylists = new JLabel("Playlists:");
        lblPlaylists.setFont(font);
        lblPlaylists.setBounds(resizeWidth(10), resizeHeight(0), resizeWidth(90), resizeHeight(30));
        add(lblPlaylists);

        Font font1 = new Font("SansSerif", Font.BOLD, 11);
        btnCriarPlaylist = new JButton("Criar Playlist");
        btnCriarPlaylist.setFont(font1);
        btnCriarPlaylist.setBounds(lblPlaylists.getX(), resizeHeight(250), resizeWidth(120), resizeHeight(25));
        btnCriarPlaylist.addActionListener(this);
        add(btnCriarPlaylist);


        btnCriadorAI = new JButton("Criar Playlist AI");
        btnCriadorAI.setFont(font1);
        btnCriadorAI.setBounds(btnCriarPlaylist.getX(), btnCriarPlaylist.getY() + resizeHeight(45), btnCriarPlaylist.getWidth(), btnCriarPlaylist.getHeight());
        add(btnCriadorAI);

        Font font2 = new Font("SansSerif", Font.BOLD, 12);
        btnBiblioteca = new JButton("Biblioteca");
        btnBiblioteca.setFont(font2);
        btnBiblioteca.setBounds(btnCriadorAI.getX(), btnCriadorAI.getY() + resizeHeight(165), resizeWidth(110), resizeHeight(25));
        add(btnBiblioteca);

        ////////////////////// Playlists Ficticias///////////////////////////////////

        playlists = utilizadorAtual.getPlaylistsProprias();

        panelPlaylists = new JPanel();

        btnListaPlaylists = new JButton[playlists.size()];

        //BoxLayout é bom quando queremos organizar os elementos numa só direcçao, neste caso vamos adiciona-los na direcçao vertical
        panelPlaylists.setLayout(new BoxLayout(panelPlaylists,BoxLayout.Y_AXIS));

        for (int i = 0; i < btnListaPlaylists.length; i++) {

            btnListaPlaylists[i] = new JButton(playlists.get(i).getNome());
            btnListaPlaylists[i].setFont(font2);
            btnListaPlaylists[i].setBorderPainted(false);
            mudarCorRGB(btnListaPlaylists[i],238,238,238);
            btnListaPlaylists[i].addMouseListener(this);

            panelPlaylists.add(btnListaPlaylists[i]);
        }

        frameCriarPlaylist=new JFrame();
        panelCriarPlaylist=new CriarPlaylistPanel(rockstar,utilizadorAtual,frameCriarPlaylist,panelPlaylists);
        frameCriarPlaylist.setLayout(null);
        frameCriarPlaylist.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameCriarPlaylist.setSize(resizeWidth(300),resizeHeight(250));
        frameCriarPlaylist.setLocationRelativeTo(null);
        frameCriarPlaylist.setResizable(false);

        frameCriarPlaylist.setVisible(false);
        panelCriarPlaylist.setBounds(0,0,frameCriarPlaylist.getWidth(),frameCriarPlaylist.getHeight());
        frameCriarPlaylist.add(panelCriarPlaylist);

        scrollPanePlaylists = new JScrollPane(panelPlaylists);
        scrollPanePlaylists.setBounds(resizeWidth(10), lblPlaylists.getY() + resizeHeight(30), resizeWidth(175), resizeHeight(150));
        scrollPanePlaylists.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        add(scrollPanePlaylists);

        ////////////////////// Playlists Ficticias////////////////////////////////
    }

    private int resizeWidth(int width) {
        Dimension ecra = Toolkit.getDefaultToolkit().getScreenSize();
        int widthAtualizada;
        widthAtualizada = (int) (width * ecra.getWidth()) / 1536;
        return widthAtualizada;
    }

    private int resizeHeight(int height) {
        Dimension ecra = Toolkit.getDefaultToolkit().getScreenSize();
        int heightAtualizada;
        heightAtualizada = (int) (height * ecra.getHeight()) / 864;
        return heightAtualizada;
    }


    public JButton getBtnBiblioteca() {
        return btnBiblioteca;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object clicked = e.getSource();

        for (int i = 0; i < btnListaPlaylists.length; i++) {
            if (clicked == btnListaPlaylists[i]) {
                System.out.println("Click na música "+(i+1));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    private void mudarCorRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnCriarPlaylist){
            frameCriarPlaylist.setVisible(true);
        }
    }
}

