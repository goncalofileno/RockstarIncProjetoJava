package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PesquisaPanel extends JPanel {

    private JLabel lblPesquisa;
    private JTextField txtPesquisa;
    private JRadioButton radioArtista, radioTitulo;
    private JButton btnPesquisa;
    private JPopupMenu popupMenu;

    public PesquisaPanel(){
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

        popupMenu=new JPopupMenu();
        popupMenu.add(new JMenuItem("Comprar música"));
        popupMenu.setSize(200,100);
        add(popupMenu);

        txtPesquisa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

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
}
