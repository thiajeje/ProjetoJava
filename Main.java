/*
 * NOME DO ALUNO: Thiago Ajeje Nunes
 * RA: 202025966
 * 
 * OBSERVAÇÕES:
 * 1º Coluna id_proprietario e id_veiculo são auto increment no banco de dados.
 * 2º Coluna CHN do proprietario e coluna Placa do veículo são unique no banco de dados.
 *  
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Connection conexao = null;

	public static void main(String[] args) throws SQLException {

		String menu = "\n1. Cadastro de proprietário" + "\n2. Remoção de proprietário"
				+ "\n3. Busca de proprietário pelo e-mail"
				+ "\n4. Relatório de todos os proprietários de veículos, considerando o nome do proprietário em ordem alfabética, a placa e a descrição do ou dos veículos os quais ele é proprietário"
				+ "\n5. Altera proprietário" + "\n6. Cadastro de veículo" + "\n7. Remoção de veículo"
				+ "\n8. Busca veículo pela placa"
				+ "\n9. Relatório de todos os veículos de uma dada cor, com o nome do proprietário e a quantidade de portas"
				+ "\n10. Busca veículo pela cor" + "\n11. Busca veículos pela quantidade de portas"
				+ "\n12. Altera veículo" + "\n13. Sair\n\n";

		int opcao = 13;

		Scanner lerOpcao = new Scanner(System.in);

		do {
			System.out.println("\n#############################################################################################");
			System.out.println("----------------------------------------   M E N U   ----------------------------------------");
			System.out.println("#############################################################################################\n\n");
			System.out.println(menu);

			System.out.print("Digite uma opção: ");
			opcao = Integer.parseInt(lerOpcao.next()); // conversor para inteiro primitivo

			switch (opcao) {
			case 1: { // cadastro de proprietário
				System.out.println("\n*************************************************");
				System.out.println("Digite os dados do proprietário a ser cadastrado:");
				System.out.println("*************************************************");
				Proprietario plido = lerDadosProprietario();
				insereProprietario(plido);
				System.out.println("\nProprietário cadastrado!!!\n\n");

				break;
			}

			case 2: { // remocao de proprietario por CNH

				System.out.println("Digite a CNH do proprietário a ser removido: ");
				String cnhDoProprietarioRemovido = lerOpcao.next();
				Proprietario proprietario = consultarProprietarioPorCNH(cnhDoProprietarioRemovido);

				if (proprietario == null) {
					System.out.println("Proprietário não cadastrado!");
				} else {
					ArrayList<Veiculo> listaVeiculo = consultarVeiculoProp(proprietario.getCodProprietario());
					if (listaVeiculo == null || listaVeiculo.isEmpty() ) {
						removerProprietarioPelaCNH(cnhDoProprietarioRemovido);
						System.out.println("Proprietário removido!");
					} else {
						System.out.println("Proprietário possui veículos cadastrados!");
					}
				}

				break;
			}

			case 3: { // busca de proprietario pelo e-mail
				
				System.out.println("Digite o e-mail do proprietário a ser pesquisado: ");
				String emailDoProprietarioBuscado = lerOpcao.next();
				Proprietario proprietario = consultarProprietarioPorEmail(emailDoProprietarioBuscado);
				
				if (proprietario == null) {
					System.out.println("Proprietário não cadastrado!");
				} else {
					System.out.println(proprietario);
				}
				break;
			}
			
			case 4: { // Relatório de todos os proprietários de veículos, considerando o nome do proprietário em ordem alfabética, a placa e a descrição do ou dos veículos os quais ele é proprietário
				ArrayList <Veiculo> vetVeiculo = consultaVeiculos(null);
				
				if (vetVeiculo == null || vetVeiculo.size() == 0) {
					System.out.println("\nNão há veículos cadastrados!");
				} else {
					relatorioFormatadoVeiculo(vetVeiculo); 
				}
				break;
			}
			
			case 5: { // altera proprietário
				
				System.out.println("Digite a CNH do proprietáro a ser alterado: ");
				String cnhBuscada = lerOpcao.next();
				Proprietario proprietario = consultarProprietarioPorCNH(cnhBuscada);
				
				if (proprietario == null) {
					System.out.println("\nProprietário não cadastrado!");
				} else {
					System.out.println(proprietario);
					System.out.println("Digite os novos dados do proprietário: ");
					proprietario = lerDadosProprietario();
					alterarProprietario(proprietario, cnhBuscada);
					System.out.println("\nProprietário alterado!!");
				}
				break;
			}
			
			case 6: { // cadastro de veiculo
				
				System.out.println("\n********************************************");
				System.out.println("Digite os dados do veículo a ser cadastrado:");
				System.out.println("********************************************");
				Veiculo vlido = lerDadosVeiculo();
				Proprietario proprietario = consultarProprietarioPorCNH(vlido.getProprietario().getCnh());

				if (proprietario == null) {
					System.out.println("\nProprietário não cadastrado!");
				} else {
					vlido.setProprietario(proprietario);
					insereVeiculo(vlido);
					System.out.println("\nVeículo cadastrado!\n\n");
				}
				break;
			}

			case 7: { // remocao de veiculo
				System.out.println("Digite a placa do veículo a ser removido: ");
				String placaDoVeiculoRemovido = lerOpcao.next();

				Veiculo veiculo = consultaVeiculoPorPlaca(placaDoVeiculoRemovido);

				if (veiculo == null) {
					System.out.println("\nVeículo não cadastrado!");
				} else {
					removerVeiculoPelaPlaca(placaDoVeiculoRemovido);
					System.out.println("\nVeículo removido!");
				}
				break;
			}
			
			case 8: { // busca veiculo por placa
				System.out.println("Digite a placa do veículo a ser pesquisado: ");
				String placaBuscada = lerOpcao.next();
				Veiculo veiculo = consultaVeiculoPorPlaca(placaBuscada);
				
				if (veiculo == null) {
					System.out.println("\nVeículo não cadastrado!");
				} else {
					System.out.println(veiculo);
				}
				break;
			}
			
			case 9: { // relatório de todos os veículos de uma dada cor, com o nome do proprietário e a quantidade de portas
				
				System.out.println("Digite a cor do(s) veículo(s) a ser buscado: ");
				String corBuscada = lerOpcao.next();
				ArrayList <Veiculo> vetVeiculo = consultaVeiculos(corBuscada);
				
				if (vetVeiculo == null || vetVeiculo.size() == 0) {
					System.out.println("\nCor de veículo não cadastrada!");
				} else {
					relatorioFormatadoVeiculoCor(vetVeiculo); 
				} 
				break;
			}
			
			case 10: { // busca veiculo por cor 
				System.out.println("Digite a cor do veiculo a ser buscado: ");
				String corBuscada = lerOpcao.next();
				ArrayList <Veiculo> vetVeiculo = consultaVeiculoPorCor(corBuscada);
				
				if (vetVeiculo == null || vetVeiculo.size() == 0) {
					System.out.println("\nCor de veículo não cadastrada!");
				} else {
					System.out.println("Veiculos encontrados:");
					for (int i=0; i<vetVeiculo.size(); i++ ) {
						Veiculo veiculo = vetVeiculo.get(i);
						System.out.println(veiculo);
					}
				}
				break;
			}
			
			case 11: { // busca veiculo pela quantidade de portas
				System.out.println("Digite a quantidade de portas do veículo a ser buscado: ");
				int quantPortas = lerOpcao.nextInt();
				ArrayList <Veiculo> vetVeiculo = consultaVeiculoPelaQuantPortas(quantPortas);
				
				if (vetVeiculo == null || vetVeiculo.size() ==0) {
					System.out.println("\nVeículos com " +quantPortas+ " portas não cadastrados!!");
				} else {
					System.out.println("\nVéiculos encontrados:");
					for (int i=0; i<vetVeiculo.size(); i++) {
						Veiculo veiculo = vetVeiculo.get(i);
						System.out.println(veiculo);
					}
				}
				break;
			}
			
			case 12: { // altera veículo
				
				System.out.println("Digite a placa do veículo a ser alterado: ");
				String placaAltera = lerOpcao.next();
				Veiculo veiculoAltera = consultaVeiculoPorPlaca(placaAltera);
				
				if (veiculoAltera == null) {
					System.out.println("\nVeículo não cadastrado!");
				} else {
					System.out.println(veiculoAltera);
					System.out.println("\nDigite os novos dados do veículo: ");
					veiculoAltera = lerDadosVeiculo();
					Proprietario propNovo = consultarProprietarioPorCNH(veiculoAltera.getProprietario().getCnh());
					
					if(propNovo == null) {
						System.out.println("\nNovo proprietário não cadastrado.");
					} else {
						veiculoAltera.setProprietario(propNovo);
						alteraVeiculo(veiculoAltera, placaAltera);
						System.out.println("\nVeículo Alterado!!");
					}
				}
				break;
				
			}
			
			case 13: {// sair
				System.out.println("\nObrigado por utilizar o sistema!");
				break;
			}
			default: {
				System.out.println("\nOpção inválida!!");
				break;
			}

			}// fim do switch

		} while (opcao != 13);

		lerOpcao.close();

	}

	// *****************************************
	// MÉTODOS PARA ENTRADA E SAÍDA PELO CONSOLE
	// *****************************************
	
	public static Proprietario lerDadosProprietario() {

		Scanner ler = new Scanner(System.in);

		String nome, email, sexo = "", cnh;
		float peso;
		
		System.out.print("\nNome.........:");
		nome = ler.nextLine();
		System.out.print("\nEmail.......:");
		email = ler.next();
		System.out.print("\nPeso........:");
		peso = Float.parseFloat(ler.next());

		while (!sexo.equals("M") && !sexo.equals("F")) {
			System.out.print("\nSexo (M/F)..:");
			sexo = ler.next();
			if (!sexo.equals("M") && !sexo.equals("F")) {
				System.out.println("Sexo inválido!");
			}
		}

		System.out.print("\nCNH.........:");
		cnh = ler.next();

		Proprietario p = new Proprietario(0, nome, email, peso, sexo, cnh);

		// ler.close();

		return p;

	}

	public static Veiculo lerDadosVeiculo() {

		Scanner ler = new Scanner(System.in);

		int numPortas = 0;
		String placa, descricao, cor, cnh;

		System.out.print("\nPlaca.............................:");
		placa = ler.nextLine();
		System.out.print("\nDescrição.........................:");
		descricao = ler.nextLine();
		System.out.print("\nCor...............................:");
		cor = ler.nextLine();
		
		System.out.print("\nCNH do Proprietário...............:");
		cnh = ler.nextLine();

		while (numPortas != 2 && numPortas != 4) {
				System.out.print("\nNúmero de Portas (2/4)............:");
			numPortas = ler.nextInt();
			if (numPortas != 2 && numPortas != 4) {
				System.out.println("Número de portas inválido!!");
			}
		}

		Proprietario proprietario = new Proprietario();
		proprietario.setCnh(cnh);

		Veiculo v = new Veiculo(0, placa, descricao, cor, numPortas, proprietario);

		return v;
	}

	private static void relatorioFormatadoVeiculoCor(ArrayList <Veiculo> vetVeiculo) {
		
		String linhaM = "----------------------------------------------------------------------------------------";
		String linhaN = "========================================================================================";
		
		System.out.print("\n" +linhaM);
		System.out.print("\n| Nome\t\t\t| Placa\t\t  | Cor\t\t\t | Quant. Portas\t|");
		System.out.print("\n" +linhaN);
		
		for (int i=0; i<vetVeiculo.size(); i++) {
			Veiculo veiculo = vetVeiculo.get(i);
			System.out.printf("\n| %20s\t| %15s | %17s\t| %d\t\t\t|", veiculo.getProprietario().getNome(), veiculo.getPlaca(), veiculo.getCor(), veiculo.getNumPortas());
		}
		System.out.print("\n" +linhaM);
		System.out.print("\n\n");
				
	}
	
	private static void relatorioFormatadoVeiculo(ArrayList<Veiculo> vetVeiculo) {
		
		String linhaM = "-------------------------------------------------------------------------------------------------";
		String linhaN = "=================================================================================================";
		
		System.out.print("\n" +linhaM);
		System.out.print("\n| CNH\t\t\t| Nome\t\t\t  | Placa\t\t | Descrição\t\t|");
		System.out.print("\n" +linhaN);
		
		for (int i=0; i<vetVeiculo.size(); i++) {
			Veiculo veiculo = vetVeiculo.get(i);
			System.out.printf("\n| %20s\t| %20s\t | %20s\t| %20s\t|", veiculo.getProprietario().getCnh(), veiculo.getProprietario().getNome(), veiculo.getPlaca(), veiculo.getDescricao());
		}
		System.out.print("\n" +linhaM);
		System.out.print("\n\n");
		
	}
	// *******************************************************************
	// MÉTODOS PARA MANIPUÇÃO (PERSISTÊNCIA DOS DADOS) NO BANCO DE DADOS
	// *******************************************************************
	
	public static void insereProprietario(Proprietario plido) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "insert into proprietario (nome, email, peso, sexo, cnh) values (?,?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, plido.getNome());
		stmt.setString(2, plido.getEmail());
		stmt.setFloat(3, plido.getPeso());
		stmt.setString(4, plido.getSexo());
		stmt.setString(5, plido.getCnh());

		stmt.execute();

		stmt.close();

		// não feche a conexao com o banco para que possa continuar cadastrando
		// conexao.close();

	}

	public static void removerProprietarioPelaCNH(String cnhDoProprietarioRemovido) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "delete from proprietario where cnh = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cnhDoProprietarioRemovido);
		stmt.execute();
		stmt.close();
	}

	public static void insereVeiculo(Veiculo vlido) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "insert into veiculo (placa, descricao, cor, num_portas, id_proprietario) values (?,?,?,?,?)";

		PreparedStatement stmt = conexao.prepareStatement(sql);

		stmt.setString(1, vlido.getPlaca());
		stmt.setString(2, vlido.getDescricao());
		stmt.setString(3, vlido.getCor());
		stmt.setInt(4, vlido.getNumPortas());
		stmt.setInt(5, vlido.getProprietario().getCodProprietario());

		stmt.execute();
		stmt.close();
	}

	private static void removerVeiculoPelaPlaca(String placaDoVeiculoRemovido) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "delete from veiculo where placa = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, placaDoVeiculoRemovido);
		stmt.execute();
		stmt.close();
	}

	private static Veiculo consultaVeiculoPorPlaca(String placa) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "select * from veiculo where placa = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, placa);
		ResultSet rs = stmt.executeQuery();
		Veiculo veiculo = null;

		while (rs.next()) {
			
			int codVeiculo = rs.getInt("cod_veiculo");
			String cor = rs.getString("cor");
			String descricao = rs.getString("descricao");
			int portas = rs.getInt("num_portas");
			int idproprietario = rs.getInt("id_proprietario");
			veiculo = new Veiculo();
			
			veiculo.setCodVeiculo(codVeiculo);
			veiculo.setCor(cor);
			veiculo.setDescricao(descricao);
			veiculo.setNumPortas(portas);

			Proprietario proprietario = new Proprietario();
			proprietario.setCodProprietario(idproprietario);

			veiculo.setProprietario(proprietario);
			veiculo.setPlaca(placa);
		}

		return veiculo;

	}

	private static Proprietario consultarProprietarioPorCNH(String cnhConsulta) throws SQLException {
		conexao = ConexaoBD.getInstance();

		String sql = "select * from proprietario where cnh = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, cnhConsulta);
		ResultSet rs = stmt.executeQuery();
		Proprietario prop = null;

		while (rs.next()) {
			int idProprietario = rs.getInt("id_proprietario");
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			String cnh = rs.getString("cnh");
			String sexo = rs.getString("sexo");
			float peso = rs.getFloat("peso");

			prop = new Proprietario(idProprietario, nome, email, peso, sexo, cnh);
		}

		stmt.close();

		return prop;
	}
	
	private static Proprietario consultarProprietarioPorEmail(String emailDoProprietarioBuscado) throws SQLException {
		conexao = ConexaoBD.getInstance();
		
		String sql = "select * from proprietario where email = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, emailDoProprietarioBuscado);
		ResultSet rs =stmt.executeQuery();
		Proprietario prop = null;
		
		while (rs.next()) {
			int idProprietario = rs.getInt("id_proprietario");
			String nome = rs.getString("nome");
			String email = rs.getString("email");
			String cnh = rs.getString("cnh");
			String sexo = rs.getString("sexo");
			float peso = rs.getFloat("peso");
			
			prop = new Proprietario(idProprietario, nome, email, peso, sexo, cnh);	
		}
		
		stmt.close();
		
		return prop;
	}
	
	private static ArrayList <Veiculo> consultaVeiculoPorCor(String corBuscada) throws SQLException {
		ArrayList <Veiculo> vetVeiculo = new ArrayList <Veiculo>();
		
		conexao = ConexaoBD.getInstance();
		
		String sql = "select * from veiculo where cor = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, corBuscada);
		ResultSet rs = stmt.executeQuery();
		Veiculo veiculo = null;
		
		while (rs.next()) {
			int codveiculo = rs.getInt("cod_veiculo");
			String placa = rs.getString("placa");
			String descricao = rs.getString("descricao");
			String cor = rs.getString("cor");
			int portas = rs.getInt("num_portas");
		
			veiculo = new Veiculo ();
			veiculo.setCodVeiculo(codveiculo);
			veiculo.setPlaca(placa);
			veiculo.setDescricao(descricao);
			veiculo.setCor(cor);
			veiculo.setNumPortas(portas);
			
			vetVeiculo.add(veiculo);
		}
		
		stmt.close();
		
		return vetVeiculo;
	}
	
	private static ArrayList <Veiculo> consultaVeiculoPelaQuantPortas(int quantPortas) throws SQLException {
		ArrayList <Veiculo> vetVeiculo = new ArrayList <Veiculo>();
		
		conexao = ConexaoBD.getInstance();
		String sql = "select * from veiculo where num_portas = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, quantPortas);
		ResultSet rs =stmt.executeQuery();
		Veiculo veiculo = null;
		
		while (rs.next()) {
			int codveiculo = rs.getInt("cod_veiculo");
			String placa = rs.getString("placa");
			String descricao = rs.getString("descricao");
			String cor = rs.getString("cor");
			int portas = rs.getInt("num_portas");
		
			veiculo = new Veiculo ();
			veiculo.setCodVeiculo(codveiculo);
			veiculo.setPlaca(placa);
			veiculo.setDescricao(descricao);
			veiculo.setCor(cor);
			veiculo.setNumPortas(portas);
			
			vetVeiculo.add(veiculo);
		}
		
		stmt.close();
		
		return vetVeiculo;
	}
	
	private static void alterarProprietario(Proprietario proprietario, String cnhBuscada) throws SQLException {
		
		conexao = ConexaoBD.getInstance();
		String sql = "update proprietario set nome = ?, email = ?, peso = ?, sexo = ?, cnh = ? where cnh = ?";	
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, proprietario.getNome());
		stmt.setString(2, proprietario.getEmail());
		stmt.setFloat(3, proprietario.getPeso());
		stmt.setString(4, proprietario.getSexo());
		stmt.setString(5, proprietario.getCnh());
		stmt.setString(6, cnhBuscada);

		stmt.execute();

		stmt.close();
	}
	
	private static void alteraVeiculo(Veiculo veiculoAltera, String placaAltera) throws SQLException {
		
		conexao = ConexaoBD.getInstance();	
		String sql = "update veiculo set placa = ?, descricao = ?, cor = ?, num_portas = ?, id_proprietario = ? where placa = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, veiculoAltera.getPlaca());
		stmt.setString(2, veiculoAltera.getDescricao());
		stmt.setString(3, veiculoAltera.getCor());
		stmt.setInt(4, veiculoAltera.getNumPortas());
		stmt.setInt(5, veiculoAltera.getProprietario().getCodProprietario());
		stmt.setString(6, placaAltera);
		
		stmt.execute();
		
		stmt.close();
	}
	
	private static ArrayList <Veiculo> consultarVeiculoProp(int codProprietario) throws SQLException {
		ArrayList<Veiculo> vetVeiculo = new ArrayList <Veiculo>();
		
		conexao = ConexaoBD.getInstance();
		
		String sql = "select * from veiculo where id_proprietario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, codProprietario);
		ResultSet rs = stmt.executeQuery();
		Veiculo veiculo = null;
		
		while (rs.next()) {
			int codveiculo = rs.getInt("cod_veiculo");
			String placa = rs.getString("placa");
			String descricao = rs.getString("descricao");
			String cor = rs.getString("cor");
			int portas = rs.getInt("num_portas");
		
			veiculo = new Veiculo ();
			veiculo.setCodVeiculo(codveiculo);
			veiculo.setPlaca(placa);
			veiculo.setDescricao(descricao);
			veiculo.setCor(cor);
			veiculo.setNumPortas(portas);
			
			vetVeiculo.add(veiculo);
		}
		
		stmt.close();
		
		return vetVeiculo;
	}
	
	private static ArrayList<Veiculo> consultaVeiculos(String corBuscada) throws SQLException {
		ArrayList<Veiculo> vetVeiculo = new ArrayList <Veiculo>();
		
		conexao = ConexaoBD.getInstance();
		
		String sql = 	"select veiculo.*, proprietario.*"
						+ " from veiculo, proprietario"
						+ " where veiculo.id_proprietario = proprietario.id_proprietario";
		
		if(corBuscada != null) {
			sql +=  " and veiculo.cor = ?";
		}
		
		sql +=  " ORDER BY proprietario.nome";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		if(corBuscada != null) {
			stmt.setString(1, corBuscada);			
		}
		ResultSet rs = stmt.executeQuery();
		
		Veiculo veiculo;
		Proprietario proprietario;
		
		while (rs.next()) {
			
			veiculo = new Veiculo(
					rs.getInt("veiculo.cod_veiculo"),
					rs.getString("veiculo.placa"),
					rs.getString("veiculo.descricao"),
					rs.getString("veiculo.cor"),
					rs.getInt("veiculo.num_portas"),
					null
					);
			
			proprietario = new Proprietario(
					rs.getInt("proprietario.id_proprietario"),
					rs.getString("proprietario.nome"),
					rs.getString("proprietario.email"),
					rs.getFloat("proprietario.peso"),
					rs.getString("proprietario.sexo"),
					rs.getString("proprietario.cnh")
					);
			
			veiculo.setProprietario(proprietario);
			vetVeiculo.add(veiculo);
		}
		
		stmt.close();
		
		return vetVeiculo;
	}
	
}
