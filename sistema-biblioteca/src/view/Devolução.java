package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class Devolução extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCodigoLivro;
    private JTextField txtDataDevolucao;
    private JTextField txtAtraso;
    private JButton btnDevolver;
    private JButton btnSair;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Devolução frame = new Devolução();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Devolução() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 480); // altura aumentada para caber o ícone
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        // --- Botão Voltar (canto superior esquerdo) ---
        btnVoltar = new JButton("← Voltar");
        btnVoltar.setBackground(new Color(240, 255, 240));
        btnVoltar.setForeground(new Color(255, 20, 147));
        btnVoltar.setFont(new Font("High Tower Text", Font.PLAIN, 13));
        btnVoltar.setBounds(10, 10, 90, 25);
        contentPane.add(btnVoltar);

        // --- Título ---
        JLabel lblTitulo = new JLabel("Devolução");
        lblTitulo.setForeground(new Color(255, 20, 147));
        lblTitulo.setFont(new Font("Footlight MT Light", Font.PLAIN, 26));
        lblTitulo.setBounds(111, 10, 169, 43);
        contentPane.add(lblTitulo);

        // --- Código do livro ---
        JLabel lblCodigo = new JLabel("Código do Livro:");
        lblCodigo.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
        lblCodigo.setForeground(new Color(255, 20, 147));
        lblCodigo.setBounds(85, 62, 180, 25);
        contentPane.add(lblCodigo);

        txtCodigoLivro = new JTextField();
        txtCodigoLivro.setBounds(85, 90, 165, 25);
        contentPane.add(txtCodigoLivro);
        txtCodigoLivro.setColumns(10);

        // --- Data real da devolução ---
        JLabel lblData = new JLabel("Data da Devolução:");
        lblData.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
        lblData.setForeground(new Color(255, 20, 147));
        lblData.setBounds(85, 130, 165, 20);
        contentPane.add(lblData);

        txtDataDevolucao = new JTextField();
        txtDataDevolucao.setEditable(false);
        txtDataDevolucao.setBounds(85, 155, 165, 25);
        contentPane.add(txtDataDevolucao);

        // --- Status / atraso ---
        JLabel lblAtraso = new JLabel("Status / Dias de Atraso:");
        lblAtraso.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
        lblAtraso.setForeground(new Color(255, 20, 147));
        lblAtraso.setBounds(85, 195, 165, 20);
        contentPane.add(lblAtraso);

        txtAtraso = new JTextField();
        txtAtraso.setEditable(false);
        txtAtraso.setBounds(85, 220, 165, 25);
        contentPane.add(txtAtraso);

        // --- Botão Confirmar Devolução ---
        btnDevolver = new JButton("Confirmar Devolução");
        btnDevolver.setFont(new Font("High Tower Text", Font.PLAIN, 13));
        btnDevolver.setForeground(new Color(255, 20, 147));
        btnDevolver.setBackground(new Color(240, 255, 240));
        btnDevolver.setBounds(85, 265, 165, 30);
        contentPane.add(btnDevolver);

        // --- Botão Sair ---
        btnSair = new JButton("Sair");
        btnSair.setFont(new Font("High Tower Text", Font.PLAIN, 13));
        btnSair.setForeground(new Color(255, 20, 147));
        btnSair.setBackground(new Color(240, 255, 240));
        btnSair.setBounds(128, 308, 84, 25);
        btnSair.setVisible(false);
        btnSair.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            dispose();
        });
        contentPane.add(btnSair);

        // --- Ícone da gatinha abaixo do botão Sair, centralizado ---
        JLabel lblImagem = new JLabel();
        lblImagem.setIcon(new ImageIcon(Devolução.class.getResource("/view/imagens/icons8-ei-gatinha-100.png")));
        lblImagem.setBounds(120, 340, 106, 100);
        contentPane.add(lblImagem);
    }

    // métodos para o controller
    public String getTxtCodigoLivro() { return txtCodigoLivro.getText(); }

    public void setTxtDataDevolucaoReal(String data) {
        txtDataDevolucao.setText(data);
    }

    public void setStatusAtraso(String mensagem, boolean emAtraso) {
        txtAtraso.setText(mensagem);
        txtAtraso.setBackground(emAtraso
            ? new Color(255, 204, 204)
            : new Color(204, 255, 204));
        btnSair.setVisible(true);
    }

    public void limparCampos() {
        txtCodigoLivro.setText("");
        txtDataDevolucao.setText("");
        txtAtraso.setText("");
        btnSair.setVisible(false);
    }

    public void acaoBotaoConfirmar(ActionListener al) {
        btnDevolver.addActionListener(al);
    }

    public void acaoBotaoVoltar(ActionListener al) {
        btnVoltar.addActionListener(al);
    }
}