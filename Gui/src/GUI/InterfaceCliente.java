package GUI;

import Objetos.Cliente;
import Objetos.RockstarInc;
import Objetos.Utilizador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class InterfaceCliente extends JPanel implements ActionListener {
    private ClientePlaylists panelPlaylists;
    private PanelCarrinho panelCarrinho;
    private PesquisaPanel filtros;
    private JLabel lblUser,lblSaldo,lblTabela;
    private JButton btnLoja, btnCarregar,btnCancelar,btnCarregar2;
    private JRadioButton radioBtnMusicas,radioBtnPlaylists;
    private ButtonGroup grupoRadioBtn;
    private JButton btnRemoverPlaylist;
    private TabelaCliente tabelaCliente;
    private JFrame frmCarregamento;
    private JPanel panelCarregamento;
    private RockstarInc rockstar;
    private Cliente utilizadorAtual;

    public InterfaceCliente(RockstarInc rockstar, Cliente utilizadorAtual){
        this.rockstar=rockstar;
        this.utilizadorAtual=utilizadorAtual;

        mudarCorRGB(this,51,153,153);
        setLayout(null);



        panelPlaylists=new ClientePlaylists(rockstar,utilizadorAtual);
        panelPlaylists.setBounds(resizeWidth(10),resizeHeight(50),resizeWidth(200),resizeHeight(500));
        add(panelPlaylists);

        tabelaCliente=new TabelaCliente( rockstar, utilizadorAtual,panelPlaylists,this);
        panelPlaylists.setTabelaCliente(tabelaCliente);
        tabelaCliente.setBounds(panelPlaylists.getX()+panelPlaylists.getWidth()+resizeWidth(25),panelPlaylists.getY(),resizeWidth(465),panelPlaylists.getHeight());
        add(tabelaCliente);

        panelPlaylists.setTabelaPanelAi(tabelaCliente);

        panelCarrinho=new PanelCarrinho();
        panelCarrinho.setBounds(resizeWidth(725),panelPlaylists.getY(),resizeWidth(200),resizeHeight(270));
        add(panelCarrinho);

        setVisible(true);

        filtros=new PesquisaPanel();
        filtros.setBounds(panelCarrinho.getX(),panelCarrinho.getY()+panelCarrinho.getHeight()+resizeHeight(20),panelCarrinho.getWidth(),resizeHeight(100));
        add(filtros);

        Font font4=new Font("SansSerif",Font.BOLD ,13);
        lblUser=new JLabel("Cliente: "+utilizadorAtual.getUsername());
        lblUser.setFont(font4);
        lblUser.setBounds(panelPlaylists.getX()+resizeWidth(10),resizeHeight(20),resizeWidth(150),resizeHeight(20));
        add(lblUser);

        Font font=new Font("SansSerif",Font.BOLD ,12);
        btnLoja=new JButton("Loja");
        btnLoja.setFont(font);
        btnLoja.setBounds(panelPlaylists.getX()+panelPlaylists.getBtnBiblioteca().getX(),panelPlaylists.getY()+panelPlaylists.getHeight()+resizeHeight(15),resizeWidth(110),resizeHeight(30));
        btnLoja.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnLoja.addActionListener(this);
        add(btnLoja);

        Font font3=new Font("SansSerif",Font.BOLD,13);
        lblTabela=new JLabel("Biblioteca de músicas:");
        lblTabela.setFont(font3);
        lblTabela.setBounds(tabelaCliente.getX(),lblUser.getY(),resizeWidth(250),lblUser.getHeight());
        add(lblTabela);

        Font font2=new Font("SansSerif",Font.BOLD | Font.ITALIC,13);
        radioBtnMusicas=new JRadioButton("Músicas");
        radioBtnPlaylists=new JRadioButton("Playlists");
        radioBtnMusicas.setFont(font2);
        radioBtnPlaylists.setFont(font2);
        radioBtnMusicas.setOpaque(false);
        radioBtnPlaylists.setOpaque(false);
        radioBtnMusicas.setBounds(lblTabela.getX()+resizeWidth(70),lblTabela.getY(),resizeWidth(80),resizeHeight(25));
        radioBtnPlaylists.setBounds(radioBtnMusicas.getX()+radioBtnMusicas.getWidth()+resizeWidth(10),radioBtnMusicas.getY(),resizeWidth(80),resizeHeight(25));
        radioBtnMusicas.setSelected(true);
        add(radioBtnMusicas);
        add(radioBtnPlaylists);
        radioBtnMusicas.setVisible(false);
        radioBtnPlaylists.setVisible(false);
        radioBtnMusicas.addActionListener(this);
        radioBtnPlaylists.addActionListener(this);

        grupoRadioBtn=new ButtonGroup();
        grupoRadioBtn.add(radioBtnMusicas);
        grupoRadioBtn.add(radioBtnPlaylists);

        btnRemoverPlaylist=new JButton("Remover Playlist");
        btnRemoverPlaylist.setFont(font);
        btnRemoverPlaylist.setBounds(panelCarrinho.getX()-resizeWidth(155),btnLoja.getY(),resizeWidth(140),resizeHeight(30));
        btnRemoverPlaylist.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(btnRemoverPlaylist);

        lblSaldo=new JLabel("Saldo: 20.00€");
        lblSaldo.setFont(font);
        lblSaldo.setBounds(panelCarrinho.getX(),lblUser.getY(),resizeWidth(80),resizeHeight(20));
        add(lblSaldo);

        btnCarregar=new JButton("Carregar");
        btnCarregar.setBounds(lblSaldo.getX()+lblSaldo.getWidth()+resizeWidth(5),lblSaldo.getY()-resizeHeight(2),resizeWidth(85),resizeHeight(25));
        btnCarregar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(btnCarregar);
        btnCarregar.addActionListener(this);

        panelPlaylists.getBtnBiblioteca().addActionListener(this);

        //////////////////////////////// JFrame CARREGAMENTO////////////////////////////////////
        frmCarregamento =new JFrame();
        frmCarregamento.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmCarregamento.setLayout(null);
        frmCarregamento.setBounds(resizeWidth(1000),resizeHeight(150),resizeWidth(240),resizeHeight(180));
        frmCarregamento.setResizable(false);

        panelCarregamento=new JPanel();
        panelCarregamento.setLayout(null);

        JLabel lblCarregarSaldo=new JLabel("Carregar Saldo");
        lblCarregarSaldo.setFont(font);

        JLabel lblValor=new JLabel("Valor :");
        lblValor.setFont(font);
        JTextField txtValor=new JTextField(20);
        txtValor.setFont(font);

        btnCancelar=new JButton("Cancelar");
        btnCancelar.setFont(font);

        btnCarregar2=new JButton("Carregar");
        btnCarregar2.setFont(font);

        lblCarregarSaldo.setBounds(resizeWidth(20),resizeHeight(5),resizeWidth(100),resizeHeight(25));
        panelCarregamento.add(lblCarregarSaldo);

        lblValor.setBounds(lblCarregarSaldo.getX(),lblCarregarSaldo.getY()+lblCarregarSaldo.getHeight()+resizeHeight(20),resizeWidth(40),resizeHeight(25));
        panelCarregamento.add(lblValor);

        txtValor.setBounds(lblValor.getX()+lblValor.getWidth()+resizeWidth(1),lblValor.getY()+resizeHeight(1),resizeWidth(50),resizeHeight(21));
        panelCarregamento.add(txtValor);

        btnCancelar.setBounds(lblValor.getX(),lblValor.getY()+lblValor.getHeight()+resizeHeight(20),resizeWidth(85),resizeHeight(25));
        panelCarregamento.add(btnCancelar);

        btnCarregar2.setBounds(btnCancelar.getX()+btnCancelar.getWidth()+resizeWidth(15),btnCancelar.getY(),btnCancelar.getWidth(),btnCancelar.getHeight());
        panelCarregamento.add(btnCarregar2);

        mudarCorRGB(panelCarregamento,51,153,153);

        panelCarregamento.setBounds(resizeWidth(0),resizeHeight(0), frmCarregamento.getWidth(), frmCarregamento.getHeight());

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmCarregamento.dispatchEvent(new WindowEvent(frmCarregamento,WindowEvent.WINDOW_CLOSING));
            }
        });

        frmCarregamento.setVisible(false);
        frmCarregamento.add(panelCarregamento);

        ////////////////////////////////////////////////////////////////////////

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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if (clicked==radioBtnMusicas){
            for (int i=0;i<50;i++) {
                tabelaCliente.getModel().setValueAt("Musica "+(i+1),i , 0);
            }
        }
        else if(clicked==radioBtnPlaylists){
            for (int i=0;i<50;i++) {
                tabelaCliente.getModel().setValueAt("Playlist "+(i+1),i , 0);
            }
        }
        else if(clicked==btnCarregar){
            frmCarregamento.setBounds(resizeWidth(1000),resizeHeight(150),resizeWidth(240),resizeHeight(180));
            frmCarregamento.setVisible(true);
        }
        else if(clicked==btnLoja){
            radioBtnPlaylists.setVisible(true);
            radioBtnMusicas.setVisible(true);
            lblTabela.setText("Loja:");
            lblTabela.setBounds(lblTabela.getX(),lblTabela.getY(),resizeWidth(60),lblUser.getHeight());
        }
        else if(clicked==panelPlaylists.getBtnBiblioteca()){
            radioBtnMusicas.setVisible(false);
            radioBtnPlaylists.setVisible(false);
            lblTabela.setText("Biblioteca de músicas:");
            lblTabela.setBounds(lblTabela.getX(),lblTabela.getY(),resizeWidth(140),lblUser.getHeight());
        }
    }

    public JButton getBtnCarregar() {
        return btnCarregar;
    }

    private void mudarCorRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    public void setLblTabela(String nome) {
        lblTabela.setText(nome);
    }
}
