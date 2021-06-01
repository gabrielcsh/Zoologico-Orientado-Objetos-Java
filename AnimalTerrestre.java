package Zoooo;

import java.time.LocalDate;

public class AnimalTerrestre extends Animal {
	
	private LocalDate ultimoBanho ;

	public AnimalTerrestre(String nome, String especie) {
		super(nome, especie);
		// TODO Auto-generated constructor stub
	}

	//verifica limite de visitante de acordo com a idade do animal.		
	public boolean verificaLimiteVisitantes() throws DataNascimentoException {
		if (getDataDeNascimento() == null) throw new DataNascimentoException("Data de Nascimento do animal não cadastrada!");
		
		int idade = verificaIdade(getDataDeNascimento());
		if((20 - idade) > 0)
			return true;
		return false;
	}
	public LocalDate getUltimoBanho() {
		return ultimoBanho;
	}

	public void setUltimoBanho() {
		LocalDate dataAtual = LocalDate.now();
		this.ultimoBanho = dataAtual;
	}
}