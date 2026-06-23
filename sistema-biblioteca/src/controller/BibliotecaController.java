package controller;

import model.BibliotecaDAO;
import model.Livro;
import model.Usuario;
import view.CadastroLivro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class BibliotecaController {

    //lista em memória para armazenar os livros e usuários
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    //matriz de emprestimo
    private int[][] emprestimos = new int[100][2];
    private int totalEmprestimos = 0;

    //contador de IDs para novos usuarios
    private int proximoIdUsuario = 1;

    //view
    private CadastroLivro telaCadastroLivro;

    public BibliotecaCadastroController(CadastroLivro telaCadastroLivro) {
        this.telacadastroLivro = telaCadastroLivro;
        registrarListeners();
        carregarDados();
    }

    //botões
    private void registrarListeners() {
        telaCadastro.acaoBotaoSalvar(e -> salvarLivro());
    }

    //cadastra um novo livro vom os dados recebidos da view
    private void cadastrarLivro() {
        String titulo = telaCadastroLivro.getTxtTitulo().trim();
        String autor = telaCadastroLivro.getTxtAutor().trim();
    }

    if (titulo.isEmpty() || autor.isEmpty()) {
        JOptionPane.showMessageDialog(
            telaCadastroLivro,
            "Preencha todos os campos obrigatórios.",
            "Erro de validação",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    //verifica titulo duplicado
    for(Livro 1: livros) {
        if (1.getTitulo().equalsIgnoreCasse(titulo)) {
            JOptionPane.showMessageDialog(
                telaCadastroLivro,
            "Já existe um livro com este tírulo!",
            "Duplicado",
            JOptionPane.WARNING_MESSAGE
        );
        return;
       }
    }

    Livro novoLivro = new Livro(titulo, autor, true, proximoIdLivro++);
    livros.add(novoLivro);

    BibliotecaDAO.salvarLivro(novoLivro);

    JOptionPane.showMessageDialog(
        telaCadastroLivro,
        "Livro\"" + titulo + "\"cadastrado com sucesso!",
        "Sucesso",
        JOptionPane.INFORMATION_MESSAGE
    );

    public void cadastrarUsuario(String nome, int matricula) {

        if (nome == null || nome.trim().isEmpty()) {
            JoptionPane.showMessageDialog(null,
                "O nome do usuário é obrigatório!",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
                return;
        }

         for (Usuario usuario : usuarios) {
            if (u.getMatricula() == matricula) {
                JOptionPane.showMessageDialog(null,
                    "Matricula" + matricula + "Já cadastrada!",
                    "Ducplicado", JOptionPane.WARNING_MESSAGE);
                    return;
            }
        }

        Usuario novoUsuario = new Usuario(nome, matricula);
        usuarios.add(novoUsuario);

        BibliotecaDAO.salvarUsuarios(usuarios);

        JOptionPane.showMessageDialog(null,
            "Usuário \"" + nome.trim() + "\" cadastrado com sucesso!", 
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }


}


   