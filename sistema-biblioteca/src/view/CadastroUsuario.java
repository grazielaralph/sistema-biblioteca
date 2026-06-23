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

public class CadastroUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnNewButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastroUsuario frame = new CadastroUsuario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CadastroUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 330, 418);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Cadastro de Usuário ");
        lblNewLabel.setForeground(new Color(255, 20, 147));
        lblNewLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 27));
        lblNewLabel.setBounds(42, 35, 329, 46);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nome:");
        lblNewLabel_1.setForeground(new Color(255, 20, 147));
        lblNewLabel_1.setFont(new Font("High Tower Text", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(80, 91, 61, 21);
        contentPane.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(84, 122, 148, 18);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Matrícula:");
        lblNewLabel_2.setFont(new Font("High Tower Text", Font.PLAIN, 16));
        lblNewLabel_2.setForeground(new Color(255, 20, 147));
        lblNewLabel_2.setBounds(82, 164, 72, 18);
        contentPane.add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setBounds(82, 192, 148, 18);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        btnNewButton = new JButton("Confirmar");
        btnNewButton.setBackground(new Color(240, 255, 255));
        btnNewButton.setForeground(new Color(255, 20, 147));
        btnNewButton.setFont(new Font("High Tower Text", Font.PLAIN, 15));
        btnNewButton.setBounds(101, 244, 107, 25);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/view/imagens/icons8-ei-gatinha-100.png")));
        lblNewLabel_3.setBounds(111, 279, 100, 92);
        contentPane.add(lblNewLabel_3);
    }

    //método para o controller
    public String getTxtNome()      { return textField.getText(); }
    public String getTxtMatricula() { return textField_1.getText(); }

    public void limparCampos() {
        textField.setText("");
        textField_1.setText("");
    }

    public void acaoBotaoSalvar(ActionListener al) {
        btnNewButton.addActionListener(al);
    }
}