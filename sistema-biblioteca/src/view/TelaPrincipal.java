package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(255, 192, 203));
		comboBox.addItem("Cadastro de Usuário");
		comboBox.addItem("Cadastro de Livro");
		comboBox.addItem("Empréstimo");
		comboBox.addItem("Devolução");
		comboBox.setBounds(85, 168, 180, 25); 
		contentPane.add(comboBox); // <--- LINHA CORRIGIDA: Agora o JComboBox vai aparecer!
		
		JButton btnNewButton = new JButton("CONFIRMAR");
		btnNewButton.setBackground(new Color(240, 255, 255));
		btnNewButton.setForeground(new Color(255, 20, 147));
		btnNewButton.setFont(new Font("Footlight MT Light", Font.PLAIN, 11));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcaoSelecionada = (String) comboBox.getSelectedItem();
				
				if (opcaoSelecionada.equals("Cadastro de Usuário")) {
					CadastroUsuario telaUser = new CadastroUsuario(); 
					telaUser.setVisible(true);
					dispose(); 
					
				} else if (opcaoSelecionada.equals("Cadastro de Livro")) {
					CadastroLivro telaLivro = new CadastroLivro();
					telaLivro.setVisible(true);
					dispose();
					
				} else if (opcaoSelecionada.equals("Empréstimo")) {
					Emprestimo telaEmprestimo = new Emprestimo();
					telaEmprestimo.setVisible(true);
					dispose();
					
				} else if (opcaoSelecionada.equals("Devolução")) {
					Devolução telaDevolucao = new Devolução();
					telaDevolucao.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(122, 231, 105, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Selecione a opção"); 
		lblNewLabel_2.setForeground(new Color(255, 20, 147));
		lblNewLabel_2.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(113, 131, 134, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imagens/icons8-ei-gatinha-100.png")));
		lblNewLabel_3.setBounds(122, 254, 104, 115);
		contentPane.add(lblNewLabel_3);
	}
}