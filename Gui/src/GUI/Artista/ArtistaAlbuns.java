package GUI.Artista;

import Objetos.Album;
import Objetos.Artista;
import Objetos.Musica;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ArtistaAlbuns extends JPanel implements ActionListener {
    private RockstarInc rockstar;
    private Artista utilizadorAtual;
    private JLabel lblAlbuns,lblNomeAlbum,lblGenero,lblNomeMusica,lblGeneroMusica,lblAlbum,lblPreco;
    private JButton btnCriarAlbum, btnCriarMusica, btnBiblioteca,btnCancelarCriarAlbum,btnCriarAlbum2,btnCancelarCriarMusica,btnCriarMusica2;
    private JPanel panelAlbuns,panelCriarAlbum, panelCriarMusica;
    private ArrayList<JButton> btnListaAlbuns;
    private JScrollPane scrollPaneAlbuns;
    private JFrame frmCriarAlbum,frmCriarMusica;
    private JTextField txtNomeAlbum,txtNomeMusica,txtPreco;
    private JComboBox cmbGenero,cmbGeneroMusica,cmbAlbum;
    private JCheckBox checkVisibilidade;
    private TabelaArtista tabelaArtista;
    private InterfaceArtista interfaceArtista;
    private EstatisticasArtista estatisticasArtista;
    private JFrame frame;


    public ArtistaAlbuns(RockstarInc rockstar, Artista utilizadorAtual, InterfaceArtista interfaceArtista, EstatisticasArtista estatisticasArtista, JFrame frame){
        this.rockstar=rockstar;
        this.utilizadorAtual =utilizadorAtual;
        this.interfaceArtista=interfaceArtista;
        this.estatisticasArtista=estatisticasArtista;
        this.frame=frame;

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(200), resizeHeight(300));
        setLayout(null);

        mudarCorRGB(this,155,223,243);

        Font font = new Font("SansSerif", Font.BOLD, 12);
        lblAlbuns = new JLabel("Álbuns:");
        lblAlbuns.setFont(font);
        lblAlbuns.setBounds(resizeWidth(10), resizeHeight(0), resizeWidth(90), resizeHeight(30));
        add(lblAlbuns);

        Font font1 = new Font("SansSerif", Font.BOLD, 11);
        btnCriarAlbum = new JButton("Criar Album");
        btnCriarAlbum.setFont(font1);
        btnCriarAlbum.setBounds(lblAlbuns.getX(), resizeHeight(250), resizeWidth(120), resizeHeight(25));
        btnCriarAlbum.addActionListener(this);
        add(btnCriarAlbum);

        btnCriarMusica = new JButton("Criar Música");
        btnCriarMusica.setFont(font1);
        btnCriarMusica.setBounds(btnCriarAlbum.getX(), btnCriarAlbum.getY() + resizeHeight(45), btnCriarAlbum.getWidth(), btnCriarAlbum.getHeight());
        btnCriarMusica.addActionListener(this);
        add(btnCriarMusica);

        Font font2 = new Font("SansSerif", Font.BOLD, 12);
        btnBiblioteca = new JButton("As minhas Músicas");
        btnBiblioteca.setFont(font2);
        btnBiblioteca.setBounds(btnCriarMusica.getX(), btnCriarMusica.getY() + resizeHeight(165), resizeWidth(140), resizeHeight(30));
        btnBiblioteca.addActionListener(this);
        add(btnBiblioteca);

        btnListaAlbuns=new ArrayList<>();
        panelAlbuns = new JPanel();

        //BoxLayout é bom quando queremos organizar os elementos numa só direcçao, neste caso vamos adiciona-los na direcçao vertical
        panelAlbuns.setLayout(new BoxLayout(panelAlbuns,BoxLayout.Y_AXIS));

        printAlbuns(this.utilizadorAtual.getAlbuns());
        //System.out.println(this.utilizadorAtual.getAlbuns().get(0).getNome());

        scrollPaneAlbuns = new JScrollPane(panelAlbuns);
        scrollPaneAlbuns.setBounds(resizeWidth(10), lblAlbuns.getY() + resizeHeight(30), resizeWidth(175), resizeHeight(150));
        scrollPaneAlbuns.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(scrollPaneAlbuns);

        ///////////////////////////////////////PANEL DE CRIAÇÃO DE ALBUM//////////////////////////////
        frmCriarAlbum=new JFrame("Criação de Album");
        panelCriarAlbum=new JPanel();

        frmCriarAlbum.setLayout(null);
        frmCriarAlbum.setSize(resizeWidth(320),resizeHeight(220));
        frmCriarAlbum.setLocationRelativeTo(null);
        frmCriarAlbum.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmCriarAlbum.setResizable(false);
        panelCriarAlbum.setBounds(0,0,frmCriarAlbum.getWidth(),frmCriarAlbum.getHeight());
        frmCriarAlbum.setVisible(false);

        panelCriarAlbum.setLayout(null);

        lblNomeAlbum=new JLabel("Nome do Álbum");
        lblNomeAlbum.setFont(font);
        lblNomeAlbum.setBounds(resizeWidth(5),resizeHeight(5),resizeWidth(120),resizeHeight(25));
        panelCriarAlbum.add(lblNomeAlbum);

        txtNomeAlbum =new JTextField(30);
        txtNomeAlbum.setFont(font);
        txtNomeAlbum.setBounds(lblNomeAlbum.getX()+lblNomeAlbum.getWidth(),lblNomeAlbum.getY(),resizeWidth(100),resizeHeight(30));
        panelCriarAlbum.add(txtNomeAlbum);

        lblGenero=new JLabel("Género");
        lblGenero.setFont(font);
        lblGenero.setBounds(lblNomeAlbum.getX(),lblNomeAlbum.getY()+lblNomeAlbum.getHeight()+resizeHeight(10),resizeWidth(50),resizeHeight(30));
        panelCriarAlbum.add(lblGenero);

        String[] generos={"Rock","Pop","Rap","Clássica","Jazz","Metal","Popular","Eletrónica"};
        cmbGenero=new JComboBox(generos);
        cmbGenero.setFont(font);
        cmbGenero.setBounds(lblGenero.getX()+lblGenero.getWidth()+resizeWidth(4),lblGenero.getY(),resizeWidth(100),resizeHeight(30));
        panelCriarAlbum.add(cmbGenero);

        btnCancelarCriarAlbum=new JButton("Cancelar");
        btnCancelarCriarAlbum.setFont(font);
        btnCancelarCriarAlbum.setBounds(lblGenero.getX(),lblGenero.getY()+lblGenero.getHeight()+resizeHeight(6),resizeWidth(100),resizeHeight(30));
        btnCancelarCriarAlbum.addActionListener(this);
        panelCriarAlbum.add(btnCancelarCriarAlbum);

        btnCriarAlbum2=new JButton("Criar");
        btnCriarAlbum2.setFont(font);
        btnCriarAlbum2.setBounds(btnCancelarCriarAlbum.getX()+btnCancelarCriarAlbum.getWidth()+resizeWidth(10),btnCancelarCriarAlbum.getY(),btnCancelarCriarAlbum.getWidth(),btnCancelarCriarAlbum.getHeight());
        btnCriarAlbum2.addActionListener(this);
        panelCriarAlbum.add(btnCriarAlbum2);

        frmCriarAlbum.add(panelCriarAlbum);

        frmCriarAlbum.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });

        //////////////////////PAINEL DE CRIAÇÃO DE MUSICA////////////////////////////////////
        frmCriarMusica=new JFrame("Criação de Música");
        panelCriarMusica=new JPanel();

        frmCriarMusica.setLayout(null);
        frmCriarMusica.setSize(resizeWidth(320),resizeHeight(250));
        frmCriarMusica.setLocationRelativeTo(null);
        frmCriarMusica.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmCriarMusica.setResizable(false);
        panelCriarMusica.setBounds(0,0,frmCriarAlbum.getWidth(),frmCriarAlbum.getHeight());
        frmCriarMusica.setVisible(false);

        panelCriarMusica.setLayout(null);

        lblNomeMusica=new JLabel("Nome da Música");
        lblNomeMusica.setFont(font);
        lblNomeMusica.setBounds(resizeWidth(5),resizeHeight(5),resizeWidth(110),resizeHeight(25));
        panelCriarMusica.add(lblNomeMusica);

        txtNomeMusica =new JTextField(30);
        txtNomeMusica.setFont(font);
        txtNomeMusica.setBounds(lblNomeMusica.getX()+lblNomeAlbum.getWidth(),lblNomeAlbum.getY(),resizeWidth(100),resizeHeight(30));
        panelCriarMusica.add(txtNomeMusica);

        lblGeneroMusica=new JLabel("Género");
        lblGeneroMusica.setFont(font);
        lblGeneroMusica.setBounds(lblNomeMusica.getX(),lblNomeMusica.getY()+lblNomeMusica.getHeight()+resizeHeight(10),resizeWidth(50),resizeHeight(30));
        panelCriarMusica.add(lblGeneroMusica);

        cmbGeneroMusica=new JComboBox(generos);
        cmbGeneroMusica.setFont(font);
        cmbGeneroMusica.setBounds(lblGeneroMusica.getX()+lblGeneroMusica.getWidth()+resizeWidth(4),lblGeneroMusica.getY(),resizeWidth(100),resizeHeight(30));
        panelCriarMusica.add(cmbGeneroMusica);

        lblAlbuns=new JLabel("Álbum");
        lblAlbuns.setFont(font);
        lblAlbuns.setBounds(lblGeneroMusica.getX(),lblGeneroMusica.getY()+lblGeneroMusica.getHeight()+resizeHeight(10),resizeWidth(50),resizeHeight(30));
        panelCriarMusica.add(lblAlbuns);

        cmbAlbum=new JComboBox(this.utilizadorAtual.titulosAlbuns());
        cmbAlbum.setFont(font);
        cmbAlbum.setBounds(lblAlbuns.getX()+lblAlbuns.getWidth()+resizeWidth(4),lblAlbuns.getY(),resizeWidth(125),resizeHeight(30));
        panelCriarMusica.add(cmbAlbum);

        lblPreco=new JLabel("Preço");
        lblPreco.setFont(font);
        lblPreco.setBounds(lblAlbuns.getX(),lblAlbuns.getY()+lblAlbuns.getHeight()+resizeHeight(10),resizeWidth(50),resizeHeight(30));
        panelCriarMusica.add(lblPreco);

        txtPreco=new JTextField(20);
        txtPreco.setFont(font);
        txtPreco.setBounds(lblPreco.getX()+lblPreco.getWidth()+resizeWidth(1),lblPreco.getY(),resizeWidth(50),resizeHeight(30));
        panelCriarMusica.add(txtPreco);

        checkVisibilidade=new JCheckBox("Pública");
        checkVisibilidade.setFont(font);
        checkVisibilidade.setBounds(txtPreco.getX()+txtPreco.getWidth()+resizeWidth(5),txtPreco.getY(),resizeWidth(100),resizeHeight(30));
        panelCriarMusica.add(checkVisibilidade);

        btnCancelarCriarMusica=new JButton("Cancelar");
        btnCancelarCriarMusica.setFont(font);
        btnCancelarCriarMusica.setBounds(lblPreco.getX()+resizeWidth(30),lblPreco.getY()+lblPreco.getHeight()+resizeHeight(10),resizeWidth(100),resizeHeight(30));
        btnCancelarCriarMusica.addActionListener(this);
        panelCriarMusica.add(btnCancelarCriarMusica);

        btnCriarMusica2=new JButton("Criar");
        btnCriarMusica2.setFont(font);
        btnCriarMusica2.setBounds(btnCancelarCriarMusica.getX()+btnCancelarCriarMusica.getWidth()+resizeWidth(20),btnCancelarCriarMusica.getY(),btnCancelarCriarMusica.getWidth(),btnCancelarCriarMusica.getHeight());
        btnCriarMusica2.addActionListener(this);
        panelCriarMusica.add(btnCriarMusica2);

        frmCriarMusica.add(panelCriarMusica);

        frmCriarMusica.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();
        if(clicked==btnCriarAlbum){
            frmCriarAlbum.setLocationRelativeTo(null);
            frame.setEnabled(false);
            frmCriarAlbum.setVisible(true);
        }
        else if(clicked==btnCriarAlbum2) {
            if (!txtNomeAlbum.getText().equals("")) {
                if (utilizadorAtual.verificarAlbum(txtNomeAlbum.getText())) {
                    txtNomeAlbum.setText("");
                    JOptionPane.showMessageDialog(frmCriarAlbum, "Já tem um Álbum com este nome");
                } else {
                    String comboGenero = (String) cmbGenero.getSelectedItem();
                    rockstar.addAlbum(new Album(txtNomeAlbum.getText(), comboGenero, utilizadorAtual));
                    printAlbuns(utilizadorAtual.getAlbuns());
                    tabelaArtista.updatePopMenuArtista();
                    estatisticasArtista.updateEstatisticas();
                    tabelaArtista.setPanelAlbuns(this);
                    txtNomeAlbum.setText("");
                    frame.setEnabled(true);
                    frmCriarAlbum.dispatchEvent(new WindowEvent(frmCriarAlbum, WindowEvent.WINDOW_CLOSING));
                    updateComboAlbuns(utilizadorAtual.titulosAlbuns());
                }
            }

        }
        else if(clicked==btnCancelarCriarAlbum){
            txtNomeAlbum.setText("");
            frame.setEnabled(true);
            frmCriarAlbum.dispatchEvent(new WindowEvent(frmCriarAlbum,WindowEvent.WINDOW_CLOSING));
        }
        else if(clicked==btnCriarMusica){
            frmCriarMusica.setLocationRelativeTo(null);
            checkVisibilidade.setSelected(true);
            frame.setEnabled(false);
            frmCriarMusica.setVisible(true);
        }
        else if(clicked==btnCriarMusica2) {
            try {
                if (!txtNomeMusica.getText().equals("") && txtPreco.getText().matches("\\d+(\\.\\d+)*")) {
                    if (!utilizadorAtual.verificarMusica(txtNomeMusica.getText())) {
                        String comboGenero = (String) cmbGenero.getSelectedItem();
                        if (cmbAlbum.getSelectedIndex() == 0) {
                            rockstar.addMusica(new Musica(txtNomeMusica.getText(), utilizadorAtual, comboGenero, Double.valueOf(txtPreco.getText()),checkVisibilidade.isSelected()));
                            tabelaArtista.printMusicas(utilizadorAtual.getTotalMusicas());
                            estatisticasArtista.updateEstatisticas();
                            interfaceArtista.setLblTabela("As minhas Músicas:");
                            txtNomeMusica.setText("");
                            txtPreco.setText("");
                            checkVisibilidade.setSelected(true);
                            frame.setEnabled(true);
                            frmCriarMusica.dispatchEvent(new WindowEvent(frmCriarMusica,WindowEvent.WINDOW_CLOSING));

                        } else {
                            rockstar.addMusica(new Musica(txtNomeMusica.getText(), utilizadorAtual, comboGenero, Double.valueOf(txtPreco.getText()), utilizadorAtual.getAlbuns().get(cmbAlbum.getSelectedIndex()-1),checkVisibilidade.isSelected()));
                            tabelaArtista.printMusicas(utilizadorAtual.getAlbuns().get(cmbAlbum.getSelectedIndex()-1).getMusicas());
                            estatisticasArtista.updateEstatisticas();
                            interfaceArtista.setLblTabela("Album: "+utilizadorAtual.getAlbuns().get(cmbAlbum.getSelectedIndex()-1).getNome());
                            txtNomeMusica.setText("");
                            txtPreco.setText("");
                            checkVisibilidade.setSelected(true);
                            frame.setEnabled(true);
                            frmCriarMusica.dispatchEvent(new WindowEvent(frmCriarMusica,WindowEvent.WINDOW_CLOSING));
                        }
                    } else {
                        txtNomeMusica.setText("");
                        JOptionPane.showMessageDialog(frmCriarMusica, "Já tem uma música com este nome");
                    }
                } else {
                    JOptionPane.showMessageDialog(frmCriarMusica, "Os dados inseridos são inválidos");
                }
            }catch(NumberFormatException f){
                txtPreco.setText("");
                JOptionPane.showMessageDialog(frmCriarMusica,"Os dados inseridos são inválidos");
            }

        }
        else if(clicked==btnCancelarCriarMusica){
            txtNomeMusica.setText("");
            frame.setEnabled(true);
            frmCriarMusica.dispatchEvent(new WindowEvent(frmCriarMusica,WindowEvent.WINDOW_CLOSING));
        }
        else if(clicked==btnBiblioteca){
            interfaceArtista.setLblTabela("As minhas Músicas:");
            tabelaArtista.printMusicas(utilizadorAtual.getTotalMusicas());
            tabelaArtista.setAlbum(null);
        }
    }

    public void updateComboAlbuns(String [] titulosAlbuns ){
        cmbAlbum.removeAllItems();
        for (int i=0;i<titulosAlbuns.length;i++){
            cmbAlbum.addItem(titulosAlbuns[i]);
        }
    }

    public void printAlbuns(ArrayList<Album> albuns){
        panelAlbuns.removeAll();
        btnListaAlbuns.clear();
        panelAlbuns.revalidate();
        panelAlbuns.repaint();

        for (int i = 0; i < albuns.size(); i++) {

            Font font = new Font("SansSerif", Font.BOLD, 12);
            btnListaAlbuns.add(new JButton(albuns.get(i).getNome()));
            btnListaAlbuns.get(i).setFont(font);
            btnListaAlbuns.get(i).setBorderPainted(false);
            mudarCorRGB(btnListaAlbuns.get(i),238,238,238);

            System.out.println(albuns.get(i).getNome());
            panelAlbuns.add(btnListaAlbuns.get(i));
        }
    }

    public void setTabelaArtista(TabelaArtista tabelaArtista) {
        this.tabelaArtista = tabelaArtista;
    }

    public ArrayList<JButton> getBtnListaAlbuns() {
        return btnListaAlbuns;
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
