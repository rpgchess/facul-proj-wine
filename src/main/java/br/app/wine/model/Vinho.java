package br.app.wine.model;

public class Vinho {
	private int id;
	private String nome;
	private int ano;
	private String tipo;
	private double valor;
	public Vinho() {
		super();
	}
	public Vinho(int id, String nome, int ano, String type, double valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.tipo = type;
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo= tipo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
