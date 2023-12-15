package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel implements MouseListener, ActionListener {
    private JLabel lblUsername, lblPass;
    private JLabel lblEmpresa;
    private JTextField txtUsername;
    private JPasswordField txtPass;
    private JButton btnLogin,btnRegisto;
    public JButton testePin,testeCliente;
    private JCheckBox checkPass;

    public LoginPanel(){

        //////////////////////TESTE INTERFACE CLIENTE//////////////////////////////////////////
        Font font4=new Font("SansSerif",Font.TYPE1_FONT,9);
        testeCliente=new JButton("TesteCliente");
        testeCliente.setFont(font4);
        testeCliente.setBounds(resizeWidth(20),resizeHeight(150),resizeWidth(100),resizeHeight(30));
        add(testeCliente);

        ////////////////////////////////////////////////////////////////////////////////////////
        Font font1=new Font("SansSererif",Font.BOLD | Font.ITALIC,35);
        lblEmpresa=new JLabel("Rockstar");
        lblEmpresa.setFont(font1);
        lblEmpresa.setForeground(Color.DARK_GRAY);
        lblEmpresa.setBounds(resizeWidth(175),resizeHeight(10),resizeWidth(200),resizeHeight(50));
        add(lblEmpresa);

        float[] cor =new float[3];
        cor=Color.RGBtoHSB(51,153,153,cor);
        setBackground(Color.getHSBColor(cor[0],cor[1],cor[2]));

        Font font=new Font("SansSerif",Font.BOLD,13);
        lblUsername=new JLabel("Username: ");
        lblUsername.setFont(font);
        lblPass=new JLabel("          Pass: ");
        lblPass.setFont(font);


        txtPass =new JPasswordField(18);
        txtPass.setFont(font);
        txtPass.setHorizontalAlignment(JPasswordField.CENTER);
        checkPass=new JCheckBox();

        txtUsername=new JTextField(18);
        txtUsername.setFont(font);
        txtUsername.setHorizontalAlignment(JTextField.CENTER);
        //txtPass=new JTextField(18);
        //txtPass.setHorizontalAlignment(JTextField.CENTER);
        //txtPass.setFont(font);

        setLayout(null);

        lblUsername.setBounds(resizeWidth(115), resizeHeight(100), resizeWidth(90), resizeHeight(20));
        add(lblUsername);

        txtUsername.setBounds(lblUsername.getX()+resizeWidth(80),lblUsername.getY(),resizeWidth(150),resizeHeight(25));
        add(txtUsername);

        lblPass.setBounds(lblUsername.getX(),lblUsername.getY()+ resizeHeight(40),lblUsername.getWidth(),lblUsername.getHeight());
        add(lblPass);

        txtPass.setBounds(txtUsername.getX(),txtUsername.getY()+resizeHeight(40),txtUsername.getWidth(),txtUsername.getHeight());
        add(txtPass);

        checkPass.setBounds(txtPass.getX()+ txtPass.getWidth()+resizeWidth(5), txtPass.getY(),resizeWidth(20),resizeWidth(20));
        checkPass.setBackground(Color.getHSBColor(cor[0],cor[1],cor[2]));
        checkPass.addActionListener(this);
        add(checkPass);

        //txtPass.setBounds(txtUsername.getX(),txtUsername.getY()+40,txtUsername.getWidth(),txtUsername.getHeight());
        //add(txtPass);

        btnLogin=new JButton("Login");
        btnLogin.setFont(font);
        btnLogin.setBounds(resizeWidth(140),resizeHeight(210),resizeWidth(90),resizeHeight(40));
        float[] cor2 =new float[3];
        cor2=Color.RGBtoHSB(170,210,220,cor2);
        btnLogin.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
        btnLogin.addMouseListener(this);

        btnRegisto=new JButton("Registo");
        btnRegisto.setFont(font);
        btnRegisto.setBounds(btnLogin.getX()+btnLogin.getWidth()+resizeWidth(30),btnLogin.getY(),btnLogin.getWidth(),btnLogin.getHeight());
        btnRegisto.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
        btnRegisto.addMouseListener(this);

        add(btnLogin);
        add(btnRegisto);

        Font font3=new Font("SansSerif",Font.TYPE1_FONT,10);
        testePin=new JButton("TestePin");
        testePin.setFont(font3);
        testePin.setBounds(resizeWidth(20),resizeHeight(250),resizeWidth(80),resizeHeight(20));
        add(testePin);

        //Criar icon para a Rockstar//////////////////////////////////////////
        JLabel imagem=new JLabel();
        int witdh40=resizeWidth(40);
        int witdh30=resizeWidth(30);
        int height50=resizeHeight(50);
        int height30=resizeHeight(30);
        imagem.setBounds(lblEmpresa.getX()-witdh40,lblEmpresa.getY(),witdh30,height50);
        ImageIcon icon =new ImageIcon("rockstar.png");
        Image imagem2;
        imagem2=icon.getImage().getScaledInstance(witdh30,height30,Image.SCALE_SMOOTH);
        imagem.setIcon(new ImageIcon(imagem2));
        add(imagem);
        ////////////////////////////////////////////////////////////////

    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public JButton getRegisto() {
        return btnRegisto;
    }

    public JButton getTestePin(){
        return testePin;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //JOptionPane.showMessageDialog(this,"Login sem sucesso");
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        float[] cor2 =new float[3];
        cor2=Color.RGBtoHSB(170,210,220,cor2);

        Object clicked=e.getSource();

        if (clicked==btnLogin){
            btnLogin.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
        }
        else if (clicked==btnRegisto){
            btnRegisto.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        float[] cor3 =new float[3];
        cor3=Color.RGBtoHSB(100,130,140,cor3);

        Object clicked=e.getSource();

        if (clicked==btnLogin){
            btnLogin.setBackground(Color.getHSBColor(cor3[0],cor3[1],cor3[2]));
        }
        else if (clicked==btnRegisto){
            btnRegisto.setBackground(Color.getHSBColor(cor3[0],cor3[1],cor3[2]));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        float[] cor2 =new float[3];
        cor2=Color.RGBtoHSB(170,210,220,cor2);

        Object clicked=e.getSource();

        if (clicked==btnLogin){
            btnLogin.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
        }
        else if (clicked==btnRegisto){
            btnRegisto.setBackground(Color.getHSBColor(cor2[0],cor2[1],cor2[2]));
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkPass.isSelected()){
            txtPass.setEchoChar((char)0);
        }
        else {
            txtPass.setEchoChar('•');
        }
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JTextField getTxtUsername() {
        return txtUsername;
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

 /*String [] genero={"Pop","Rock","Pop latino"};
        cmbox=new JComboBox(genero);
        cmbox.setBounds(5,5,100,100);
        add(cmbox);*/
