package GUI;

import Objetos.Cliente;
import Objetos.RockstarInc;

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
    private RockstarInc rockstar;

    public Frame(){
        super("Rockstar");
        rockstar=new RockstarInc();
        setLayout(null);
        setSize((resizeWidth(500)),resizeHeight(350));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panelLogin=new LoginPanel(rockstar,this);
        panelRegisto=new Registo(rockstar,panelLogin);
        panelRegisto.setBounds(0,0,getWidth(),getWidth());
        panelLogin.setBounds(0,0,getWidth(),getHeight());
        add(panelLogin);

        /////////////////////////////////////////// JFrame do Pin do Artista////////////////////////
        //frmArtista =new JFrame("PIN");
        //frmArtista.setSize(resizeWidth(250),resizeHeight(200));
        //frmArtista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //frmArtista.setLayout(null);
        //frmArtista.setResizable(false);
        //frmArtista.setVisible(false);

        //teste=new LoginPin();
        //teste.setBounds(0,0, frmArtista.getWidth(), frmArtista.getHeight());
        //frmArtista.add(teste);

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
            }
        });

        panelLogin.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean verificado=false;
                //for (int i=0;i<clientes.size();i++){
                    //String pass=new String(panelLogin.getTxt//ass().getPassword());
                    //System.out.println(clientes.get(i).getNome()+" "+clientes.get(i).getPass());
                    //System.out.println(panelLogin.getTxtUsername().getText()+" "+pass);
                   // if (clientes.get(i).getNome().equals(panelLogin.getTxtUsername().getText()) && clientes.get(i).getPass().equals(pass) && !verificado){
                   //     JOptionPane.showMessageDialog(panelLogin,"A conta existe");
                   //     verificado=true;
                   // }
               // }
                //if (verificado==false){
                  //  JOptionPane.showMessageDialog(panelLogin,"A conta não existe");
                //}
            }
        });

        panelRegisto.getBtnValidar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //clientes.add(new Cliente(panelRegisto.getTxtNome().getText(),panelRegisto.getTxtPass().getText()));
            }
        });

        setResizable(false);
        setVisible(true);


        panelCliente=new InterfaceCliente();
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
