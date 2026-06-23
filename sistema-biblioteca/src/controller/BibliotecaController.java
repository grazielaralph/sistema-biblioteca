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

    //emprestimo de livros
    public void realizarEmprestimo(int matriculaUsuario, int idLivro) {

        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios) {
            if (u.getMatricula() == matriculaUsuario) {
                usuarioEncontrado = u;
                break;
            }
        }

        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getId() == idLivro) {
                livroEncontrado = l;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Usuario com matricula " + matriculaUsuario + " não encontrado!",
                "Erro", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (livroEcontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Livro com ID " + idLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!livroEncontrado.isDisponivel()) {
            JOptionPane.showMessageDialog(null,
                "Este Livro \"" + livroEncontrado.getTitulo() + "\" não está disponível para empréstimo!",
                "Indisponivel", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (totalEmprestimos >= emprestimos.length) {
            JOptionPane.showMessageDialog(null,
                "Limite de empréstimos atingido!",
                "Limite", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        emprestimos[totalEmprestimos][0] = matriculaUsuario;
        emprestimos[totalEmprestimos][1] = idLivro;
        totalEmprestimos++;
        
        livroEncontrado.setDisponivel(false);
        BibliotecaDAO.salvarLivros(livros);

        JOptionPane.showMessageDialog(null,
            "Empréstimo realizado com sucesso!\n" + 
            "Uusuário : " + usuarioEncontrado.getNome() + "\n" +
            "Livro :" + livroEcontrado.getTitulo(),
            "Empréstimo registrado.", JOptionPane.INFORMATION_MESSAGE
        );

    }

    //devolução
    public void realizarDevolucao(int idLivro) {

        Livro livroEncontado  = null;
        for (livro 1 : livros) {
            if (l.getId == idLivro) {
                livroEncontrado = l;
                break;
            }
        }

        if (livroEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Livro " + idLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!livroEncontrado.isDisponivel()) {
            JOptionPane.showMessage(null,
                "Este livro ainda não foi emprestado.",
                "Aviso", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        //remove da matriz deslocando os registros - estruturas de repetição + matrizes
        for (int i = 0; i < totalEmprestimos; i++) {
            if (emprestimos[i][1] == idLivro) {
                for (int j = i; j < totalEmprestimos - 1; j++) {
                    emprestimos[j][0] = emprestimos[j + 1][0];
                    emprestimos[j][1] = emprestimos[j + 1][1];
                }
                emprestimos[totalEmprestimos - 1][0] = 0;
                emprestimos[totalEmprestimos - 1][1] = 0;
                totalEmprestimos--;
                break;
            }
        }

        livroEncontrado.setDisponivel(true);
        BibliotecaDAO.salvarLivros(livros);
 
        JOptionPane.showMessageDialog(null,
            "Devolução do livro \"" + livroEncontrado.getTitulo() + "\" registrada com sucesso!",
            "Devolução Confirmada", JOptionPane.INFORMATION_MESSAGE);
    }

}


   