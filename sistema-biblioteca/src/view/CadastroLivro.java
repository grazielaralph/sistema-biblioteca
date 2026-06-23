package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class CadastroLivro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroLivro frame = new CadastroLivro();
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
	public CadastroLivro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 441);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 20, 147));
		contentPane.setToolTipText("oi");
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastre seu livro aqui!!");
		lblNewLabel.setForeground(new Color(255, 20, 147));
		lblNewLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 23));
		lblNewLabel.setBounds(91, 24, 273, 51);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Confirmar"); // Corrigido erro de digitação de "Confrimar"
		btnNewButton.setFont(new Font("High Tower Text", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(240, 255, 255));
		btnNewButton.setForeground(new Color(255, 20, 147));
		btnNewButton.setBounds(122, 303, 109, 25); // Ajustado levemente a altura para o botão ficar melhor posicionado
		
		// --- ADICIONADO: Lógica para voltar à tela principal ao clicar ---
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abre a tela principal
				TelaPrincipal telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				
				// Fecha a janela de cadastro atual
				dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Título:");
		lblNewLabel_2.setForeground(new Color(255, 20, 147));
		lblNewLabel_2.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(78, 93, 132, 27);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(98, 121, 158, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Autor:");
		lblNewLabel_1.setFont(new Font("High Tower Text", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(new Color(255, 20, 147));
		lblNewLabel_1.setBounds(78, 162, 59, 18);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(98, 190, 158, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("ID:");
		lblNewLabel_3.setFont(new Font("High Tower Text", Font.PLAIN, 15));
		lblNewLabel_3.setForeground(new Color(255, 20, 147));
		lblNewLabel_3.setBounds(78, 227, 59, 18);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(98, 255, 158, 18);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Preencha os espaços para finalizar o cadastro");
		lblNewLabel_4.setFont(new Font("Footlight MT Light", Font.PLAIN, 13));
		lblNewLabel_4.setForeground(new Color(255, 20, 147));
		lblNewLabel_4.setBounds(98, 56, 240, 27);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(CadastroLivro.class.getResource("/imagens/icons8-ei-gatinha-100.png")));
		lblNewLabel_5.setBounds(0, 10, 106, 81);
		contentPane.add(lblNewLabel_5);

	}
}