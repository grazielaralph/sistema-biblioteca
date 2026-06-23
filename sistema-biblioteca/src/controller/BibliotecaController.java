package controller;

import model.BibliotecaDAO;
import model.Livro;
import model.Usuario;
import view.CadastroLivro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {

    // lista em memória para armazenar os livros e usuários
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    // matriz de emprestimo
    private int[][] emprestimos = new int[100][2];
    private int totalEmprestimos = 0;

    // contador de IDs para novos livros
    private int proximoIdLivro = 1;

    // view
    private CadastroLivro telaCadastroLivro;

    public BibliotecaController(CadastroLivro telaCadastroLivro) {
        this.telaCadastroLivro = telaCadastroLivro; 
        registrarListeners();
        carregarDados();
    }

    private void registrarListeners() {
        telaCadastroLivro.acaoBotaoSalvar(e -> cadastrarLivro());
    }

    private void cadastrarLivro() {
        String titulo = telaCadastroLivro.getTxtTitulo().trim();
        String autor  = telaCadastroLivro.getTxtAutor().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(
                telaCadastroLivro,
                "Preencha todos os campos obrigatórios.",
                "Erro de validação",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // verifica titulo duplicado
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) { 
                JOptionPane.showMessageDialog(
                    telaCadastroLivro,
                    "Já existe um livro com este título!", 
                    "Duplicado",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
        }

        Livro novoLivro = new Livro(titulo, autor, true, proximoIdLivro++);
        livros.add(novoLivro);

        BibliotecaDAO.salvarLivros(livros);

        JOptionPane.showMessageDialog(
            telaCadastroLivro,
            "Livro \"" + titulo + "\" cadastrado com sucesso!", 
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void cadastrarUsuario(String nome, int matricula) {

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "O nome do usuário é obrigatório!",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (Usuario u : usuarios) {
            if (u.getMatricula() == matricula) {
                JOptionPane.showMessageDialog(null,
                    "Matrícula " + matricula + " já cadastrada!", 
                    "Duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        Usuario novoUsuario = new Usuario(nome.trim(), matricula);
        usuarios.add(novoUsuario);

        BibliotecaDAO.salvarUsuarios(usuarios);

        JOptionPane.showMessageDialog(null,
            "Usuário \"" + nome.trim() + "\" cadastrado com sucesso!",
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    // emprestimo de livros
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
                "Usuário com matrícula " + matriculaUsuario + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE 
            );
            return;
        }

        if (livroEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Livro com ID " + idLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!livroEncontrado.isDisponivel()) {
            JOptionPane.showMessageDialog(null,
                "O livro \"" + livroEncontrado.getTitulo() + "\" não está disponível para empréstimo!",
                "Indisponível", JOptionPane.WARNING_MESSAGE
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
            "Usuário : " + usuarioEncontrado.getNome() + "\n" + 
            "Livro   : " + livroEncontrado.getTitulo(), 
            "Empréstimo registrado.", JOptionPane.INFORMATION_MESSAGE
        );
    }

    // devolução
    public void realizarDevolucao(int idLivro) {

        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getId() == idLivro) { 
                livroEncontrado = l;
                break;
            }
        }

        if (livroEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Livro com ID " + idLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (livroEncontrado.isDisponivel()) {
            JOptionPane.showMessageDialog(null, 
                "Este livro não está emprestado.",
                "Aviso", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // remove da matriz deslocando os registros
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

    // relatórios
    public String[][] gerarRelatorioLivros() {
        String[][] dados = new String[livros.size()][4];
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            dados[i][0] = String.valueOf(l.getId()); 
            dados[i][1] = l.getTitulo();
            dados[i][2] = l.getAutor();
            dados[i][3] = l.isDisponivel() ? "Disponível" : "Emprestado";
        }
        return dados;
    }

    public String[][] gerarRelatorioEmprestimos() {
        String[][] dados = new String[totalEmprestimos][3];
        for (int i = 0; i < totalEmprestimos; i++) {
            int mat     = emprestimos[i][0];
            int idLivro = emprestimos[i][1];

            String tituloLivro = "";
            
            for (Livro l : livros) {
                if (l.getId() == idLivro) {
                    tituloLivro = l.getTitulo();
                    break;
                }
            }

            dados[i][0] = String.valueOf(mat);
            dados[i][1] = String.valueOf(idLivro);
            dados[i][2] = tituloLivro;
        }
        return dados;
    }

    private void carregarDados() {
        livros   = BibliotecaDAO.carregarLivros();
        usuarios = BibliotecaDAO.carregarUsuario();

        // recalcula o próximo ID com base nos livros que já tem
        for (Livro l : livros) {
            if (l.getId() >= proximoIdLivro) {
                proximoIdLivro = l.getId() + 1;
            }
        }
    }

    // getters -> pra view preencher combos/tabelas
    public List<Livro> getLivros() {
        return new ArrayList<>(livros);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}

   