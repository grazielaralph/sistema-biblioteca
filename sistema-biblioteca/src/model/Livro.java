package model;

public class Livro {
	private String titulo;
	private String autor;
	private boolean disponivel;
	private int id;
	
	//construtores
	public Livro() {
		// TODO Auto-generated constructor stub
	}

	public Livro(String titulo, String autor, boolean disponivel, int id) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.disponivel = disponivel;
		this.id = id;
	}
	//encapsulamento
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public int getId() {
		return id;
	}
	
}
