package GUI;

import GUI.Cliente.InterfaceCliente;
import Objetos.RockstarInc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Frame extends JFrame implements ActionListener{
    private LoginPanel panelLogin;
    private InterfaceCliente panelCliente;
    private Registo panelRegisto;
    private LoginPin teste;
    private JFrame frmArtista;
    private RockstarInc rockstar;

    public Frame(){
        super("Rockstar");

        File fileRockstar=new File("rockstar.dat");

        try{
            //ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileRockstar));
            //oos.writeObject(new RockstarInc());
            //oos.close();
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(fileRockstar));
            this.rockstar=(RockstarInc) ois.readObject();
        }
        catch (IOException e){
        }
        catch(ClassNotFoundException e){
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileRockstar));
                    oos.writeObject(rockstar);
                    oos.close();
                }
                catch (IOException u){
                }
            }
        });

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
