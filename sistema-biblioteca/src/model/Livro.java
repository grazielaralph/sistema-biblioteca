package model;

public class Livro {
    private int codigo;
    private String titulo;
    private String autor;
    private boolean disponivel;
    private String usuarioEmprestimo;
    private String dataEmprestimo;
    private String dataDevolucao;

    // Construtor
    public Livro(int codigo, String titulo, String autor, boolean disponivel, 
                 String usuarioEmprestimo, String dataEmprestimo, String dataDevolucao) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
        this.usuarioEmprestimo = usuarioEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    // Getters e Setters 
    public int getCodigo() { return codigo; }
    
    public String getTitulo() { return titulo; }
    
    public String getAutor() { return autor; }
    
    public boolean isDisponivel() { return disponivel; }
    
    public String getStringDisponibilidade() {
        return this.disponivel ? "Disponível" : "Não disponível";
    }

    public String getUsuarioEmprestimo() { return usuarioEmprestimo; }
    public void setUsuarioEmprestimo(String usuarioEmprestimo) { this.usuarioEmprestimo = usuarioEmprestimo; }

    public String getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(String dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public String getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(String dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}