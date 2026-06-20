package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CadastroLivro extends JFrame {
    // 1. Declarar os componentes da tela
    private JLabel lblTitulo, lblAutor;
    private JTextField txtTitulo, txtAutor;
    private JButton btnSalvar, btnCancelar;

    public CadastroLivro() {
        // 2. Configurações básicas da janela
        setTitle("Cadastro de Livro");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela
        setLayout(new GridBagLayout()); // Gerenciador de layout organizado
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 3. Inicializar e posicionar os componentes
        lblTitulo = new JLabel("Título:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblTitulo, gbc);

        txtTitulo = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(txtTitulo, gbc);

        lblAutor = new JLabel("Autor:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblAutor, gbc);

        txtAutor = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(txtAutor, gbc);

        // Botões
        btnSalvar = new JButton("Salvar");
        gbc.gridx = 0; gbc.gridy = 2;
        add(btnSalvar, gbc);

        btnCancelar = new JButton("Cancelar");
        gbc.gridx = 1; gbc.gridy = 2;
        add(btnCancelar, gbc);
    }

    // 4. Métodos para o CONTROLLER conseguir pegar os dados digitados
    public String getTxtTitulo() { return txtTitulo.getText(); }
    public String getTxtAutor() { return txtAutor.getText(); }

    // 5. Método para o CONTROLLER escutar o clique do botão
    public void acaoBotaoSalvar(ActionListener ouvinte) {
        btnSalvar.addActionListener(ouvinte);
    }
}