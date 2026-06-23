package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaDAO {
	private static final String ARQUIVO_LIVROS = "livros.csv";
	private static final String ARQUIVO_USUARIOS = "usarios.csv";
	
	
	//manipulacao do arquivo de livros
	
	//gravando no arquivo
	public static void salvarLivros(List<Livro> listaLivros) {
		try(BufferedWriter bw = new BufferedWriter (new FileWriter(ARQUIVO_LIVROS))) {
			for (Livro livro : listaLivros) {
				String linha = livro.getCodigo() + "|" +
                        livro.getTitulo() + "|" +
                        livro.getAutor() + "|" +
                        livro.isDisponivel() + "|" +
                        livro.getUsuarioEmprestimo() + "|" +
                        livro.getDataEmprestimo() + "|" +
                        livro.getDataDevolucao();
				bw.write(linha);
				bw.newLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Erro ao salvar livros: "+e.getMessage());
		}
		
	}
	
	//le os livros gravados no arquivo
	public static List<Livro> carregarLivros(){
		List<Livro> listaLivros = new ArrayList<>();
		File arquivo = new File(ARQUIVO_LIVROS);
		
		if(!arquivo.exists()) {
			return listaLivros; //se o arquivo ainda nao exisir, retorna a lista vazia
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_LIVROS))){
			String linha;
			while((linha = br.readLine()) != null) {
				String[] dados = linha.split("\\|");
				
				int codigo = Integer.parseInt(dados[0]);
                String titulo = dados[1];
                String autor = dados[2];
                boolean disponivel = Boolean.parseBoolean(dados[3]);
                String usuario = dados[4];
                String dtEmprestimo = dados[5];
                String dtDevolucao = dados[6];
				
                Livro livro = new Livro(codigo, titulo, autor, disponivel, usuario, dtEmprestimo, dtDevolucao);
				listaLivros.add(livro);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Erro ao carregar livros: " + e.getMessage());
		}
		
		return listaLivros;
	}
	
	//manipulacao do arquivo de usuarios
	//salvando usuario no arquivo
	public static void salvarUsuarios(List<Usuario> listaUsuarios) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS))){
			for(Usuario user : listaUsuarios) {
				String linha = user.getNome() + "|" + user.getMatricula();
				bw.write(linha);
				bw.newLine();
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//recuperando os usuarios do arquivo
	public static List<Usuario> carregarUsuario(){
		List<Usuario> listaUsuarios = new ArrayList<>();
		File arquivoUsuario = new File(ARQUIVO_USUARIOS);
		
		//verificando se o arquivo ja existe
		if(!arquivoUsuario.exists()) {
			return listaUsuarios;
		}
		
		try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))){
			String linha;
			while((linha = br.readLine()) != null) {
				String[] dados = linha.split("\\|");
				
				String nome = dados[0];
				int matricula 	= Integer.parseInt(dados[1]);
				
				Usuario user = new Usuario(nome, matricula);
				listaUsuarios.add(user);
				
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Erro ao carregar livros: " + e.getMessage());
		}
		
		return listaUsuarios;
	}
		
}
