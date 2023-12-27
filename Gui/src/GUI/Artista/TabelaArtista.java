package GUI.Artista;

import GUI.Cliente.ClientePlaylists;
import Objetos.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class TabelaArtista extends JPanel implements ActionListener, MouseListener {
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private RockstarInc rockstar;
    private Artista utilizadorAtual;
    private ArrayList<Musica> listaMusicasAtual;
    private ArtistaAlbuns panelAlbuns;
    private ArrayList<JButton> btnAlbuns;
    private InterfaceArtista interfaceArtista;
    private Album album;
    private JPopupMenu popmenuArtista;
    private Musica musicaSelecionada;

    public TabelaArtista(RockstarInc rockstar, Artista utilizadorAtual, ArtistaAlbuns panelAlbuns, InterfaceArtista interfaceArtista) {
        this.rockstar = rockstar;
        this.utilizadorAtual = utilizadorAtual;
        this.interfaceArtista = interfaceArtista;

        setPanelAlbuns(panelAlbuns);

        String[] headers = {"Título", "Album", "Rating", "Preço", "Visibilidade"};
        // Criar o modelo da tabela
        model = new DefaultTableModel();

        model.setColumnIdentifiers(headers);

        setLayout(null);

        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.getTableHeader().setReorderingAllowed(false);

        table.setEnabled(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setOrdemTabela();

        mudarCorRGB(this, 51, 153, 153);
        mudarCorRGB(table, 200, 240, 250);

        Font font = new Font("SansSerif", Font.BOLD, 12);
        table.setFont(font);

        scrollPane = new JScrollPane(table);

        printMusicas(utilizadorAtual.getTotalMusicas());

        scrollPane.setBounds(resizeWidth(0), resizeHeight(0), resizeWidth(465), (listaMusicasAtual.size() + 1) * resizeHeight(23));
        add(scrollPane);

        table.setRowHeight(23);

        table.getTableHeader().setResizingAllowed(false);

        popmenuArtista = new JPopupMenu();

        JMenuItem menuArtista1 = new JMenuItem("Alterar visibilidade");
        menuArtista1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row != -1 && row < listaMusicasAtual.size()) {
                    row = table.convertRowIndexToModel(row);
                    musicaSelecionada = rockstar.musicaSelecionada(utilizadorAtual.getUsername(), table.getModel().getValueAt(row, 0).toString());
                    if (musicaSelecionada.isEstadoAtividade()) {
                        musicaSelecionada.setEstadoAtividade(false);
                    } else {
                        musicaSelecionada.setEstadoAtividade(true);
                    }
                    printMusicas(listaMusicasAtual);
                }
            }
        });

        popmenuArtista.add(menuArtista1);
        popmenuArtista.setSize(resizeWidth(300),resizeHeight(300));
        add(popmenuArtista);

        table.addMouseListener(this);
    }

    public void setHeader(String[] headers) {
        model.setColumnIdentifiers(headers);
    }

    private void setOrdemTabela() {

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        sorter.setSortable(0, false);
        sorter.setSortable(1, false);
        sorter.setSortable(2, true);
        sorter.setSortable(3, true);
    }

    public void printMusicas(ArrayList<Musica> musicas) {
        listaMusicasAtual = musicas;
        scrollPane.setSize(resizeWidth(465), (listaMusicasAtual.size() + 1) * resizeHeight(23));
        model.setRowCount(0);

        setOrdemTabela();

        for (int i = 0; i < musicas.size(); i++) {
            model.addRow(new Object[]{"", "", "", "",""});
        }
        for (int i = 0; i < musicas.size(); i++) {
            model.setValueAt(musicas.get(i).getTitulo(), i, 0);
            if(musicas.get(i).getAlbum()!=null) {
                model.setValueAt(musicas.get(i).getAlbum().getNome(), i, 1);
            }
            else  model.setValueAt("Sem Álbum", i, 1);
            if (musicas.get(i).getRatingMedio() > 0) {
                model.setValueAt(musicas.get(i).getRatingMedio(), i, 2);
            } else {
                model.setValueAt("Sem Rating", i, 2);
            }
            model.setValueAt(musicas.get(i).getPrecoMusica(), i, 3);
            if(musicas.get(i).isEstadoAtividade()){
                model.setValueAt("Ativa",i,4);
            }
            else {
                model.setValueAt("Inativa", i, 4);
            }
        }
        alinharTable();
    }

    public void alinharTable() {
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    public void setPanelAlbuns(ArtistaAlbuns panelAlbuns) {
        this.panelAlbuns = panelAlbuns;
        this.btnAlbuns = panelAlbuns.getBtnListaAlbuns();
        for (int i = 0; i < btnAlbuns.size(); i++) {
            this.btnAlbuns.get(i).addActionListener(this);
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked = e.getSource();

        for (int i = 0; i < btnAlbuns.size(); i++) {
            if (clicked == btnAlbuns.get(i)) {

                interfaceArtista.setLblTabela("Album: " + utilizadorAtual.getAlbuns().get(i).getNome());
                printMusicas(utilizadorAtual.getAlbuns().get(i).getMusicas());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3){
            int row = table.getSelectedRow();

            if (row != -1 && row<listaMusicasAtual.size()) {
                row = table.convertRowIndexToModel(row);

                popmenuArtista.show(e.getComponent(), e.getX(), e.getY());

                int r = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(r, r);

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
}
