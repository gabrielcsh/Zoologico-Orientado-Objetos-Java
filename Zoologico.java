package Zoooo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class Zoologico {	
	
	private HashMap<String, Animal> animais;
	private HashMap<String, Integer> especies;

	public Zoologico() {
		animais = new HashMap<>();
		especies = new HashMap<>();
	}
	
	// Insere animal no zoológico
	public boolean insereAnimal(Animal a) {
		
		String codEspecie, numAnimal; 
		String especie = a.getEspecie();
		
		// Se o código único já existir, cancela inserção
		if (animais.containsKey(a.getCodUnico())) 
			return false;
		
		// Adiciona nova espécie caso não exista no HashMap
		especie = especie.toUpperCase();
		if (!especies.containsKey(especie)) {
			especies.put(especie, 0);
		}
		especies.computeIfPresent(especie, (k, v) -> v + 1); // Incrementa número de animais da espécie 
		
		// Define codUnico do animal
		codEspecie = especie.substring(0, 3); // 3 primeiros caracteres da espécie
		numAnimal = String.format("%04d", (especies.get(especie))); // Número do animal com 4 casas
		a.setCodUnico(codEspecie + '-' + numAnimal); // Seta codUnico concatenado em maiúsculo

		animais.put(a.getCodUnico(), a);		
		return true;
	}
	
	public void removeAnimal(String codUnico) throws CodUnicoException {
		Animal a = animais.get(codUnico.toUpperCase());
		if (a == null) throw new CodUnicoException("Código único de animal inválido.");
		animais.remove(codUnico.toUpperCase());
	}
	
	// Retorna animal pelo codUnico
	private Animal getAnimal(String codUnico) throws CodUnicoException {
		Animal a = animais.get(codUnico.toUpperCase());
		if (a == null) throw new CodUnicoException("Código único de animal inválido.");
		return a;
	}
	
	// Busca animal no zoológico pelo codUnico
	public boolean buscaAnimal(String codUnico) throws CodUnicoException {
		Animal a = getAnimal(codUnico);
		if(a != null) {
			a.exibeInformacoes();
			return true;
		}
		System.out.println("Código de animal não encontrado!");
		return false;
	}
	
	// Preenche informações de animal pelo codUnico
	public void preencheInformacoes(String codUnico, String data, String sexo, String coloracao, String alimentacao) throws CodUnicoException {
		Animal a = getAnimal(codUnico);
		a.setDataDeNascimento(data);
		a.setSexo(sexo);
		a.setColoracao(coloracao);
		a.setAlimentacao(alimentacao);
	}
	
	// Solicita cudados médicos para animal por codUnico
	public void solicitaCuidados(String codUnico) throws CodUnicoException {
		Animal a = getAnimal(codUnico);
		a.setPrecisaDeCuidados(true);
	}
	
	// Solicita limpeza para animal por codUnico
	public void solicitaLimpeza(String codUnico) throws CodUnicoException {
		Animal a = getAnimal(codUnico);
		a.setNumVisitantes(0); // Zera os visitantes para o animal
		
		if(a instanceof AnimalAquatico) {
			((AnimalAquatico) a).setUltimaLimpezaAquario();
			System.out.println("Solicitada limpeza de aquário do animal " + codUnico);
		}
		
		else if(a instanceof AnimalTerrestre) {
			((AnimalTerrestre) a).setUltimoBanho();
			System.out.println("Solicitado banho para o animal " + codUnico);
		}
		
		else {
			((AnimalAereo) a).setUltimoBanho();
			System.out.println("Solicitado banho para o animal " + codUnico);
		}
	}
	
	// Adicionar visitante ao animal
	public boolean adicionaVisitante(String codUnico, int numVisitantes) throws CodUnicoException, DataNascimentoException {
		
		Animal a = getAnimal(codUnico);
		
		int totalVisitantes = a.getNumVisitantes() + numVisitantes;
		
		if(numVisitantes <= 0) {
			System.out.println("Não é possível adicionar menos do que um visitante!");
			return false;
		}
			
		else if(a instanceof AnimalAquatico) {
			a.setNumVisitantes(totalVisitantes);
			return true;
		}
		
		else if (a instanceof AnimalAereo){
			if(((AnimalAereo) a).verificaLimiteVisitantes()) {
				a.setNumVisitantes(totalVisitantes);
				return true;
			}
		}
		
		else {
			if(((AnimalTerrestre) a).verificaLimiteVisitantes()) {
				a.setNumVisitantes(totalVisitantes);
				return true;
			}
		}
		System.out.println("O número limite de visitantes foi atingido");
		return false;
	}
	
	// Remover visitante ao animal
	public boolean removeVisitante(String codUnico, int numVisitantes) throws CodUnicoException {
		Animal a = getAnimal(codUnico);
		if((a.getNumVisitantes() - numVisitantes) < 0) {
			System.out.println("O animal não tem " + numVisitantes + " visitantes!");
			return false;
		}
		a.setNumVisitantes(a.getNumVisitantes() - numVisitantes);
		System.out.println("Visitante(s) removido(s) com sucesso!");
		return true;
	}
	
	// Imprime relatório 1
	public void relatorio1() {
		// Ordenação de chaves do HashMap
		SortedSet<String> codUnicos = new TreeSet<>(animais.keySet());
		LocalDate ultimaLimpeza;
		
		System.out.println("Quantidade de animais: " + animais.size());
		for (String codUnico : codUnicos) { // Percorre HashMap ascendentemente pelo codUnico
			Animal a = animais.get(codUnico);
			a.exibeInformacoes(); // Exibe informações do animal
			
			// Imprime adicionalmente última limpeza do animal
			if(a instanceof AnimalAquatico)
				ultimaLimpeza = ((AnimalAquatico) a).getUltimaLimpezaAquario();
			
			else if(a instanceof AnimalTerrestre)
				ultimaLimpeza = ((AnimalTerrestre) a).getUltimoBanho();
			
			else
				ultimaLimpeza = ((AnimalAereo) a).getUltimoBanho();
	
			System.out.println("Último banho do animal: " + (ultimaLimpeza == null ? "não informado" : ultimaLimpeza));
			System.out.println();
		}
	}
		
	// Imprime relatório 2
	public void relatorio2(String especie) {
		// Ordenação de chaves do HashMap
		SortedSet<String> codUnicos = new TreeSet<>(animais.keySet());
		
		if (!especies.containsKey(especie.toUpperCase()))
			System.out.println("Não existem animais dessa espécie no zoológico!");
		
		else {
			System.out.println("- Filtrando " + especie.toLowerCase() + "s do zoologico -\n");
			
			for (String codUnico : codUnicos) { // Percorre HashMap ascendentemente pelo codUnico
			   Animal a = animais.get(codUnico);	   
			   if((a.getEspecie()).equalsIgnoreCase(especie))
					System.out.println(a.getCodUnico() + " - " + a.getNome() + " - " + a.cuidadosMedicos());
			}
		}
	}
}