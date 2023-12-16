package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientePlaylists extends JPanel implements MouseListener {
    private String[] list;
    private JButton[] btnListaPlaylists;
    private JPanel panelPlaylists;
    private JScrollPane scrollPanePlaylists;
    private JLabel lblPlaylists;
    private JButton btnCriarPlaylist, btnCriadorAI, btnBiblioteca;

    public ClientePlaylists() {
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

        list = new String[10];
        list[0] = "Playlist 1";
        list[1] = "Playlist 2";
        list[2] = "Playlist 3";
        list[3] = "Playlist 4";
        list[4] = "Playlist 5";
        list[5] = "Playlist 6";
        list[6] = "Playlist 7";
        list[7] = "Playlist 8";
        list[8] = "Playlist 9";
        list[9] = "Playlist 10";

        panelPlaylists = new JPanel();

        btnListaPlaylists = new JButton[list.length];

        //BoxLayout é bom quando queremos organizar os elementos numa só direcçao, neste caso vamos adiciona-los na direcçao vertical
        panelPlaylists.setLayout(new BoxLayout(panelPlaylists,BoxLayout.Y_AXIS));

        for (int i = 0; i < btnListaPlaylists.length; i++) {

            btnListaPlaylists[i] = new JButton(list[i]);
            btnListaPlaylists[i].setFont(font2);
            btnListaPlaylists[i].setBorderPainted(false);
            mudarCorRGB(btnListaPlaylists[i],238,238,238);
            btnListaPlaylists[i].addMouseListener(this);

            panelPlaylists.add(btnListaPlaylists[i]);

        }

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
}

