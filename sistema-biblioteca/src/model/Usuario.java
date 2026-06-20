package model;

public class Usuario {
	private String nome;
	private int matricula;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nome, int matricula) {
		super();
		this.nome = nome;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMatricula() {
		return matricula;
	}

}
