package GUI.Artista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TabelaArtista extends JPanel {
    private DefaultTableModel model;
    public TabelaArtista(){
        String[] headers = {"Nome", "Artista", "Género", "Rating", ""};
        // Criar o modelo da tabela
        model = new DefaultTableModel();

        model.setColumnIdentifiers(headers);

        setLayout(null);


    }

    public void setHeader(String[] headers) {
        model.setColumnIdentifiers(headers);
    }
}
