package GUI;

import Objetos.Cliente;
import Objetos.Playlist;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class CriarPlaylistPanel extends JPanel implements ActionListener {
    private JTextField txtNome;
    private JLabel lblNome;
    private JCheckBox checkVisibilidade;
    private RockstarInc rockstar;
    private Cliente utilizadorAtual;
    private JButton btnCriar,btnCancelar;
    private JFrame frame;
    private JPanel panelPlaylists;
    public CriarPlaylistPanel(RockstarInc rockstar, Cliente utilizadorAtual,JFrame frame,JPanel panelPlaylists){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;
        this.frame=frame;
        this.panelPlaylists=panelPlaylists;

        setLayout(null);
        Font font=new Font("SansSerif",Font.BOLD,12);
        lblNome=new JLabel("Nome da Playlist");
        lblNome.setFont(font);
        lblNome.setBounds(resizeWidth(20),resizeHeight(20),resizeWidth(150),resizeHeight(25));
        add(lblNome);
        txtNome=new JTextField(20);
        txtNome.setBounds(lblNome.getX(),lblNome.getY()+lblNome.getHeight()+resizeHeight(2),resizeWidth(150),resizeHeight(40));
        add(txtNome);
        checkVisibilidade=new JCheckBox("Pública");
        checkVisibilidade.setBounds(txtNome.getX(),txtNome.getY()+txtNome.getHeight()+resizeHeight(5),resizeWidth(100),resizeHeight(40));
        add(checkVisibilidade);
        btnCriar=new JButton("Criar");
        btnCancelar=new JButton("Cancelar");
        btnCancelar.setBounds(checkVisibilidade.getX(),checkVisibilidade.getY()+checkVisibilidade.getHeight()+resizeHeight(5),resizeWidth(100),resizeHeight(30));
        btnCriar.setBounds(btnCancelar.getX()+btnCancelar.getWidth()+resizeWidth(10),btnCancelar.getY(), btnCancelar.getWidth(), btnCancelar.getHeight());
        btnCriar.setFont(font);
        btnCancelar.setFont(font);
        btnCriar.addActionListener(this);
        btnCancelar.addActionListener(this);
        add(btnCancelar);
        add(btnCriar);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if(clicked==btnCriar) {
            if (checkVisibilidade.isSelected()) {
                Playlist playlist=new Playlist(txtNome.getText(), true);
                rockstar.addPlaylist(playlist);
                utilizadorAtual.addPlaylist(playlist);
                JOptionPane.showMessageDialog(this,"A playlist "+playlist.getNome()+" foi criada");
                frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
                JButton btnListaPlaylists = new JButton(playlist.getNome());
                btnListaPlaylists.setBorderPainted(false);
                panelPlaylists.add(btnListaPlaylists);

            }
            else{
                Playlist playlist=new Playlist(txtNome.getText(), false);
                rockstar.addPlaylist(playlist);
                utilizadorAtual.addPlaylist(playlist);
                JOptionPane.showMessageDialog(this,"A playlist "+playlist.getNome()+" foi criada");
                frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
            }
        }
        else if (clicked==btnCancelar){
            frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
        }
    }
}


