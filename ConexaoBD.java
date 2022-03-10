
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	
	// atributos
	private static Connection conexao = null;
	//A variável fonte recebe o mesmo nome da base de dados criada no MySQL
	private String fonte = "projeto_final";
	
	//Conexao Para a Base de Dados do PostgresSQL utilizando JDBC
	private ConexaoBD() {
	
		try {
		
		//Driver para fazer conexao com um Banco mysql
		
		//Class.forName("org.mysql.Driver");
		
		//comando para fazer conexao via JDBC com um banco postgresql
		//sendo informado o servidor e sua porta, no caso localhost na porta 5432
		// + o nome da base de dados, o usuario e a senha.
		
		conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + fonte, "root", "123456");
		
		//} catch (ClassNotFoundException e) {
			//e.printStackTrace();
			//System.out.println("Ocorreu um erro de classe não encontrada!!!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro na conexão com o banco de dados!!!");
		} 
	}
	
	
	public static Connection getInstance() {
		if (conexao == null) {
			new ConexaoBD();
		}
		return conexao;
	}
}


/* 
 * EXPLICAÃ‡Ã•ES E COMENTÃ�RIOS SOBRE O CÃ“DIGO
 *
 
 
public class ConexaoBD {

	// atributos
	private static Connection conexao = null;
	//esta Ã© a variÃ¡vel fonte recebe o mesmo nome da base de dados
	//criada no postgresql
	private String fonte = "MiniCurso";
	//Conexao Para a Base de Dados do PostgresSQL utilizando JDBC
	private ConexaoBD() {
	
		try {
		
		//Driver para fazer conexao com um Banco mysql
		
		Class.forName("org.mysql.Driver");
		
		//comando para fazer conexao via JDBC com um banco postgresql
		//sendo informado o servidor e sua porta, no caso localhost na porta 3306
		// + o nome da base de dados, o usuario e a senha.
		
		conexao = DriverManager.getConnection("mysql://localhost:3306/" + fonte, "usuario do banco", "senha do banco");
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro de class nÃ£o encontrada!!!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um erro na conexao com o banco de dados!!!");
		}
	}
	
	
	
	public static Connection getInstance() {
	
		if (conexao == null) {
			new ConexaoBD();
		}
		
		return conexao;
		
	}
}


 * 
 * 
 */