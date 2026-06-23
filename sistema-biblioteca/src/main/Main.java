package main;

import controller.BibliotecaController;
import javax.swing.SwingUtilities;
import view.CadastroLivro;

public class Main {
    public static void main(String[] args) {
        // O Swing exige que as telas sejam iniciadas dentro desta Thread de evento
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Instancia a tela que criamos
                CadastroLivro telaCadastroLivro = new CadastroLivro();
                
                // 2. Instancia o controller passando a view
                new BibliotecaController(telaCadastroLivro);

                //exibe a tela
                telaCadastroLivro.setVisible(true);
            }
        });
    }
}