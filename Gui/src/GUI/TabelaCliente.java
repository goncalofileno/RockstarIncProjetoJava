package GUI;

import Objetos.Cliente;
import Objetos.Musica;
import Objetos.RockstarInc;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TabelaCliente extends JPanel implements ActionListener {
    private JButton btnTitulo,btnArtista,btnRating,btnPreco,btnVisibilidade;
    private DefaultTableModel model;
    private RockstarInc rockstar;
    private Cliente utizadorAtual;
    private ClientePlaylists panelPlaylists;
    private JButton btnBiblioteca;
    private InterfaceCliente interfaceCliente;
    private panelPlaylistAI panelPlaylistAI;

    private ArrayList<JButton> btnPlaylists;
    public TabelaCliente(RockstarInc rockstar, Cliente utilizadorAtual,ClientePlaylists panelPlaylists, InterfaceCliente interfaceCliente) {
        this.rockstar=rockstar;
        this.utizadorAtual=utilizadorAtual;
        this.interfaceCliente=interfaceCliente;

        setPanelPlaylists(panelPlaylists);
        //this.panelPlaylists=panelPlaylists;
        //this.btnPlaylists=panelPlaylists.getBtnListaPlaylists();
        //for (int i=0;i<btnPlaylists.size();i++){
        //    btnPlaylists.get(i).addActionListener(this);
        //}

        btnBiblioteca=panelPlaylists.getBtnBiblioteca();
        btnBiblioteca.addActionListener(this);

        // Criar o modelo da tabela
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Apenas permitir a edição na terceira coluna (coluna com índice 2)
                return column == 4;
            }
        };

        setLayout(null);

        model.addColumn("");
        model.addColumn("");
        model.addColumn("");
        //model.addColumn("");

        // Adicionar dados à tabela (pode-se adicionar mais linhas conforme necessário)

        for (int i = 0; i < 50; i++) {
            model.addRow(new Object[]{"", "", "", "", Boolean.FALSE});
        }

        // Criar a tabela com o modelo
        JTable table = new JTable(model);

        mudarCorRGB(this,200,240,250);
        mudarCorRGB(table,200,240,250);
        Font font=new Font("SansSerif",Font.BOLD,12);
        table.setFont(font);

        // Configurar a coluna do checkbox
        //TableColumn checkboxColumn = table.getColumnModel().getColumn(4);
        //checkboxColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //checkboxColumn.setCellEditor(table.getDefaultEditor(Boolean.class));

        printMusicas(utilizadorAtual.getBiblioteca());

        // Adicionar a tabela à janela
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(resizeWidth(0),resizeHeight(25),resizeWidth(465),resizeHeight(500));
        add(scrollPane);
        table.setRowHeight(23);

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);

        for (int i=0;i<table.getColumnCount();i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }

        table.getTableHeader().setResizingAllowed(false);

        btnTitulo=new JButton("Título");
        btnTitulo.setFont(font);
        btnArtista=new JButton("Artista");
        btnArtista.setFont(font);
        btnRating=new JButton("Rating");
        btnRating.setFont(font);
        btnPreco=new JButton("Preço");
        btnPreco.setFont(font);

        Font font2=new Font("SansSerif", Font.BOLD,9);
        btnVisibilidade=new JButton("Visibilidade");
        btnVisibilidade.setFont(font2);

        btnTitulo.setBounds(scrollPane.getX(),resizeHeight(0),resizeWidth(150),resizeHeight(25));
        btnArtista.setBounds(btnTitulo.getX()+btnTitulo.getWidth(),resizeHeight(0),btnTitulo.getWidth(),btnTitulo.getHeight());
        btnRating.setBounds(btnArtista.getX()+btnArtista.getWidth(),resizeHeight(0),btnTitulo.getWidth()-resizeWidth(1),btnTitulo.getHeight());
        btnPreco.setBounds(btnRating.getX()+btnRating.getWidth(),resizeHeight(0),btnTitulo.getWidth(),btnTitulo.getHeight());
        btnVisibilidade.setBounds(btnPreco.getX()+btnPreco.getWidth(),resizeHeight(0),btnTitulo.getWidth()-resizeWidth(1),btnTitulo.getHeight());

        add(btnTitulo);
        add(btnArtista);
        add(btnRating);
        //add(btnPreco);
        //add(btnVisibilidade);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    private int resizeWidth(int width) {
        Dimension ecra = Toolkit.getDefaultToolkit().getScreenSize();
        int widthAtualizada;
        widthAtualizada = (int) (width * ecra.getWidth()) / 1536;
        return widthAtualizada;
    }

    private int resizeHeight(int height) {
        Dimension ecra = Toolkit.getDefaultToolkit().getScreenSize();
        int heightAtualizada;
        heightAtualizada = (int) (height * ecra.getHeight()) / 864;
        return heightAtualizada;
    }

    private void mudarCorRGB(Component componente,int red,int green,int blue){ float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    public void printMusicas(ArrayList<Musica> musicas){
        for (int i=0;i<model.getRowCount();i++){
            model.removeRow(i);
        }
        for (int i = 0; i < 50; i++) {
            model.addRow(new Object[]{"", "", ""});
        }
        for (int i=0;i<musicas.size();i++) {
            model.setValueAt(musicas.get(i).getTitulo(),i , 0);
            model.setValueAt(musicas.get(i).getCompositor().getNome(),i , 1);
            if (musicas.get(i).getRatingMedio()>0) {
                model.setValueAt(musicas.get(i).getRatingMedio(), i, 2);
            }
            else {
                model.setValueAt("Sem Rating", i, 2);
            }
        }
    }

    public void setPanelPlaylists(ClientePlaylists panelPlaylists) {
        this.panelPlaylists = panelPlaylists;
        this.btnPlaylists=panelPlaylists.getBtnListaPlaylists();
        for (int i=0;i<btnPlaylists.size();i++){
            this.btnPlaylists.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked=e.getSource();

        if (clicked==btnBiblioteca){
            printMusicas(utizadorAtual.getBiblioteca());
            interfaceCliente.setLblTabela("Biblioteca de músicas:");
        }
        for (int i=0;i<btnPlaylists.size();i++) {
            if (clicked ==btnPlaylists.get(i)){
                printMusicas(utizadorAtual.getPlaylistsProprias().get(i).getMusicas());
                interfaceCliente.setLblTabela("Playlist: "+utizadorAtual.getPlaylistsProprias().get(i).getNome());
            }
        }
    }
}



