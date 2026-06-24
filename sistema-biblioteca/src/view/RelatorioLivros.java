package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;

public class RelatorioLivros extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color ROSA_FUNDO     = new Color(255, 182, 193);
    private static final Color ROSA_FORTE     = new Color(255, 20, 147);
    private static final Color VERDE_CLARO    = new Color(204, 255, 204);
    private static final Color VERMELHO_CLARO = new Color(255, 204, 204);
    private static final Color BRANCO         = Color.WHITE;

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnAtualizar;
    private JButton btnVoltar;

    public RelatorioLivros() {
        setTitle("Relatório de Livros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 480);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel contentPane = new JPanel(new BorderLayout(0, 0));
        contentPane.setBackground(ROSA_FUNDO);
        contentPane.setBorder(new EmptyBorder(12, 16, 12, 16));
        setContentPane(contentPane);

        // ── Painel superior ───────────────────────────────────────────────
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setOpaque(false);
        painelTopo.setBorder(new EmptyBorder(0, 0, 10, 0));

        btnVoltar = new JButton("← Voltar");
        estilizarBotao(btnVoltar);

        JLabel lblTitulo = new JLabel("Relatório de Livros", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Footlight MT Light", Font.BOLD, 24));
        lblTitulo.setForeground(ROSA_FORTE);

        btnAtualizar = new JButton("↻ Atualizar");
        estilizarBotao(btnAtualizar);

        painelTopo.add(btnVoltar,    BorderLayout.WEST);
        painelTopo.add(lblTitulo,    BorderLayout.CENTER);
        painelTopo.add(btnAtualizar, BorderLayout.EAST);
        contentPane.add(painelTopo,  BorderLayout.NORTH);

        // ── Tabela ────────────────────────────────────────────────────────
        String[] colunas = {
            "ID", "Nome do Livro", "Autor",
            "Disponível", "Usuário", "Dt. Empréstimo", "Dt. Devolução"
        };

        modelo = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        tabela = new JTable(modelo);
        tabela.setFont(new Font("High Tower Text", Font.PLAIN, 14));
        tabela.setRowHeight(26);
        tabela.setGridColor(new Color(255, 160, 180));
        tabela.setShowGrid(true);
        tabela.setFillsViewportHeight(true);
        tabela.setBackground(BRANCO);

        int[] larguras = {35, 200, 150, 85, 150, 110, 110};
        for (int i = 0; i < larguras.length; i++)
            tabela.getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);

        // renderer: colore linha, exibe "Sim"→"Disponível" / "Nao"→"Emprestado"
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                // traduz o valor interno para texto amigável na coluna Disponível
                Object exibir = value;
                if (col == 3) {
                    if ("Sim".equals(value)) exibir = "Disponível";
                    else if ("Nao".equals(value)) exibir = "Emprestado";
                }

                super.getTableCellRendererComponent(t, exibir, isSelected, hasFocus, row, col);

                setHorizontalAlignment(
                    (col == 0 || col == 3 || col == 5 || col == 6)
                        ? SwingConstants.CENTER : SwingConstants.LEFT);

                if (!isSelected) {
                    // lê o valor bruto (não traduzido) para decidir a cor
                    Object dispVal = t.getValueAt(row, 3);
                    boolean disponivel = "Sim".equals(dispVal); // ← compara com "Sim"
                    setBackground(disponivel ? VERDE_CLARO : VERMELHO_CLARO);
                    setForeground(Color.DARK_GRAY);
                } else {
                    setBackground(new Color(255, 105, 180, 120));
                    setForeground(Color.DARK_GRAY);
                }
                return this;
            }
        });

        // cabeçalho
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Footlight MT Light", Font.BOLD, 14));
        header.setBackground(ROSA_FORTE);
        header.setForeground(BRANCO);
        header.setReorderingAllowed(false);
        ((DefaultTableCellRenderer) header.getDefaultRenderer())
            .setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(ROSA_FORTE, 2));
        scroll.getViewport().setBackground(BRANCO);
        contentPane.add(scroll, BorderLayout.CENTER);

        // ── Legenda ───────────────────────────────────────────────────────
        JPanel painelLegenda = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 4));
        painelLegenda.setOpaque(false);
        painelLegenda.setBorder(new EmptyBorder(6, 0, 0, 0));
        painelLegenda.add(legendaItem(VERDE_CLARO,    "Disponível"));
        painelLegenda.add(legendaItem(VERMELHO_CLARO, "Emprestado"));
        contentPane.add(painelLegenda, BorderLayout.SOUTH);
    }

    private void estilizarBotao(JButton btn) {
        btn.setFont(new Font("High Tower Text", Font.PLAIN, 13));
        btn.setForeground(ROSA_FORTE);
        btn.setBackground(new Color(240, 255, 240));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private JPanel legendaItem(Color cor, String texto) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        item.setOpaque(false);
        JLabel quadrado = new JLabel("  ");
        quadrado.setOpaque(true);
        quadrado.setBackground(cor);
        quadrado.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        quadrado.setPreferredSize(new Dimension(16, 16));
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Footlight MT Light", Font.PLAIN, 12));
        label.setForeground(ROSA_FORTE);
        item.add(quadrado);
        item.add(label);
        return item;
    }

    public void preencherTabela(String[][] dados) {
        modelo.setRowCount(0);
        for (String[] linha : dados) {
            String[] linhaTratada = new String[linha.length];
            for (int i = 0; i < linha.length; i++)
                linhaTratada[i] = (linha[i] == null || linha[i].isBlank()
                                   || linha[i].equalsIgnoreCase("null"))
                                  ? "-" : linha[i];
            modelo.addRow(linhaTratada);
        }
    }

    public void acaoBotaoVoltar(ActionListener al)    { btnVoltar.addActionListener(al); }
    public void acaoBotaoAtualizar(ActionListener al) { btnAtualizar.addActionListener(al); }
}