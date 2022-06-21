package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//nome do usuario do sql
	private static final String USERNAME = "root";
	
	//senha do banco
	private static final String PASSWORD = "";
	
	//caminho do banco de dados, porta, nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	//conexao com o banco 
	public static Connection createConnectionToMySQL() throws Exception {
		//faz com que a classe seja carregada pela JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		//cria a conexao com o banco 
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//recuperar uma conexao com o banco
		Connection con = createConnectionToMySQL();
		
		//testar se a conexao � nula
		if(con!=null) {
			System.out.println("Conexao obetida com sucesso!");
			con.close();
		}
	}
	
}
