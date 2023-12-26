package GUI.Cliente;

import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PesquisaPanel extends JPanel {

    private JLabel lblPesquisa;
    private JTextField txtPesquisa;
    private JRadioButton radioArtista, radioTitulo;
    private JButton btnPesquisa;
    private TabelaCliente tabelaCliente;
    private RockstarInc rockstar;
    private InterfaceCliente interfaceCliente;

    public PesquisaPanel(TabelaCliente tabelaCliente, RockstarInc rockstar, InterfaceCliente interfaceCliente){
        this.tabelaCliente=tabelaCliente;
        this.rockstar=rockstar;
        this.interfaceCliente=interfaceCliente;

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

        radioArtista=new JRadioButton("Artista");
        radioArtista.setFont(font);
        radioArtista.setBounds(txtPesquisa.getX(),txtPesquisa.getY()+txtPesquisa.getHeight()+resizeHeight(10),resizeWidth(70),resizeHeight(20));
        radioArtista.setOpaque(false);
        radioArtista.setSelected(true);
        add(radioArtista);

        radioTitulo=new JRadioButton("Título");
        radioTitulo.setFont(font);
        radioTitulo.setBounds(radioArtista.getX()+radioArtista.getWidth()+resizeWidth(5),radioArtista.getY(),radioArtista.getWidth(),radioArtista.getHeight());
        radioTitulo.setOpaque(false);
        add(radioTitulo);

        ButtonGroup grupoBtn=new ButtonGroup();
        grupoBtn.add(radioArtista);
        grupoBtn.add(radioTitulo);

        btnPesquisa=new JButton("\uD83D\uDD0E");
        btnPesquisa.setBounds(txtPesquisa.getX()+txtPesquisa.getWidth()+resizeWidth(5),txtPesquisa.getY(),resizeWidth(50),resizeHeight(22));
        add(btnPesquisa);

        btnPesquisa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioArtista.isSelected()){
                    tabelaCliente.setListaMusicasAtual(rockstar.pesquisaArtista(tabelaCliente.getListaMusicasAtual(),txtPesquisa.getText()));
                    if(interfaceCliente.getLblTabela().getText().equals("Loja:")) {

                        tabelaCliente.printMusicasLoja(tabelaCliente.getListaMusicasAtual());
                    }
                    else {
                        tabelaCliente.printMusicas(tabelaCliente.getListaMusicasAtual());
                    }
                    tabelaCliente.setPlaylist(null);
                }
                else {
                    tabelaCliente.setListaMusicasAtual(rockstar.pesquisaTitulo(tabelaCliente.getListaMusicasAtual(),txtPesquisa.getText()));
                    if(interfaceCliente.getLblTabela().getText().equals("Loja:")) {
                        tabelaCliente.printMusicasLoja(tabelaCliente.getListaMusicasAtual());
                    }
                    else {
                        tabelaCliente.printMusicas(tabelaCliente.getListaMusicasAtual());
                    }
                    tabelaCliente.setPlaylist(null);
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

    public JTextField getTxtPesquisa() {
        return txtPesquisa;
    }

    public JRadioButton getRadioArtista() {
        return radioArtista;
    }

    public JRadioButton getRadioTitulo() {
        return radioTitulo;
    }

    public JButton getBtnPesquisa() {
        return btnPesquisa;
    }
}
