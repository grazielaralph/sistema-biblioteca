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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.ImageIcon;

public class Devolução extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtDataDevolucao;
	private JTextField txtAtraso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Devolução frame = new Devolução();
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
	public Devolução() {
		setBackground(new Color(255, 182, 193));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Devolução");
		lblNewLabel.setForeground(new Color(255, 20, 147));
		lblNewLabel.setFont(new Font("Footlight MT Light", Font.PLAIN, 26));
		lblNewLabel.setBounds(111, 28, 169, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Livro que vai ser devolvido:");
		lblNewLabel_1.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(new Color(255, 20, 147));
		lblNewLabel_1.setBounds(85, 82, 180, 32);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(85, 115, 165, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		

		JLabel lblData = new JLabel("Data da Devolução:");
		lblData.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		lblData.setForeground(new Color(255, 20, 147));
		lblData.setBounds(85, 155, 165, 20);
		contentPane.add(lblData);

		txtDataDevolucao = new JTextField();
		txtDataDevolucao.setEditable(false); 
		txtDataDevolucao.setBounds(85, 180, 165, 25);
		contentPane.add(txtDataDevolucao);

		JLabel lblAtraso = new JLabel("Status / Dias de Atraso:");
		lblAtraso.setFont(new Font("Footlight MT Light", Font.PLAIN, 14));
		lblAtraso.setForeground(new Color(255, 20, 147));
		lblAtraso.setBounds(85, 220, 165, 20);
		contentPane.add(lblAtraso);

		txtAtraso = new JTextField();
		txtAtraso.setEditable(false); 
		txtAtraso.setBounds(85, 245, 165, 25);
		contentPane.add(txtAtraso);

		JButton btnDevolver = new JButton("Confirmar Devolução");
		btnDevolver.setFont(new Font("High Tower Text", Font.PLAIN, 13));
		btnDevolver.setForeground(new Color(255, 20, 147));
		btnDevolver.setBackground(new Color(240, 255, 240));
		btnDevolver.setBounds(85, 300, 165, 30);
		contentPane.add(btnDevolver);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("High Tower Text", Font.PLAIN, 13));
		btnSair.setForeground(new Color(255, 20, 147));
		btnSair.setBackground(new Color(240, 255, 240));
		btnSair.setBounds(128, 345, 84, 25);
		btnSair.setVisible(false);
		contentPane.add(btnSair);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon(Devolução.class.getResource("/imagens/icons8-ei-gatinha-100.png")));
		lblNewLabel_5.setBounds(0, 11, 106, 81);
		contentPane.add(lblNewLabel_5);


		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LocalDate hoje = LocalDate.now();
				DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				txtDataDevolucao.setText(hoje.format(formatador));

				LocalDate dataLimiteAcordada = hoje.minusDays(5); 

				long diasDeDiferenca = ChronoUnit.DAYS.between(dataLimiteAcordada, hoje);

				if (diasDeDiferenca > 0) {
					txtAtraso.setText(diasDeDiferenca + " dias de atraso");
					txtAtraso.setBackground(new Color(255, 204, 204));
				} else {
					txtAtraso.setText("No prazo / Sem atraso");
					txtAtraso.setBackground(new Color(204, 255, 204));
				}

								btnSair.setVisible(true);
			}
		});

		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPrincipal tela = new TelaPrincipal();
				tela.setVisible(true);
				dispose();
			}
		});
	}
}