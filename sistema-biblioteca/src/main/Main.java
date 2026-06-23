package main;

import controller.BibliotecaController;
import javax.swing.SwingUtilities;
import view.CadastroLivro;
import view.CadastroUsuario;
import view.Emprestimo;
import view.Devolução;
import view.TelaPrincipal;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. instancia todas as views uma única vez
                CadastroLivro   telaCadastroLivro   = new CadastroLivro();
                CadastroUsuario telaCadastroUsuario  = new CadastroUsuario();
                Emprestimo      telaEmprestimo       = new Emprestimo();
                Devolução       telaDevolucao        = new Devolução();

                // 2. instancia o controller passando todas as views (listeners gerados em cada tela)
                new BibliotecaController(
                    telaCadastroLivro,
                    telaCadastroUsuario,
                    telaEmprestimo,
                    telaDevolucao
                );

                // 3. cria a TelaPrincipal com as mesmas instâncias já conectadas
                TelaPrincipal telaPrincipal = new TelaPrincipal(
                    telaCadastroUsuario,
                    telaCadastroLivro,
                    telaEmprestimo,
                    telaDevolucao
                );

                // 4. salva referência global para o botão sair das outras telas voltarem aqui
                TelaPrincipal.instancia = telaPrincipal;

                // 5. exibe apenas a tela principal para começar
                telaPrincipal.setVisible(true);
            }
        });
    }
}