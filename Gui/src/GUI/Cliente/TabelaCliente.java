package GUI.Cliente;

import Objetos.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TabelaCliente extends JPanel implements ActionListener {
    private DefaultTableModel model;
    private RockstarInc rockstar;
    private Cliente utilizadorAtual;
    private ClientePlaylists panelPlaylists;
    private JButton btnBiblioteca, btnRemoverPlaylist;
    private InterfaceCliente interfaceCliente;
    private panelPlaylistAI panelPlaylistAI;
    private JTable table;
    private ArrayList<Musica> listaMusicasAtual;
    private Playlist playlist;
    private ArrayList<JButton> btnPlaylists;
    private JScrollPane scrollPane;
    private JPopupMenu popupMenuLoja, popupMenuBiblioteca;
    private PanelCarrinho panelCarrinho;
    private JFrame frmPrecos;
    private JPanel panelPrecos,panelRating;
    private JMenuItem menuBiblioteca1;
    private ArrayList<JMenuItem> menuBiblioteca11;
    private ArrayList<Playlist> playlistsPopMenu;
    private Musica musiceSelecionada;
    private JDialog frmRating;
    private PesquisaPanel panelPesquisa;

    public TabelaCliente(RockstarInc rockstar, Cliente utilizadorAtual, ClientePlaylists panelPlaylists, InterfaceCliente interfaceCliente) {
        this.rockstar = rockstar;
        this.utilizadorAtual = utilizadorAtual;
        this.interfaceCliente = interfaceCliente;

        setPanelPlaylists(panelPlaylists);

        btnBiblioteca = panelPlaylists.getBtnBiblioteca();
        btnBiblioteca.addActionListener(this);

        String[] headers = {"Nome", "Artista", "Género", "Rating", ""};
        // Criar o modelo da tabela
        model = new DefaultTableModel();

        model.setColumnIdentifiers(headers);

        setLayout(null);

        //model.addColumn("");

        // Adicionar dados à tabela (pode-se adicionar mais linhas conforme necessário)

        // Criar a tabela com o modelo
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        table.setEnabled(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setOrdemTabela();

        //table.setAutoCreateRowSorter(true);

        mudarCorRGB(this, 51, 153, 153);
        mudarCorRGB(table, 200, 240, 250);

        Font font = new Font("SansSerif", Font.BOLD, 12);
        table.setFont(font);

        // Configurar a coluna do checkbox
        //TableColumn checkboxColumn = table.getColumnModel().getColumn(4);
        //checkboxColumn.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        //checkboxColumn.setCellEditor(table.getDefaultEditor(Boolean.class));

        scrollPane = new JScrollPane(table);
        printMusicas(utilizadorAtual.getBiblioteca());

        // Adicionar a tabela à janela

        int scrollPaneHeight;
        if ((listaMusicasAtual.size() + 1) * resizeHeight(23)<=500){
            scrollPaneHeight=(listaMusicasAtual.size() + 1) * resizeHeight(23);
        }
        else scrollPaneHeight=resizeHeight(500);

        scrollPane.setSize(resizeWidth(465), scrollPaneHeight);
        add(scrollPane);
        table.setRowHeight(23);

        table.getTableHeader().setResizingAllowed(false);

        popupMenuLoja = new JPopupMenu();
        JMenuItem menuLoja1 = new JMenuItem("Comprar música");
        menuLoja1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int row = table.getSelectedRow();

                    if (row != -1 && row < listaMusicasAtual.size()) {
                        row = table.convertRowIndexToModel(row);

                        Musica musicaSelecionada = rockstar.musicaSelecionada(table.getModel().getValueAt(row, 5).toString(), table.getModel().getValueAt(row, 0).toString());

                        if (!utilizadorAtual.verificarBiblioteca(musicaSelecionada)) {
                            if (!utilizadorAtual.verificarMusicaCarrinho(musicaSelecionada)) {
                                utilizadorAtual.comprarMusica(musicaSelecionada);
                                if (musicaSelecionada.getPrecoMusica() != 0) {
                                    panelCarrinho.getTitulos().add(musicaSelecionada.getTitulo());
                                    panelCarrinho.addPrecoTotal(musicaSelecionada.getPrecoMusica());
                                    panelCarrinho.getPanelCarrinho().removeAll();
                                    panelCarrinho.getPanelCarrinho().revalidate();
                                    panelCarrinho.atualizarLblTotalCompra();
                                    panelCarrinho.getBtnCheckout().setEnabled(true);
                                    panelCarrinho.getBtnReset().setEnabled(true);

                                    for (int i = 0; i < utilizadorAtual.getCarrinhoDeCompras().size(); i++) {
                                        panelCarrinho.getPanelCarrinho().add(new JLabel(panelCarrinho.getTitulos().get(i)+" - "+panelCarrinho.getPrecos().get(i) + "€"));
                                        panelCarrinho.getPanelCarrinho().add(new JLabel(" "));
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(interfaceCliente, "Música adicionada com sucesso");
                                }
                            } else {
                                JOptionPane.showMessageDialog(interfaceCliente, "A música selecionada já se encontra no carrinho de compras.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(interfaceCliente, "Música já adquirida.");
                        }

                    }
                }catch(ArrayIndexOutOfBoundsException j){
                }
            }
        });

        frmPrecos = new JFrame("Histórico de preços");
        frmPrecos.setLayout(null);
        frmPrecos.setSize(resizeWidth(200), resizeHeight(300));
        frmPrecos.setLocationRelativeTo(null);

        frmPrecos.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        panelPrecos = new JPanel();
        panelPrecos.setLayout(new BoxLayout(panelPrecos, BoxLayout.Y_AXIS));
        frmPrecos.setVisible(false);

        JScrollPane scrollPane2 = new JScrollPane(panelPrecos);
        scrollPane2.setBounds(0, 0, frmPrecos.getWidth(), frmPrecos.getHeight());
        frmPrecos.add(scrollPane2);

        frmRating =new JDialog();
        frmRating.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frmRating.setLayout(null);
        frmRating.setBounds(resizeWidth(1000),resizeHeight(150),resizeWidth(280),resizeHeight(180));
        frmRating.setResizable(false);
        frmRating.setVisible(false);
        frmRating.setLocationRelativeTo(null);

        panelRating=new JPanel();
        panelRating.setLayout(null);

        JLabel lblDarRating=new JLabel("Dar Rating");
        lblDarRating.setFont(font);

        JLabel lblRating=new JLabel("Avaliação (1 - 10) :");
        lblRating.setFont(font);
        JTextField txtRating=new JTextField(20);
        txtRating.setFont(font);

        JButton btnCancelar=new JButton("Cancelar");
        btnCancelar.setFont(font);

        JButton btnRating=new JButton("Submeter");
        btnRating.setFont(font);

        lblDarRating.setBounds(resizeWidth(20),resizeHeight(5),resizeWidth(100),resizeHeight(25));
        panelRating.add(lblDarRating);

        lblRating.setBounds(lblDarRating.getX(),lblDarRating.getY()+lblDarRating.getHeight()+resizeHeight(20),resizeWidth(115),resizeHeight(25));
        panelRating.add(lblRating);

        txtRating.setBounds(lblRating.getX()+lblRating.getWidth(),lblRating.getY()+resizeHeight(1),resizeWidth(50),resizeHeight(21));
        panelRating.add(txtRating);

        btnCancelar.setBounds(lblRating.getX(),lblRating.getY()+lblRating.getHeight()+resizeHeight(20),resizeWidth(100),resizeHeight(25));
        panelRating.add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRating.dispatchEvent(new WindowEvent(frmRating,WindowEvent.WINDOW_CLOSING));
            }
        });

        btnRating.setBounds(btnCancelar.getX()+btnCancelar.getWidth()+resizeWidth(15),btnCancelar.getY(),btnCancelar.getWidth(),btnCancelar.getHeight());
        panelRating.add(btnRating);
        btnRating.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (Integer.valueOf(txtRating.getText()) >= 1 && Integer.valueOf(txtRating.getText()) <= 10) {
                        if (utilizadorAtual.adicionaRating(musiceSelecionada, Integer.valueOf(txtRating.getText()))) {
                            printMusicas(listaMusicasAtual);
                            JOptionPane.showMessageDialog(interfaceCliente, "Rating alterado com sucesso");
                            frmRating.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        } else {
                            printMusicas(listaMusicasAtual);
                            JOptionPane.showMessageDialog(interfaceCliente, "Rating adicionado com sucesso");
                            frmRating.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(interfaceCliente,"O valor inserido é inválido");
                    }
                }
                catch (NumberFormatException j){
                    JOptionPane.showMessageDialog(interfaceCliente,"Os dados introduzidos são inválidos");
                }

            }
        });

        mudarCorRGB(panelRating,51,153,153);

        panelRating.setBounds(resizeWidth(0),resizeHeight(0), frmRating.getWidth(), frmRating.getHeight());

        frmRating.add(panelRating);

        JMenuItem menuLoja2 = new JMenuItem("Histórico do preço");

        menuLoja2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPrecos.removeAll();
                panelPrecos.revalidate();
                panelPrecos.repaint();

                try {
                    int row = table.getSelectedRow();
                    if (row != -1 && row < listaMusicasAtual.size()) {
                        row = table.convertRowIndexToModel(row);
                        Musica musicaSelecionada = rockstar.musicaSelecionada(table.getModel().getValueAt(row, 5).toString(), table.getModel().getValueAt(row, 0).toString());

                        ArrayList<Preco> precos = musicaSelecionada.getListaPrecos();


                        for (int i = 0; i < precos.size(); i++) {
                            panelPrecos.add(new JLabel("        " + precos.get(i).getValor() + "€  -  " + precos.get(i).getDataPreco().toString()));
                            panelPrecos.add(new JLabel("      "));
                        }

                        frmPrecos.setVisible(true);
                    }
                }catch(ArrayIndexOutOfBoundsException h){
                }
            }
        });

        popupMenuLoja.add(menuLoja1);
        popupMenuLoja.add(menuLoja2);

        popupMenuLoja.setSize(0, 0);

        add(popupMenuLoja);

        popupMenuBiblioteca = new JPopupMenu();
        menuBiblioteca1 = new JMenu("Adicionar à Playlist");
        menuBiblioteca11 = new ArrayList<>();
        playlistsPopMenu = new ArrayList<>();

        for (int i = 0; i < utilizadorAtual.getPlaylistsProprias().size(); i++) {
            playlistsPopMenu.add(utilizadorAtual.getPlaylistsProprias().get(i));
            menuBiblioteca11.add(new JMenuItem(utilizadorAtual.getPlaylistsProprias().get(i).getNome()));
            menuBiblioteca1.add(menuBiblioteca11.get(i));
        }

        updateActionsListeners();

        JMenuItem menuBiblioteca2=new JMenuItem("Dar Rating");

        menuBiblioteca2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRating.setVisible(true);
            }
        });


        popupMenuBiblioteca.add(menuBiblioteca1);
        popupMenuBiblioteca.add(menuBiblioteca2);

        popupMenuBiblioteca.setSize(0, 0);
        add(popupMenuBiblioteca);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (interfaceCliente.getLblTabela().getText().equals("Loja:")) {
                        int row = table.getSelectedRow();

                        if (row != -1 && row<listaMusicasAtual.size()) {
                            row = table.convertRowIndexToModel(row);

                            popupMenuLoja.show(e.getComponent(), e.getX(), e.getY());

                            int r = table.rowAtPoint(e.getPoint());
                            table.setRowSelectionInterval(r, r);

                            musiceSelecionada = rockstar.musicaSelecionada(table.getModel().getValueAt(row, 4).toString(), table.getModel().getValueAt(row, 0).toString());
                        }

                    } else if (interfaceCliente.getLblTabela().getText().equals("Biblioteca de músicas:")) {

                        int row = table.getSelectedRow();

                        if (row != -1 && row<listaMusicasAtual.size()) {
                            row = table.convertRowIndexToModel(row);

                            popupMenuBiblioteca.show(e.getComponent(), e.getX(), e.getY());
                            int r = table.rowAtPoint(e.getPoint());
                            table.setRowSelectionInterval(r, r);

                            musiceSelecionada = rockstar.musicaSelecionada(table.getModel().getValueAt(row, 4).toString(), table.getModel().getValueAt(row, 0).toString());
                        }
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

    private void mudarCorRGB(Component componente, int red, int green, int blue) {
        float[] cor = new float[3];
        cor = Color.RGBtoHSB(red, green, blue, cor);
        componente.setBackground(Color.getHSBColor(cor[0], cor[1], cor[2]));
    }

    public void printMusicas(ArrayList<Musica> musicas) {
        String [] headers= {"Nome","Artista","Género","Rating",""};
        setHeader(headers);

        listaMusicasAtual = musicas;

        int scrollPaneHeight;
        if ((listaMusicasAtual.size() + 1) * resizeHeight(23)<=500){
            scrollPaneHeight=(listaMusicasAtual.size() + 1) * resizeHeight(23);
        }
        else scrollPaneHeight=resizeHeight(500);

        scrollPane.setSize(resizeWidth(465), scrollPaneHeight);
        model.setRowCount(0);

        setOrdemTabela();

        for (int i = 0; i < musicas.size(); i++) {
            model.addRow(new Object[]{"", "", "", "", ""});
        }
        for (int i = 0; i < musicas.size(); i++) {
            model.setValueAt(musicas.get(i).getTitulo(), i, 0);
            model.setValueAt(musicas.get(i).getCompositor().getNome(), i, 1);
            model.setValueAt(musicas.get(i).getGenero(), i, 2);
            if (musicas.get(i).getRatingMedio() > 0) {
                model.setValueAt(musicas.get(i).getRatingMedio(), i, 3);
            } else {
                model.setValueAt("Sem Rating", i, 3);
            }
            model.setValueAt(musicas.get(i).getCompositor().getUsername(), i, 4);
        }

        alinharTable();

        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn((tcm.getColumn(4)));
    }

    public void printMusicasLoja(ArrayList<Musica> musicas) {
        String [] headers ={"Nome","Artista","Género","Rating","Preço","Username"};
        setHeader(headers);

        listaMusicasAtual=musicas;

        int scrollPaneHeight;
        if ((listaMusicasAtual.size() + 1) * resizeHeight(23)<=500){
            scrollPaneHeight=(listaMusicasAtual.size() + 1) * resizeHeight(23);
        }
        else scrollPaneHeight=resizeHeight(500);

        scrollPane.setSize(resizeWidth(465), scrollPaneHeight);
        model.setRowCount(0);

        setOrdemTabela();

        for (int i = 0; i < musicas.size(); i++) {
            model.addRow(new Object[]{"", "", "", "", "", ""});
        }
        for (int i = 0; i < musicas.size(); i++) {
            model.setValueAt(musicas.get(i).getTitulo(), i, 0);
            model.setValueAt(musicas.get(i).getCompositor().getNome(), i, 1);
            model.setValueAt(musicas.get(i).getGenero(), i, 2);
            if (musicas.get(i).getRatingMedio() > 0) {
                model.setValueAt(musicas.get(i).getRatingMedio(), i, 3);
            } else {
                model.setValueAt("Sem Rating", i, 3);
            }
            model.setValueAt(musicas.get(i).getPrecoMusica(), i, 4);
            model.setValueAt(musicas.get(i).getCompositor().getUsername(), i, 5);
        }

        alinharTable();

        TableColumnModel tcm = table.getColumnModel();
        tcm.removeColumn((tcm.getColumn(5)));

    }

    public void setPanelPlaylists(ClientePlaylists panelPlaylists) {
        this.panelPlaylists = panelPlaylists;
        this.btnPlaylists = panelPlaylists.getBtnListaPlaylists();
        for (int i = 0; i < btnPlaylists.size(); i++) {
            this.btnPlaylists.get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked = e.getSource();

        for (int i = 0; i < btnPlaylists.size(); i++) {
            if (clicked == btnPlaylists.get(i)) {

                setPlaylist(utilizadorAtual.getPlaylistsProprias().get(i));

                if (playlist.isVisibilidade()) {
                    interfaceCliente.getBtnAlterarVisibilidade().setText("Privada");
                    interfaceCliente.revalidate();
                    interfaceCliente.getBtnAlterarVisibilidade().revalidate();
                    interfaceCliente.repaint();
                    interfaceCliente.getBtnAlterarVisibilidade().repaint();

                } else {
                    interfaceCliente.getBtnAlterarVisibilidade().setText("Pública");
                    interfaceCliente.revalidate();
                    interfaceCliente.getBtnAlterarVisibilidade().revalidate();
                    interfaceCliente.repaint();
                    interfaceCliente.getBtnAlterarVisibilidade().repaint();
                }
                interfaceCliente.setLblTabela("Playlist: " + utilizadorAtual.getPlaylistsProprias().get(i).getNome());
                String[] headers = {"Nome", "Artista", "Género", "Rating", ""};
                setHeader(headers);
                setPlaylist(utilizadorAtual.getPlaylistsProprias().get(i));
                printMusicas(utilizadorAtual.getPlaylistsProprias().get(i).getMusicas());
                playlist = utilizadorAtual.getPlaylistsProprias().get(i);
                interfaceCliente.getBtnRemoverPlaylist().setVisible(true);
                interfaceCliente.getBtnAlterarVisibilidade().setVisible(true);
                interfaceCliente.getLblAlterarVisibilidade().setVisible(true);
            }
        }

    }

    public JTable getTable() {
        return table;
    }

    public void setListaMusicasAtual(ArrayList<Musica> listaMusicasAtual) {
        this.listaMusicasAtual = listaMusicasAtual;
    }

    public ArrayList<Musica> getListaMusicasAtual() {
        return listaMusicasAtual;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void setPanelPesquisa(PesquisaPanel panelPesquisa) {
        this.panelPesquisa = panelPesquisa;
    }

    public void setHeader(String[] headers) {
        model.setColumnIdentifiers(headers);
    }

    public void alinharTable() {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    public JPopupMenu getPopupMenuLoja() {
        return popupMenuLoja;
    }

    public JMenuItem getMenuBiblioteca1() {
        return menuBiblioteca1;
    }

    public ArrayList<Playlist> getPlaylistsPopMenu() {
        return playlistsPopMenu;
    }

    public JPopupMenu getPopupMenuBiblioteca() {
        return popupMenuBiblioteca;
    }

    public ArrayList<JMenuItem> getMenuBiblioteca11() {
        return menuBiblioteca11;
    }

    public void setPanelCarrinho(PanelCarrinho panelCarrinho) {
        this.panelCarrinho = panelCarrinho;
    }

    public void addPlaylistPopMenu(Playlist playlist) {
        playlistsPopMenu.add(playlist);
    }

    private void setOrdemTabela() {

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        sorter.setSortable(0, true);
        sorter.setSortable(1, true);
        sorter.setSortable(2, false);
        sorter.setSortable(3, false);
        sorter.setSortable(4, false);
        if (interfaceCliente.getLblTabela().getText().equals("Loja:")) {
            sorter.setSortable(5, false);
        }
    }

    public void updateActionsListeners() {
        for (int i = 0; i < menuBiblioteca11.size(); i++) {

            Object clicked = menuBiblioteca11.get(i);
            menuBiblioteca11.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Playlist playlistMenu = playlistsPopMenu.get(menuBiblioteca11.indexOf(clicked));
                    if (musiceSelecionada.isEstadoAtividade()) {
                        if (playlistMenu.addMusica(musiceSelecionada)) {
                            JOptionPane.showMessageDialog(interfaceCliente, "Música adicionada à playlist à " + playlistMenu.getNome());
                        } else {
                            JOptionPane.showMessageDialog(interfaceCliente, "Esta música já se encontra na playlist selecionada");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(interfaceCliente,"A música selecionada não está disponível para ser adicionada a playlists");
                    }
                }
            });
        }
    }
}



