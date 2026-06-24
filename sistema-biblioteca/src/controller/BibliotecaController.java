package controller;

import model.BibliotecaDAO;
import model.Livro;
import model.Usuario;
import view.CadastroLivro;
import view.CadastroUsuario;
import view.Emprestimo;
import view.RelatorioLivros;
import view.TelaPrincipal;
import view.Devolução;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaController {

    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    private int proximoCodigoLivro = 1;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private CadastroLivro    telaCadastroLivro;
    private CadastroUsuario  telaCadastroUsuario;
    private Emprestimo       telaEmprestimo;
    private Devolução        telaDevolucao;
    private TelaPrincipal    telaPrincipal;
    private RelatorioLivros  telaRelatorio;

    public BibliotecaController(CadastroLivro    telaCadastroLivro,
                                CadastroUsuario  telaCadastroUsuario,
                                Emprestimo       telaEmprestimo,
                                Devolução        telaDevolucao,
                                TelaPrincipal    telaPrincipal,
                                RelatorioLivros  telaRelatorio) {

        this.telaCadastroLivro   = telaCadastroLivro;
        this.telaCadastroUsuario = telaCadastroUsuario;
        this.telaEmprestimo      = telaEmprestimo;
        this.telaDevolucao       = telaDevolucao;
        this.telaPrincipal       = telaPrincipal;
        this.telaRelatorio       = telaRelatorio;

        carregarDados();
        registrarListeners();
    }

    private void registrarListeners() {
        telaCadastroLivro  .acaoBotaoSalvar   (e -> cadastrarLivro());
        telaCadastroUsuario.acaoBotaoSalvar   (e -> cadastrarUsuario());
        telaEmprestimo     .acaoBotaoConfirmar(e -> realizarEmprestimo());
        telaDevolucao      .acaoBotaoConfirmar(e -> realizarDevolucao());
        telaCadastroLivro  .acaoBotaoVoltar(e -> voltarParaPrincipal(telaCadastroLivro));
        telaCadastroUsuario.acaoBotaoVoltar(e -> voltarParaPrincipal(telaCadastroUsuario));
        telaEmprestimo     .acaoBotaoVoltar(e -> voltarParaPrincipal(telaEmprestimo));
        telaDevolucao      .acaoBotaoVoltar(e -> voltarParaPrincipal(telaDevolucao));
        telaRelatorio      .acaoBotaoVoltar  (e -> voltarParaPrincipal(telaRelatorio));
        telaRelatorio      .acaoBotaoAtualizar(e -> abrirRelatorio());
    }

    private void cadastrarLivro() {
        String titulo = telaCadastroLivro.getTxtTitulo().trim();
        String autor  = telaCadastroLivro.getTxtAutor().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(telaCadastroLivro,
                "Preencha todos os campos obrigatórios.",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                JOptionPane.showMessageDialog(telaCadastroLivro,
                    "Já existe um livro com este título!",
                    "Duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        int idGerado = proximoCodigoLivro++;
        Livro novoLivro = new Livro(idGerado, titulo, autor, true, null, null, null);
        livros.add(novoLivro);
        BibliotecaDAO.salvarLivros(livros);

        telaCadastroLivro.setTxtId(String.valueOf(idGerado));

        JOptionPane.showMessageDialog(telaCadastroLivro,
            "Livro \"" + titulo + "\" cadastrado com sucesso!\nID: " + idGerado,
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cadastrarUsuario() {
        String nomeRaw = telaCadastroUsuario.getTxtNome();
        String matRaw  = telaCadastroUsuario.getTxtMatricula();

        if (nomeRaw == null || nomeRaw.trim().isEmpty()) {
            JOptionPane.showMessageDialog(telaCadastroUsuario,
                "O nome do usuário é obrigatório!",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int matricula;
        try {
            matricula = Integer.parseInt(matRaw.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(telaCadastroUsuario,
                "Matrícula deve ser um número inteiro!",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        for (Usuario u : usuarios) {
            if (u.getMatricula() == matricula) {
                JOptionPane.showMessageDialog(telaCadastroUsuario,
                    "Matrícula " + matricula + " já cadastrada!",
                    "Duplicado", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        usuarios.add(new Usuario(nomeRaw.trim(), matricula));
        BibliotecaDAO.salvarUsuarios(usuarios);

        JOptionPane.showMessageDialog(telaCadastroUsuario,
            "Usuário \"" + nomeRaw.trim() + "\" cadastrado com sucesso!",
            "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        telaCadastroUsuario.limparCampos();
    }

    private void realizarEmprestimo() {
        String codigoRaw = telaEmprestimo.getTxtCodigoLivro().trim();
        String matRaw    = telaEmprestimo.getTxtMatriculaUsuario().trim();

        if (codigoRaw.isEmpty() || matRaw.isEmpty()) {
            JOptionPane.showMessageDialog(telaEmprestimo,
                "Preencha o código do livro e a matrícula do usuário.",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigoLivro, matricula;
        try {
            codigoLivro = Integer.parseInt(codigoRaw);
            matricula   = Integer.parseInt(matRaw);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(telaEmprestimo,
                "Código e matrícula devem ser números inteiros!",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios) {
            if (u.getMatricula() == matricula) { usuarioEncontrado = u; break; }
        }

        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getCodigo() == codigoLivro) { livroEncontrado = l; break; }
        }

        if (usuarioEncontrado == null) {
            JOptionPane.showMessageDialog(telaEmprestimo,
                "Usuário com matrícula " + matricula + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (livroEncontrado == null) {
            JOptionPane.showMessageDialog(telaEmprestimo,
                "Livro com código " + codigoLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!livroEncontrado.isDisponivel()) {
            JOptionPane.showMessageDialog(telaEmprestimo,
                "O livro \"" + livroEncontrado.getTitulo() + "\" não está disponível!",
                "Indisponível", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String hoje      = LocalDate.now().format(FORMATTER);
        String devolucao = LocalDate.now().plusDays(15).format(FORMATTER);

        livroEncontrado.setDisponivel(false);
        livroEncontrado.setUsuarioEmprestimo(usuarioEncontrado.getNome());
        livroEncontrado.setDataEmprestimo(hoje);
        livroEncontrado.setDataDevolucao(devolucao);

        BibliotecaDAO.salvarLivros(livros);

        telaEmprestimo.setTxtDataDevolucao(devolucao);

        JOptionPane.showMessageDialog(telaEmprestimo,
            "Empréstimo realizado!\n" +
            "Usuário       : " + usuarioEncontrado.getNome() + "\n" +
            "Livro         : " + livroEncontrado.getTitulo() + "\n" +
            "Devolução até : " + devolucao,
            "Empréstimo registrado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void realizarDevolucao() {
        String codigoRaw = telaDevolucao.getTxtCodigoLivro().trim();

        if (codigoRaw.isEmpty()) {
            JOptionPane.showMessageDialog(telaDevolucao,
                "Informe o código do livro a devolver.",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int codigoLivro;
        try {
            codigoLivro = Integer.parseInt(codigoRaw);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(telaDevolucao,
                "Código do livro deve ser um número inteiro!",
                "Erro de validação", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getCodigo() == codigoLivro) { livroEncontrado = l; break; }
        }

        if (livroEncontrado == null) {
            JOptionPane.showMessageDialog(telaDevolucao,
                "Livro com código " + codigoLivro + " não encontrado!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (livroEncontrado.isDisponivel()) {
            JOptionPane.showMessageDialog(telaDevolucao,
                "Este livro não está emprestado.",
                "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String hoje        = LocalDate.now().format(FORMATTER);
        String dtDevolucao = livroEncontrado.getDataDevolucao();
        String statusAtraso;

        try {
            LocalDate prazo = LocalDate.parse(dtDevolucao, FORMATTER);
            long dias = java.time.temporal.ChronoUnit.DAYS.between(prazo, LocalDate.now());
            statusAtraso = dias > 0 ? dias + " dias de atraso" : "No prazo / Sem atraso";
            telaDevolucao.setStatusAtraso(statusAtraso, dias > 0);
        } catch (Exception ex) {
            statusAtraso = "Data de devolução inválida";
            telaDevolucao.setStatusAtraso(statusAtraso, false);
        }

        telaDevolucao.setTxtDataDevolucaoReal(hoje);

        String titulo = livroEncontrado.getTitulo();

        livroEncontrado.setDisponivel(true);
        livroEncontrado.setUsuarioEmprestimo(null);
        livroEncontrado.setDataEmprestimo(null);
        livroEncontrado.setDataDevolucao(null);

        BibliotecaDAO.salvarLivros(livros);

        JOptionPane.showMessageDialog(telaDevolucao,
            "Devolução do livro \"" + titulo + "\" registrada!\n" + statusAtraso,
            "Devolução Confirmada", JOptionPane.INFORMATION_MESSAGE);
    }

    private void voltarParaPrincipal(JFrame telaAtual) {
        telaAtual.setVisible(false);
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setVisible(true);
    }

    public void abrirRelatorio() {
        telaRelatorio.preencherTabela(gerarRelatorioLivros());
        telaRelatorio.setLocationRelativeTo(null);
        telaRelatorio.setVisible(true);
    }

    public String[][] gerarRelatorioLivros() {
        String[][] dados = new String[livros.size()][7];
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            dados[i][0] = String.valueOf(l.getCodigo());
            dados[i][1] = l.getTitulo();
            dados[i][2] = l.getAutor();
            // ← "Sim"/"Nao" para o renderer da tabela detectar corretamente
            dados[i][3] = l.isDisponivel() ? "Sim" : "Nao";
            dados[i][4] = vazio(l.getUsuarioEmprestimo());
            dados[i][5] = vazio(l.getDataEmprestimo());
            dados[i][6] = vazio(l.getDataDevolucao());
        }
        return dados;
    }

    /** Retorna "-" para qualquer valor nulo, vazio ou a string literal "null" */
    private String vazio(String valor) {
        if (valor == null) return "-";
        String v = valor.trim();
        if (v.isEmpty() || v.equalsIgnoreCase("null")) return "-";
        return v;
    }

    public String[][] gerarRelatorioEmprestimos() {
        List<String[]> linhas = new ArrayList<>();
        for (Livro l : livros) {
            if (!l.isDisponivel()) {
                linhas.add(new String[]{
                    vazio(l.getUsuarioEmprestimo()),
                    String.valueOf(l.getCodigo()),
                    l.getTitulo(),
                    vazio(l.getDataEmprestimo()),
                    vazio(l.getDataDevolucao())
                });
            }
        }
        return linhas.toArray(new String[0][]);
    }

    private void carregarDados() {
        livros   = BibliotecaDAO.carregarLivros();
        usuarios = BibliotecaDAO.carregarUsuario();

        for (Livro l : livros) {
            if (l.getCodigo() >= proximoCodigoLivro)
                proximoCodigoLivro = l.getCodigo() + 1;
        }
    }

    public List<Livro>   getLivros()   { return new ArrayList<>(livros); }
    public List<Usuario> getUsuarios() { return new ArrayList<>(usuarios); }
}