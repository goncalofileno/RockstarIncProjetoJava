package GUI;

import javax.swing.*;
import java.awt.*;

public class PanelCarrinho extends JPanel {
    private JLabel lblCarrinho,lblTotalCompra;
    private JPanel panelCarrinho;
    private JScrollPane scroll;
    private JButton checkout;
    private JList lista;
    private JPanel filtros;

    public PanelCarrinho(){
        float[] cor =new float[3];
        cor=Color.RGBtoHSB(155,223,243,cor);
        setBackground(Color.getHSBColor(cor[0],cor[1],cor[2]));

        setLayout(null);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(resizeWidth(200),resizeHeight(400));

        Font font=new Font("SansSerif",Font.BOLD,12);
        lblCarrinho=new JLabel("Carrinho:");
        lblCarrinho.setFont(font);
        lblCarrinho.setBounds(resizeWidth(10),resizeHeight(0),resizeWidth(90),resizeHeight(30));
        add(lblCarrinho);

        ///////////////////////////Painel do carrinho de compras///////////////////////////////
        panelCarrinho=new JPanel();

        panelCarrinho.setLayout(new BoxLayout(panelCarrinho,BoxLayout.Y_AXIS));

        String[] musicas={"Musica 1","Musica 2","Musica 3","Musica 4","Musica 5","Musica 6"};
        Double[] precos={9.00,2.00,3.00,4.00,2.00,3.00};
        Double precoTotal=0.0;

        for (int i=0;i<musicas.length;i++){
            panelCarrinho.add(new JLabel(musicas[i]));
            panelCarrinho.add(new JLabel(String.valueOf(precos[i])+"€"));
            precoTotal+=precos[i];
        }

        scroll=new JScrollPane(panelCarrinho);
        scroll.setBounds(lblCarrinho.getX(),lblCarrinho.getY()+resizeHeight(30),resizeWidth(180),resizeHeight(150));
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scroll);

        lblTotalCompra=new JLabel("Total: "+precoTotal+"€");
        lblTotalCompra.setFont(font);
        lblTotalCompra.setBounds(lblCarrinho.getX(),scroll.getY()+scroll.getHeight()+resizeHeight(5),resizeWidth(75),resizeHeight(20));
        add(lblTotalCompra);

        checkout=new JButton("Checkout");
        checkout.setFont(font);
        checkout.setBounds(lblCarrinho.getX(),lblTotalCompra.getY()+lblTotalCompra.getHeight()+resizeHeight(10),resizeWidth(110),resizeHeight(25));
        add(checkout);

        /////////////////////////////////////////////////////////////////////////////////////////


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
