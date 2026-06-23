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

public class Emprestimo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCodigoLivro;
    private JTextField txtMatriculaUsuario;
    private JTextField txtDataDevolucao;
    private JButton btnConfirmar;
    private JButton btnSair;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Emprestimo frame = new Emprestimo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Emprestimo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 340, 470);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Empréstimo de Livros");
        lblTitulo.setForeground(new Color(255, 20, 147));
        lblTitulo.setFont(new Font("Footlight MT Light", Font.BOLD, 26));
        lblTitulo.setBounds(47, 79, 246, 42);
        contentPane.add(lblTitulo);

        // --- Código do Livro ---
        JLabel lblCodigo = new JLabel("Código do Livro:");
        lblCodigo.setForeground(new Color(255, 20, 147));
        lblCodigo.setFont(new Font("High Tower Text", Font.PLAIN, 16));
        lblCodigo.setBounds(28, 135, 160, 25);
        contentPane.add(lblCodigo);

        txtCodigoLivro = new JTextField();
        txtCodigoLivro.setBounds(89, 165, 147, 25);
        contentPane.add(txtCodigoLivro);
        txtCodigoLivro.setColumns(10);

        // --- Matrícula do Usuário ---
        JLabel lblMatricula = new JLabel("Matrícula do Usuário:");
        lblMatricula.setForeground(new Color(255, 20, 147));
        lblMatricula.setFont(new Font("High Tower Text", Font.PLAIN, 16));
        lblMatricula.setBounds(28, 200, 190, 25);
        contentPane.add(lblMatricula);

        txtMatriculaUsuario = new JTextField();
        txtMatriculaUsuario.setBounds(89, 230, 147, 25);
        contentPane.add(txtMatriculaUsuario);
        txtMatriculaUsuario.setColumns(10);

        // --- Data de Devolução (preenchida pelo controller) ---
        JLabel lblData = new JLabel("Data da devolução:");
        lblData.setForeground(new Color(255, 20, 147));
        lblData.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
        lblData.setBounds(89, 268, 160, 25);
        contentPane.add(lblData);

        txtDataDevolucao = new JTextField();
        txtDataDevolucao.setEditable(false);
        txtDataDevolucao.setBounds(89, 298, 147, 25);
        contentPane.add(txtDataDevolucao);

        // --- Botão Confirmar ---
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(240, 255, 240));
        btnConfirmar.setForeground(new Color(255, 20, 147));
        btnConfirmar.setFont(new Font("High Tower Text", Font.PLAIN, 13));
        btnConfirmar.setBounds(112, 338, 98, 25);
        contentPane.add(btnConfirmar);

        // --- Botão Sair ---
        btnSair = new JButton("Sair");
        btnSair.setVisible(false);
        btnSair.setBackground(new Color(240, 255, 240));
        btnSair.setForeground(new Color(255, 20, 147));
        btnSair.setFont(new Font("High Tower Text", Font.PLAIN, 13));
        btnSair.setBounds(125, 375, 75, 25);
        btnSair.addActionListener(e -> {
            new TelaPrincipal().setVisible(true);
            dispose();
        });
        contentPane.add(btnSair);

        JLabel lblImagem = new JLabel();
        lblImagem.setIcon(new ImageIcon(Emprestimo.class.getResource("/imagens/icons8-ei-gatinha-100.png")));
        lblImagem.setBounds(112, 0, 106, 81);
        contentPane.add(lblImagem);
    }

    //metódo para o controller
    public String getTxtCodigoLivro()      { return txtCodigoLivro.getText(); }
    public String getTxtMatriculaUsuario() { return txtMatriculaUsuario.getText(); }

    public void setTxtDataDevolucao(String data) {
        txtDataDevolucao.setText(data);
        btnSair.setVisible(true);
    }

    public void limparCampos() {
        txtCodigoLivro.setText("");
        txtMatriculaUsuario.setText("");
        txtDataDevolucao.setText("");
        btnSair.setVisible(false);
    }

    public void acaoBotaoConfirmar(ActionListener al) {
        btnConfirmar.addActionListener(al);
    }
}