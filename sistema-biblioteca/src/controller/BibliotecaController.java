package controller;

import model.BibliotecaDAO;
import model.Livro;
import model.Usuario;
import view.CadastroLivro;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {

    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    private int proximoCodigoLivro = 1;

    private CadastroLivro telaCadastroLivro;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

        //construtor
        Livro novoLivro = new Livro(proximoCodigoLivro++, titulo, autor, true, null, null, null);
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

    public void realizarEmprestimo(int matriculaUsuario, int codigoLivro) {

        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios) {
            if (u.getMatricula() == matriculaUsuario) {
                usuarioEncontrado = u;
                break;
            }
        }

        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getCodigo() == codigoLivro) {         
                livroEncontrado = l;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Usuário com matrícula " + matriculaUsuario + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (livroEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Livro com código " + codigoLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!livroEncontrado.isDisponivel()) {
            JOptionPane.showMessageDialog(null,
                "O livro \"" + livroEncontrado.getTitulo() + "\" não está disponível para empréstimo!",
                "Indisponível", JOptionPane.WARNING_MESSAGE);
            return;
        }

        //dados de empréstimo dentro do objeto Livro
        String hoje       = LocalDate.now().format(FORMATTER);
        String devolucao  = LocalDate.now().plusDays(14).format(FORMATTER); 

        livroEncontrado.setDisponivel(false);
        livroEncontrado.setUsuarioEmprestimo(usuarioEncontrado.getNome());
        livroEncontrado.setDataEmprestimo(hoje);
        livroEncontrado.setDataDevolucao(devolucao);

        BibliotecaDAO.salvarLivros(livros);

        JOptionPane.showMessageDialog(null,
            "Empréstimo realizado com sucesso!\n" +
            "Usuário       : " + usuarioEncontrado.getNome() + "\n" +
            "Livro         : " + livroEncontrado.getTitulo() + "\n" +
            "Devolução até : " + devolucao,
            "Empréstimo registrado.", JOptionPane.INFORMATION_MESSAGE);
    }

    public void realizarDevolucao(int codigoLivro) {

        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getCodigo() == codigoLivro) {          
                livroEncontrado = l;
                break;
            }
        }

        if (livroEncontrado == null) {
            JOptionPane.showMessageDialog(null,
                "Livro com código " + codigoLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (livroEncontrado.isDisponivel()) {
            JOptionPane.showMessageDialog(null,
                "Este livro não está emprestado.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String titulo = livroEncontrado.getTitulo();

        // limpa os dados de empréstimo
        livroEncontrado.setDisponivel(true);
        livroEncontrado.setUsuarioEmprestimo(null);
        livroEncontrado.setDataEmprestimo(null);
        livroEncontrado.setDataDevolucao(null);

        BibliotecaDAO.salvarLivros(livros);

        JOptionPane.showMessageDialog(null,
            "Devolução do livro \"" + titulo + "\" registrada com sucesso!",
            "Devolução Confirmada", JOptionPane.INFORMATION_MESSAGE);
    }

    // relatório de livros 
    public String[][] gerarRelatorioLivros() {
        String[][] dados = new String[livros.size()][7];
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            dados[i][0] = String.valueOf(l.getCodigo());   
            dados[i][1] = l.getTitulo();
            dados[i][2] = l.getAutor();
            dados[i][3] = l.isDisponivel() ? "Disponível" : "Emprestado";
            dados[i][4] = l.getUsuarioEmprestimo() != null ? l.getUsuarioEmprestimo() : "-";
            dados[i][5] = l.getDataEmprestimo()    != null ? l.getDataEmprestimo()    : "-";
            dados[i][6] = l.getDataDevolucao()     != null ? l.getDataDevolucao()     : "-";
        }
        return dados;
    }

    // relatório de empréstimos
    public String[][] gerarRelatorioEmprestimos() {
        List<String[]> linhas = new ArrayList<>();
        for (Livro l : livros) {
            if (!l.isDisponivel()) {
                linhas.add(new String[]{
                    l.getUsuarioEmprestimo(),
                    String.valueOf(l.getCodigo()),
                    l.getTitulo(),
                    l.getDataEmprestimo(),
                    l.getDataDevolucao()
                });
            }
        }
        return linhas.toArray(new String[0][]);
    }

    private void carregarDados() {
        livros   = BibliotecaDAO.carregarLivros();
        usuarios = BibliotecaDAO.carregarUsuario();

        for (Livro l : livros) {
            if (l.getCodigo() >= proximoCodigoLivro) {    
                proximoCodigoLivro = l.getCodigo() + 1;
            }
        }
    }

    public List<Livro> getLivros() {
        return new ArrayList<>(livros);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }
}

   