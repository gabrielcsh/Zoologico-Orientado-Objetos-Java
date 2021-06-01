package Zoooo;

import java.time.LocalDate;

public class AnimalAquatico extends Animal {
	private LocalDate ultimaLimpezaAquario ;

	public AnimalAquatico(String nome, String especie) {
		super(nome, especie);
		// TODO Auto-generated constructor stub
	}

	public LocalDate getUltimaLimpezaAquario() {
		return ultimaLimpezaAquario;
	}

	public void setUltimaLimpezaAquario() {
		LocalDate dataAtual = LocalDate.now();
		this.ultimaLimpezaAquario = dataAtual;
	}
}