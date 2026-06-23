package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // views injetadas pelo Main (já conectadas ao controller)
    private CadastroUsuario telaCadastroUsuario;
    private CadastroLivro   telaCadastroLivro;
    private Emprestimo      telaEmprestimo;
    private Devolução       telaDevolucao;
    
    //construtor padrao
    public TelaPrincipal() {}

    public TelaPrincipal(CadastroUsuario telaCadastroUsuario,
                         CadastroLivro   telaCadastroLivro,
                         Emprestimo      telaEmprestimo,
                         Devolução       telaDevolucao) {

        this.telaCadastroUsuario = telaCadastroUsuario;
        this.telaCadastroLivro   = telaCadastroLivro;
        this.telaEmprestimo      = telaEmprestimo;
        this.telaDevolucao       = telaDevolucao;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 358, 428);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bem-Vindo!!");
        lblNewLabel.setForeground(new Color(255, 20, 147));
        lblNewLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 27));
        lblNewLabel.setBounds(91, 23, 173, 52);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Biblioteca da Wepink");
        lblNewLabel_1.setFont(new Font("Footlight MT Light", Font.BOLD, 27));
        lblNewLabel_1.setForeground(new Color(255, 20, 147));
        lblNewLabel_1.setBounds(39, 71, 263, 44);
        contentPane.add(lblNewLabel_1);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(255, 192, 203));
        comboBox.addItem("Cadastro de Usuário");
        comboBox.addItem("Cadastro de Livro");
        comboBox.addItem("Empréstimo");
        comboBox.addItem("Devolução");
        comboBox.setBounds(85, 168, 180, 25);
        contentPane.add(comboBox);

        JLabel lblNewLabel_2 = new JLabel("Selecione a opção");
        lblNewLabel_2.setForeground(new Color(255, 20, 147));
        lblNewLabel_2.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(113, 131, 134, 27);
        contentPane.add(lblNewLabel_2);

        JButton btnNewButton = new JButton("CONFIRMAR");
        btnNewButton.setBackground(new Color(240, 255, 255));
        btnNewButton.setForeground(new Color(255, 20, 147));
        btnNewButton.setFont(new Font("Footlight MT Light", Font.PLAIN, 11));
        btnNewButton.setBounds(122, 205, 105, 25);
        btnNewButton.addActionListener(e -> {
            String opcao = (String) comboBox.getSelectedItem();
            JFrame tela = null;

            switch (opcao) {
                case "Cadastro de Usuário" -> tela = telaCadastroUsuario;
                case "Cadastro de Livro"   -> tela = telaCadastroLivro;
                case "Empréstimo"          -> tela = telaEmprestimo;
                case "Devolução"           -> tela = telaDevolucao;
            }

            if (tela != null) {
                tela.setLocationRelativeTo(null);
                tela.setVisible(true);
                dispose(); // fecha a TelaPrincipal
            }
        });
        contentPane.add(btnNewButton);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/icons8-ei-gatinha-100.png")));
        lblNewLabel_3.setBounds(122, 240, 104, 115);
        contentPane.add(lblNewLabel_3);
    }
}