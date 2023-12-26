package GUI;

import GUI.Cliente.InterfaceCliente;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        setResizable(false);
        setVisible(true);
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
