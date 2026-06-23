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
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Emprestimo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Emprestimo frame = new Emprestimo();
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
	public Emprestimo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 436);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		// Corrigido de "Livos" para "Livros"
		JLabel lblNewLabel = new JLabel("Empréstimo de Livros");
		lblNewLabel.setForeground(new Color(255, 20, 147));
		lblNewLabel.setFont(new Font("Footlight MT Light", Font.BOLD, 26));
		lblNewLabel.setBounds(47, 79, 246, 42);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(89, 164, 147, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Digite o Livro que você deseja emprestar:");
		lblNewLabel_1.setForeground(new Color(255, 20, 147));
		lblNewLabel_1.setBackground(new Color(255, 20, 147));
		lblNewLabel_1.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(28, 122, 274, 32);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 275, 147, 25);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Data da devolução");
		lblNewLabel_2.setForeground(new Color(255, 20, 147));
		lblNewLabel_2.setFont(new Font("Footlight MT Light", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(98, 238, 129, 25);
		contentPane.add(lblNewLabel_2);
		
		// Botão Sair (instanciado antes para podermos mudar a visibilidade dele no botão Confirmar)
		JButton btnSair = new JButton("Sair ");
		btnSair.setVisible(false); 
		btnSair.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        TelaPrincipal telaPrincipal = new TelaPrincipal();
		        telaPrincipal.setVisible(true);
		        dispose(); 
		    }
		});
		btnSair.setBackground(new Color(240, 255, 240)); // Alterado para dar contraste ao texto rosa
		btnSair.setForeground(new Color(255, 20, 147));
		btnSair.setFont(new Font("High Tower Text", Font.PLAIN, 13));
		btnSair.setBounds(125, 323, 75, 25); // Ajustado a largura para o texto caber melhor
		contentPane.add(btnSair);

		// Botão Confirmar (Único e corrigido)
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        java.time.LocalDate hoje = java.time.LocalDate.now();
		        int diasParaDevolucao = 15;
		        java.time.LocalDate dataDevolucao = hoje.plusDays(diasParaDevolucao);
		        
		        java.time.format.DateTimeFormatter formatador = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
		        String dataFormatada = dataDevolucao.format(formatador);
		        
		        textField_1.setText(dataFormatada); 
		        
		        // Mostra o botão de sair após a confirmação
		        btnSair.setVisible(true); 
		    }
		});
		btnConfirmar.setBackground(new Color(240, 255, 240));
		btnConfirmar.setForeground(new Color(255, 20, 147));
		btnConfirmar.setFont(new Font("High Tower Text", Font.PLAIN, 13));
		btnConfirmar.setBounds(112, 203, 98, 25);
		contentPane.add(btnConfirmar);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(Emprestimo.class.getResource("/imagens/icons8-ei-gatinha-100.png")));
		lblNewLabel_5.setBounds(112, 0, 106, 81);
		contentPane.add(lblNewLabel_5);
	}	
}