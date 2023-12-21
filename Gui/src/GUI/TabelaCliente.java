package GUI;

import Objetos.Cliente;
import Objetos.Musica;
import Objetos.Playlist;
import Objetos.RockstarInc;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TabelaCliente extends JPanel implements ActionListener {
    private DefaultTableModel model;
    private RockstarInc rockstar;
    private Cliente utilizadorAtual;
    private ClientePlaylists panelPlaylists;
    private JButton btnBiblioteca,btnRemoverPlaylist;
    private InterfaceCliente interfaceCliente;
    private panelPlaylistAI panelPlaylistAI;
    private JTable table;
    private ArrayList<Musica> listaMusicasAtual;
    private Playlist playlist;
    private ArrayList<JButton> btnPlaylists;
    private JScrollPane scrollPane;
    private JPopupMenu popupMenuLoja,popupMenuBiblioteca;

    public TabelaCliente(RockstarInc rockstar, Cliente utilizadorAtual,ClientePlaylists panelPlaylists, InterfaceCliente interfaceCliente) {
        this.rockstar=rockstar;
        this.utilizadorAtual =utilizadorAtual;
        this.interfaceCliente=interfaceCliente;

        setPanelPlaylists(panelPlaylists);

        btnBiblioteca=panelPlaylists.getBtnBiblioteca();
        btnBiblioteca.addActionListener(this);

        String [] headers ={"Nome","Artista","Género","Rating",""};
        // Criar o modelo da tabela
        model = new DefaultTableModel();

        model.setColumnIdentifiers(headers);

        setLayout(null);

        //model.addColumn("");

        // Adicionar dados à tabela (pode-se adicionar mais linhas conforme necessário)

        // Criar a tabela com o modelo
        table = new JTable(model){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        table.getTableHeader().setReorderingAllowed(false);

        table.setEnabled(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        /*TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);*/

        table.setAutoCreateRowSorter(true);


        mudarCorRGB(this,51,153,153);
        mudarCorRGB(table,200,240,250);

        Font font=new Font("SansSerif",Font.BOLD,12);
        table.setFont(font);

        // Configurar a coluna do checkbox
        //TableColumn checkboxColumn = table.getColumnModel().getColumn(4);
        //checkboxColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //checkboxColumn.setCellEditor(table.getDefaultEditor(Boolean.class));

        scrollPane = new JScrollPane(table);
        printMusicas(utilizadorAtual.getBiblioteca());

        // Adicionar a tabela à janela

        scrollPane.setBounds(resizeWidth(0),resizeHeight(0),resizeWidth(465),(listaMusicasAtual.size()+1)*resizeHeight(23));
        add(scrollPane);
        table.setRowHeight(23);

        alinharTable();

        table.getTableHeader().setResizingAllowed(false);

        popupMenuLoja =new JPopupMenu();
        JMenuItem menuLoja1=new JMenuItem("Comprar música");
        menuLoja1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row =table.getSelectedRow();
                row =table.convertRowIndexToModel(row);

                if(!utilizadorAtual.verificarBiblioteca(rockstar.musicaSelecionada(table.getModel().getValueAt(row,5).toString(),table.getModel().getValueAt(row,0).toString()))) {
                    utilizadorAtual.comprarMusica(rockstar.musicaSelecionada(table.getModel().getValueAt(row, 5).toString(), table.getModel().getValueAt(row, 0).toString()));
                }
                else {
                    JOptionPane.showMessageDialog(interfaceCliente, "Música já adquirida.");
                }

            }
        });

        popupMenuLoja.add(menuLoja1);

        popupMenuLoja.setSize(600,300);
        add(popupMenuLoja);

        popupMenuBiblioteca=new JPopupMenu();
        JMenuItem menuBiblioteca1=new JMenuItem("Adicionar música");

        menuBiblioteca1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        popupMenuBiblioteca.add(menuBiblioteca1);

        popupMenuBiblioteca.setSize(600, 300);
        add(popupMenuBiblioteca);


        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (interfaceCliente.getLblTabela().getText().equals("Loja:")) {
                        int row =table.getSelectedRow();
                        row =table.convertRowIndexToModel(row);

                        popupMenuLoja.show(e.getComponent(), e.getX(), e.getY());
                        int r = table.rowAtPoint(e.getPoint());
                        table.setRowSelectionInterval(r, r);

                        System.out.println(table.getModel().getValueAt(row,5).toString());

                    }



                    else if (interfaceCliente.getLblTabela().getText().equals("Biblioteca de músicas:")){

                        int row =table.getSelectedRow();
                        row =table.convertRowIndexToModel(row);

                        popupMenuBiblioteca.show(e.getComponent(), e.getX(), e.getY());
                        int r = table.rowAtPoint(e.getPoint());
                        table.setRowSelectionInterval(r, r);

                        rockstar.musicaSelecionada(table.getModel().getValueAt(row,4).toString(),table.getModel().getValueAt(row,0).toString());

                        System.out.println(table.getModel().getValueAt(row,4).toString());;
                    }
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
        listaMusicasAtual=musicas;
        scrollPane.setSize(resizeWidth(465),(listaMusicasAtual.size()+1)*resizeHeight(23));
        model.setRowCount(0);
        for (int i = 0; i < musicas.size(); i++) {
            model.addRow(new Object[]{"", "", "","",""});
        }
        for (int i=0;i<musicas.size();i++) {
            model.setValueAt(musicas.get(i).getTitulo(),i , 0);
            model.setValueAt(musicas.get(i).getCompositor().getNome(),i , 1);
            model.setValueAt(musicas.get(i).getGenero(),i,2);
            if (musicas.get(i).getRatingMedio()>0) {
                model.setValueAt(musicas.get(i).getRatingMedio(), i, 3);
            }
            else {
                model.setValueAt("Sem Rating", i, 3);
            }
            model.setValueAt(musicas.get(i).getCompositor().getUsername(),i,4);
        }

        alinharTable();

        TableColumnModel tcm=table.getColumnModel();
        tcm.removeColumn((tcm.getColumn(4)));
    }

    public void printMusicasLoja(ArrayList<Musica> musicas){
        listaMusicasAtual=musicas;
        scrollPane.setSize(resizeWidth(465),(listaMusicasAtual.size()+1)*resizeHeight(23));
        model.setRowCount(0);

        for (int i = 0; i < musicas.size(); i++) {
            model.addRow(new Object[]{"", "", "","","",""});
        }
        for (int i=0;i<musicas.size();i++) {
            model.setValueAt(musicas.get(i).getTitulo(),i , 0);
            model.setValueAt(musicas.get(i).getCompositor().getNome(),i , 1);
            model.setValueAt(musicas.get(i).getGenero(),i,2);
            if (musicas.get(i).getRatingMedio()>0) {
                model.setValueAt(musicas.get(i).getRatingMedio(), i, 3);
            }
            else {
                model.setValueAt("Sem Rating", i, 3);
            }
            model.setValueAt(musicas.get(i).getPrecoMusica(),i,4);
            model.setValueAt(musicas.get(i).getCompositor().getUsername(),i,5);
        }

        alinharTable();

        TableColumnModel tcm=table.getColumnModel();
        tcm.removeColumn((tcm.getColumn(5)));

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

        for (int i=0;i<btnPlaylists.size();i++) {
            if (clicked ==btnPlaylists.get(i)){
                String [] headers= {"Nome","Artista","Género","Rating"};
                setHeader(headers);
                printMusicas(utilizadorAtual.getPlaylistsProprias().get(i).getMusicas());
                playlist= utilizadorAtual.getPlaylistsProprias().get(i);
                interfaceCliente.setLblTabela("Playlist: "+ utilizadorAtual.getPlaylistsProprias().get(i).getNome());
                interfaceCliente.getBtnRemoverPlaylist().setVisible(true);
            }
        }

    }
    public JTable getTable() {
        return table;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
    public void setHeader(String[] headers){
        model.setColumnIdentifiers(headers);
    }
    public void alinharTable(){
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);

        for (int i=0;i<table.getColumnCount();i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    public JPopupMenu getPopupMenuLoja() {
        return popupMenuLoja;
    }

    public JPopupMenu getPopupMenuBiblioteca() {
        return popupMenuBiblioteca;
    }
}



