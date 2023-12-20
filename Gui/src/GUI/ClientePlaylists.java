package GUI;

import Objetos.Cliente;
import Objetos.Playlist;
import Objetos.RockstarInc;
import Objetos.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ClientePlaylists extends JPanel implements MouseListener, ActionListener {
    private ArrayList<JButton> btnListaPlaylists;
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
        this.rockstar=rockstar;

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

        btnListaPlaylists=new ArrayList<>();
        panelPlaylists = new JPanel();


        //BoxLayout é bom quando queremos organizar os elementos numa só direcçao, neste caso vamos adiciona-los na direcçao vertical
        panelPlaylists.setLayout(new BoxLayout(panelPlaylists,BoxLayout.Y_AXIS));

        printPlaylists(this.utilizadorAtual.getPlaylistsProprias());

        ///////////////////////Criação da frame de criar playlist/////////////////////////////////////
        frameCriarPlaylist=new JFrame();
        panelCriarPlaylist=new CriarPlaylistPanel(rockstar,utilizadorAtual,frameCriarPlaylist,this);
        frameCriarPlaylist.setLayout(null);
        frameCriarPlaylist.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameCriarPlaylist.setSize(resizeWidth(300),resizeHeight(250));
        frameCriarPlaylist.setLocationRelativeTo(null);
        frameCriarPlaylist.setResizable(false);
        panelCriarPlaylist.getBtnCriar().addActionListener(this);
        panelCriarPlaylist.getBtnCancelar().addActionListener(this);

        frameCriarPlaylist.setVisible(false);
        panelCriarPlaylist.setBounds(0,0,frameCriarPlaylist.getWidth(),frameCriarPlaylist.getHeight());
        frameCriarPlaylist.add(panelCriarPlaylist);


        scrollPanePlaylists = new JScrollPane(panelPlaylists);
        scrollPanePlaylists.setBounds(resizeWidth(10), lblPlaylists.getY() + resizeHeight(30), resizeWidth(175), resizeHeight(150));
        scrollPanePlaylists.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        add(scrollPanePlaylists);
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

        for (int i = 0; i < btnListaPlaylists.size(); i++) {
            if (clicked == btnListaPlaylists.get(i)) {
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
        else if(clicked==panelCriarPlaylist.getBtnCriar()) {

            if (panelCriarPlaylist.getCheckVisibilidade().isSelected()) {
                Playlist playlist=new Playlist(panelCriarPlaylist.getTxtNome().getText(), true);
                rockstar.addPlaylist(playlist);
                utilizadorAtual.addPlaylist(playlist);
                printPlaylists(utilizadorAtual.getPlaylistsProprias());
                frameCriarPlaylist.dispatchEvent(new WindowEvent(frameCriarPlaylist,WindowEvent.WINDOW_CLOSING));
                JOptionPane.showMessageDialog(frameCriarPlaylist,"A playlist "+playlist.getNome()+" foi criada");

            }
            else{
                Playlist playlist=new Playlist(panelCriarPlaylist.getTxtNome().getText(), false);
                rockstar.addPlaylist(playlist);
                utilizadorAtual.addPlaylist(playlist);
                printPlaylists(utilizadorAtual.getPlaylistsProprias());
                frameCriarPlaylist.dispatchEvent(new WindowEvent(frameCriarPlaylist,WindowEvent.WINDOW_CLOSING));
                JOptionPane.showMessageDialog(frameCriarPlaylist,"A playlist "+playlist.getNome()+" foi criada");
            }
        }
        else if (clicked==panelCriarPlaylist.getBtnCancelar()){
            frameCriarPlaylist.dispatchEvent(new WindowEvent(frameCriarPlaylist,WindowEvent.WINDOW_CLOSING));
        }
    }

    public void printPlaylists(ArrayList<Playlist> playlists){
        panelPlaylists.removeAll();
        btnListaPlaylists.clear();
        panelPlaylists.revalidate();

        for (int i = 0; i < playlists.size(); i++) {

            Font font = new Font("SansSerif", Font.BOLD, 12);
            btnListaPlaylists.add(new JButton(playlists.get(i).getNome()));
            btnListaPlaylists.get(i).setFont(font);
            btnListaPlaylists.get(i).setBorderPainted(false);
            mudarCorRGB(btnListaPlaylists.get(i),238,238,238);
            btnListaPlaylists.get(i).addMouseListener(this);

            System.out.println(playlists.get(i).getNome());
            panelPlaylists.add(btnListaPlaylists.get(i));
        }
    }
}

