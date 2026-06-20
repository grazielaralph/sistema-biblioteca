package main;

import javax.swing.SwingUtilities;
import view.CadastroLivro;

public class Main {
    public static void main(String[] args) {
        // O Swing exige que as telas sejam iniciadas dentro desta Thread de evento
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Instancia a tela que criamos
                CadastroLivro telaTestada = new CadastroLivro();
                
                // 2. Faz a janela ficar visível na tela
                telaTestada.setVisible(true);
            }
        });
    }
}