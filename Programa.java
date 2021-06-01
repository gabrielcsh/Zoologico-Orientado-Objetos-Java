package Zoooo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Programa {
	
	public static void main(String[] args) throws CodUnicoException, DataNascimentoException {
		
		// Inicialização do programa
		Zoologico z = new Zoologico();
		Scanner menu = new Scanner(System.in);
		
		// Variáveis de controle
		int opcaoMenu;
		boolean iniciado = true;
		
		int quantidadeVisitantes;
		String especie, codUnico, data, sexo, coloracao, alimentacao, escolhaOpcao;
		String novoNome, novaEspecie, novoTipo, novoCodigo;
    	
        while(iniciado) {
        	
        	try {
	        	exibeMenu();
	        	opcaoMenu = menu.nextInt();
	        	menu.nextLine();
	        			
	        	switch (opcaoMenu) {
	        		// Adiciona animal no Zoológico
		    		case 1:
		    			System.out.println("Digite o nome do animal: ");
		    			novoNome = menu.nextLine();
			    	        
		    	        System.out.println("Digite a espécie do animal: ");
		    	        novaEspecie = menu.nextLine();
			    	        
		    	        System.out.println("Digite o tipo de animal a ser adicionado: (aéreo, terrestre ou aquatico)");
		       	        novoTipo = menu.nextLine();
			    	        
		    	        if (novoTipo.equalsIgnoreCase("aereo") || novoTipo.equalsIgnoreCase("aéreo"))
		    	        	z.insereAnimal(new AnimalAereo(novoNome, novaEspecie));
			    	        
			   	        else if (novoTipo.equalsIgnoreCase("terrestre"))
			   	        	z.insereAnimal(new AnimalTerrestre(novoNome, novaEspecie));
			    	        
			   	        else if (novoTipo.equalsIgnoreCase("aquatico") || novoTipo.equalsIgnoreCase("aquático"))
			   	        	z.insereAnimal(new AnimalAquatico(novoNome, novaEspecie));
			    	        
		    	        else {
		    	        	System.out.println("Tipo de animal não definido");
		    	        	break;			    	        
		    	        }
		    	        System.out.println("Animal adicionado com sucesso!");
		    	        break; 
	        	
		    	    // Remove animal do zoológico pelo código único do chip
		    		case 2:
		    			System.out.println("Insira o código único do animal: (formato AAA-0000)");
		    			codUnico = menu.nextLine();
		    			z.removeAnimal(codUnico);
		    			System.out.println("Animal removido: " + codUnico);
		    			break;
		    			
		    		// Busca animal no zoológico pelo código único do chip
		    		case 3:
		    			System.out.println("Insira o código único do animal: (formato AAA-0000)");
		    			novoCodigo = menu.nextLine();
		    			z.buscaAnimal(novoCodigo);
		    			break;
		    			
			    	// Preenche informações de animal cadastrado
		    		case 4:
		    			System.out.println("Insira o código único do animal: (formato AAA-0000)");
		    			codUnico = menu.nextLine();
		    			
		    			System.out.println("Informe a data de nascimento: ");
		    			data = menu.nextLine();
		    					
		    			System.out.println("Informe o sexo: ");
		    			sexo = menu.nextLine();
		    			
		    			System.out.println("Informe sua coloracao: ");
		    			coloracao = menu.nextLine();
		    			
		    			System.out.println("Tipo de alimentacao: ");
		    			alimentacao = menu.nextLine();
		    			
		    			z.preencheInformacoes(codUnico, data, sexo, coloracao, alimentacao);
		    			break;
		    		
			    	// Solicitar cuidados médicos
		    		case 5:
		    			System.out.println("Insira o código único do animal: (formato AAA-0000)");
		    			codUnico = menu.nextLine();
		    			z.solicitaCuidados(codUnico);
		    			System.out.println("Solicitado cuidados médicos para o animal " + codUnico);
		    			break;
		    			
		    		// Solicitar limpeza para animal (limpeza de aquáreio - aquático; banho - terrestre e aéreo )
		    		case 6:
		    			System.out.println("Insira o código único do animal: (formato AAA-0000)");
		    			codUnico = menu.nextLine();
		    			z.solicitaLimpeza(codUnico);
		    			break;
		    		
		    		// Adicionar ou remover visitante para animal (terrestre ou aéreo)
		    		case 7:
		    			System.out.println("Insira o código único do animal: (formato AAA-0000)");
		    			codUnico = menu.nextLine();
		    			
		    			System.out.println("Escreva \"adicionar\" ou \"remover\" conforme desejado:");
		    			escolhaOpcao = menu.nextLine();
		    			
		    			if(escolhaOpcao.equalsIgnoreCase("Adicionar")) {
		    				System.out.println("Quantos visitantes deseja adicionar? (Utilizar números)");
		    				quantidadeVisitantes = menu.nextInt();	
		    				menu.nextLine();
		    				z.adicionaVisitante(codUnico, quantidadeVisitantes);
		    			}
		    			else if(escolhaOpcao.equalsIgnoreCase("Remover")) {
		    				System.out.println("Quantos visitantes deseja remover? (Utilizar números)");
		    				quantidadeVisitantes = menu.nextInt();	
		    				menu.nextLine();
		    				z.removeVisitante(codUnico, quantidadeVisitantes);
		    			}
		
		    			else
		    				System.out.println("Opção não encontrada!");
		    			break;
		    			
		    		// 1° Relatório: Ordenação por chip e informações
		    		case 8:
		    			z.relatorio1();
		    			break;
		    				
			    	// 2° Relatório: Filtro por espécie e cuidados médicos	    			
		    		case 9:
		    			System.out.println("Insira a espécie para filtrar os animais: ");
		    			especie = menu.nextLine();
		    			z.relatorio2(especie);
		    			break;
		    			
		            // Finaliza programa
		            case 10:
		                menu.close();
		                iniciado = false;
		                System.out.println("Programa finalizado!");
		                break;
		                
		            default:
		                System.out.println("Opção Inválida!");
		                break;
	    		}
        	} catch (InputMismatchException inputMismatchException) {
        		menu.nextLine(); // descarta entrada para o usuário
				System.out.println("Insira uma opção válida (apenas o número)!");
        	}
        	catch (CodUnicoException codUnicoException) {
				System.out.println("Código único inválido, tente novamente!");
        	}
        	catch (DataNascimentoException dataNascimentoException) {
        		System.out.println("O animal não possui informações cadastradas e não pode receber visitas!");
        		System.out.println("Selecione a opção 4 para preencher as informações do animal.");
        	}
        }
	}
	
	public static void exibeMenu() {
		System.out.println();
		System.out.println("##-- Menu de Opções --##");
        System.out.println("|-----------------------------------------------------------------|");
        System.out.println("| Opção 1 - Adicionar novo animal no zoológico                    |");
        System.out.println("| Opção 2 - Remover animal existente no zoológico                 |");
        System.out.println("| Opção 3 - Buscar animal pelo código único do chip               |");
        System.out.println("| Opção 4 - Preencher informações de animal cadastrado            |");
        System.out.println("| Opção 5 - Solicitar cuidados médicos para animal cadastrado     |");
        System.out.println("| Opção 6 - Solicitar limpeza para animal cadastrado              |"); 
        System.out.println("| Opção 7 - Adicionar ou remover visitante para animal cadastrado |");
        System.out.println("| Opção 8 - Relatório 1: Ordenação por chip e informações         |");
        System.out.println("| Opção 9 - Relatório 2: Filtro por espécie e cuidados médicos    |");
        System.out.println("| Opção 10 - Finalizar programa                                   |");
        System.out.println("|-----------------------------------------------------------------|");
        System.out.println("Digite uma opção: ");
	}
}