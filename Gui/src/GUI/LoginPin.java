package GUI;

import Objetos.Artista;
import Objetos.RockstarInc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginPin extends JPanel implements MouseListener, ActionListener {
    private JLabel lblPin;
    private JTextField txtPin;
    private JPasswordField pin;
    private JButton btnValidar;

    private JCheckBox checkPin;
    private Border border;
    private RockstarInc rockstar;
    private Artista artista;

    public LoginPin(RockstarInc rockstar, Artista artista){
        this.artista=artista;
        this.rockstar=rockstar;

        mudarCorRGB(this,50,126,154);
        setLayout(null);
        Font font=new Font("SansSerif",Font.BOLD,13);
        lblPin=new JLabel("Pin:");
        lblPin.setFont(font);
        lblPin.setBounds(resizeWidth(45),resizeHeight(45),resizeWidth(30),resizeHeight(20));
        add(lblPin);

        pin=new JPasswordField(18);
        pin.setFont(font);
        pin.setBounds(lblPin.getX()+lblPin.getWidth()+resizeWidth(2),lblPin.getY(),resizeWidth(100),resizeHeight(20));
        pin.setHorizontalAlignment(JPasswordField.CENTER);
        add(pin);

        /*txtPin=new JTextField(18);
        txtPin.setFont(font);
        txtPin.setBounds(lblPin.getX()+lblPin.getWidth()+2,lblPin.getY(),100,20);
        txtPin.setHorizontalAlignment(JTextField.CENTER);
        add(txtPin);*/

        btnValidar=new JButton("Validar");
        btnValidar.setFont(font);
        btnValidar.setBounds(resizeWidth(80),resizeHeight(100),resizeWidth(90),resizeHeight(40));
        mudarCorRGB(btnValidar,170,210,220);
        btnValidar.addMouseListener(this);
        add(btnValidar);

        checkPin=new JCheckBox();
        checkPin.setBounds(pin.getX()+pin.getWidth()+resizeWidth(5),pin.getY(),resizeWidth(20),resizeHeight(20));
        mudarCorRGB(checkPin,50,126,154);
        checkPin.addActionListener(this);
        add(checkPin);


        //Criar icon para a Rockstar/////
        JLabel imagem=new JLabel();
        imagem.setBounds(resizeWidth(210),resizeHeight(10),resizeWidth(20),resizeHeight(20));
        ImageIcon icon =new ImageIcon("rockstar.png");
        Image imagem2;
        imagem2=icon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        imagem.setIcon(new ImageIcon(imagem2));
        add(imagem);
        /////////////////////////////////

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        float[] cor2 =new float[3];
        cor2=Color.RGBtoHSB(100,130,140,cor2);
        btnValidar.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        float[] cor2 =new float[3];
        cor2=Color.RGBtoHSB(170,210,220,cor2);
        btnValidar.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if (checkPin.isSelected()){
            pin.setEchoChar((char)0);
        }
        else {
            pin.setEchoChar('•');
        }
        if (clicked==btnValidar){
            if (rockstar.verificarLoginPin(artista,txtPin.getText())){

            }
            else JOptionPane.showMessageDialog(this,"O PIN introduzido está incorreto");
        }

    }

    public JTextField getTxtPin() {
        return txtPin;
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
}
