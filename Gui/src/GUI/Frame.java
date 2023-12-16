package GUI;

import Objetos.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener{
    private LoginPanel panelLogin;
    private InterfaceCliente panelCliente;
    private Registo panelRegisto;
    private LoginPin teste;
    private JFrame frmArtista;
    private ArrayList<Cliente> clientes=new ArrayList<>();

    public Frame(){
        super("Rockstar");

        setLayout(null);
        setSize((resizeWidth(500)),resizeHeight(350));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelLogin=new LoginPanel();
        panelRegisto=new Registo();
        panelRegisto.setBounds(0,0,getWidth(),getWidth());
        panelLogin.setBounds(0,0,getWidth(),getHeight());
        add(panelLogin);

        /////////////////////////////////////////// JFrame do Pin do Artista////////////////////////
        frmArtista =new JFrame("PIN");
        frmArtista.setSize(resizeWidth(250),resizeHeight(200));
        frmArtista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frmArtista.setLayout(null);
        frmArtista.setResizable(false);
        frmArtista.setVisible(false);

        teste=new LoginPin();
        teste.setBounds(0,0, frmArtista.getWidth(), frmArtista.getHeight());
        frmArtista.add(teste);

        ///////////////////////////////////////////////////////////////////////////////
        panelRegisto.getBtnVoltarAtras().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(panelLogin);
                panelLogin.setVisible(true);
                panelRegisto.setVisible(false);
                remove(panelRegisto);
            }
        });

        panelLogin.getRegisto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLogin.setVisible(false);
                add(panelRegisto);
                panelRegisto.setVisible(true);
                remove(panelLogin);
            }
        });

        panelLogin.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean verificado=false;
                for (int i=0;i<clientes.size();i++){
                    String pass=new String(panelLogin.getTxtPass().getPassword());
                    System.out.println(clientes.get(i).getNome()+" "+clientes.get(i).getPass());
                    System.out.println(panelLogin.getTxtUsername().getText()+" "+pass);
                    if (clientes.get(i).getNome().equals(panelLogin.getTxtUsername().getText()) && clientes.get(i).getPass().equals(pass) && !verificado){
                        JOptionPane.showMessageDialog(panelLogin,"A conta existe");
                        verificado=true;
                    }
                }
                if (verificado==false){
                    JOptionPane.showMessageDialog(panelLogin,"A conta não existe");
                }
            }
        });

        panelRegisto.getBtnValidar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientes.add(new Cliente(panelRegisto.getTxtNome().getText(),panelRegisto.getTxtPass().getText()));
            }
        });

        setResizable(false);
        setVisible(true);

        //////////////////////Teste do Painel Pin Artista///////////////////////////////////////////////

        panelLogin.getTestePin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmArtista.setLocationRelativeTo(null);
                frmArtista.setVisible(true);
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////

        panelCliente=new InterfaceCliente();


        ///////////////TESTE CLIENTE////////////////////////////////
        panelLogin.testeCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setSize(resizeWidth(950),resizeHeight(650));
                panelCliente.setBounds(0,0,getWidth(),getHeight());
                setLocationRelativeTo(null);
                add(panelCliente);
                panelCliente.setVisible(true);
                panelLogin.setVisible(false);
            }
        });

        ///////////////////////////////////////////////////////////
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
