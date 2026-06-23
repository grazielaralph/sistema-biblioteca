package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CadastroLivro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JButton btnNewButton;
    private JButton btnVoltar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastroLivro frame = new CadastroLivro();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastroLivro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 368, 480);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 20, 147));
        contentPane.setBackground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        // --- Botão Voltar (canto superior esquerdo, sem sobreposição) ---
        btnVoltar = new JButton("← Voltar");
        btnVoltar.setBackground(new Color(240, 255, 240));
        btnVoltar.setForeground(new Color(255, 20, 147));
        btnVoltar.setFont(new Font("High Tower Text", Font.PLAIN, 13));
        btnVoltar.setBounds(10, 10, 90, 25);
        contentPane.add(btnVoltar);

        // --- Título da tela (deslocado para não colidir com o botão voltar) ---
        JLabel lblNewLabel = new JLabel("Cadastre seu livro aqui!!");
        lblNewLabel.setForeground(new Color(255, 20, 147));
        lblNewLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 20));
        lblNewLabel.setBounds(91, 40, 260, 30);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_4 = new JLabel("Preencha os espaços para finalizar o cadastro");
        lblNewLabel_4.setFont(new Font("Footlight MT Light", Font.PLAIN, 13));
        lblNewLabel_4.setForeground(new Color(255, 20, 147));
        lblNewLabel_4.setBounds(55, 72, 280, 20);
        contentPane.add(lblNewLabel_4);

        // --- Título ---
        JLabel lblNewLabel_2 = new JLabel("Título:");
        lblNewLabel_2.setForeground(new Color(255, 20, 147));
        lblNewLabel_2.setFont(new Font("High Tower Text", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(78, 100, 132, 27);
        contentPane.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBounds(98, 127, 158, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        // --- Autor ---
        JLabel lblNewLabel_1 = new JLabel("Autor:");
        lblNewLabel_1.setFont(new Font("High Tower Text", Font.PLAIN, 18));
        lblNewLabel_1.setForeground(new Color(255, 20, 147));
        lblNewLabel_1.setBounds(78, 158, 59, 18);
        contentPane.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(98, 180, 158, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        // --- ID (gerado automaticamente, exibido após confirmar) ---
        JLabel lblNewLabel_3 = new JLabel("ID gerado:");
        lblNewLabel_3.setFont(new Font("High Tower Text", Font.PLAIN, 15));
        lblNewLabel_3.setForeground(new Color(255, 20, 147));
        lblNewLabel_3.setBounds(78, 212, 100, 18);
        contentPane.add(lblNewLabel_3);

        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setBackground(new Color(255, 240, 245)); // fundo diferente para indicar só-leitura
        textField_2.setBounds(98, 232, 158, 22);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        // --- Botão Confirmar ---
        btnNewButton = new JButton("Confirmar");
        btnNewButton.setFont(new Font("High Tower Text", Font.PLAIN, 14));
        btnNewButton.setBackground(new Color(240, 255, 255));
        btnNewButton.setForeground(new Color(255, 20, 147));
        btnNewButton.setBounds(122, 270, 109, 25);
        contentPane.add(btnNewButton);

        // --- Ícone da gatinha embaixo do botão Confirmar ---
        JLabel lblImagem = new JLabel();
        lblImagem.setIcon(new ImageIcon(CadastroLivro.class.getResource("/view/imagens/icons8-ei-gatinha-100.png")));
        lblImagem.setBounds(127, 305, 106, 100);
        contentPane.add(lblImagem);
    }

    // métodos para o controller
    public String getTxtTitulo() { return textField.getText(); }
    public String getTxtAutor()  { return textField_1.getText(); }

    public void setTxtId(String id) { textField_2.setText(id); }

    public void limparCampos() {
        textField.setText("");
        textField_1.setText("");
        textField_2.setText("");
    }

    public void acaoBotaoSalvar(ActionListener al) {
        btnNewButton.addActionListener(al);
    }

    public void acaoBotaoVoltar(ActionListener al) {
        btnVoltar.addActionListener(al);
    }
}