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


}
