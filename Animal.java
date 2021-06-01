package Zoooo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Animal {
	private int numVisitantes = 0;
	private String nome;
	private String dataDeNascimento;
	private String especie;
	private String sexo;
	private String coloracao;
	private String habitat;
	private String locomocao;
	private String alimentacao;
	private String codUnico;
	private boolean precisaDeCuidados = false;
	private LocalDate dataAtual = LocalDate.now();
	
	public Animal(String nome, String especie) {
		this.nome = nome;
		this.especie = especie;
	}
	
	// Retorna idade do animal de acordo com a data atual
	public int verificaIdade(String dataDenascimento) {
		this.dataDeNascimento = dataDenascimento;
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate idade = LocalDate.parse(dataDeNascimento, fmt2);
		idade = dataAtual.minusYears(idade.getYear());
		return idade.getYear();
	}
	
	// Exibe informações do animal
	public void exibeInformacoes() {
		System.out.println("Exibindo informações " + codUnico);
		System.out.println("Nome: " + nome);
		System.out.println("Data de nascimento : " + corrigeValorNulo(dataDeNascimento));
		System.out.println("Especie: " + especie);
		System.out.println("Sexo: " + corrigeValorNulo(sexo));
		System.out.println("Coloracao: " + corrigeValorNulo(coloracao));
		System.out.println("Alimentacao: " + corrigeValorNulo(alimentacao));
	}
	
	public static String corrigeValorNulo(String input) {
		return input == null ? "não informado" : input;
	}
	
	public String cuidadosMedicos() {
		if(this.precisaDeCuidados)
			return "Precisa de cuidados médicos";
		else
			return "Não precisa de cuidados médicos";
	}

	public void setNumVisitantes(int numVisitantes) {
		this.numVisitantes = numVisitantes;
	}
	
	public void setLocomocao(String locomocao) {
		this.locomocao = locomocao;
	}

	public void setAlimentacao(String alimentacao) {
		this.alimentacao = alimentacao;
	}
	
	public int getNumVisitantes() {
		return numVisitantes;
	}
	
	public String getLocomocao() {
		return locomocao;
	}

	public String getAlimentacao() {
		return alimentacao;
	}

	public String getNome() {
		return nome;
	}
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public String getEspecie() {
		return especie;
	}
	public String getSexo() {
		return sexo;
	}
	public String getColoracao() {
		return coloracao;
	}
	public String getHabitat() {
		return habitat;
	}
	public String getCodUnico() {
		return codUnico;
	}
	public void setCodUnico(String codUnico) {
		this.codUnico = codUnico;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setColoracao(String coloracoa) {
		this.coloracao = coloracoa;
	}
	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
	public void setPrecisaDeCuidados(boolean precisaDeCuidados) {
		this.precisaDeCuidados = precisaDeCuidados;
	}
}