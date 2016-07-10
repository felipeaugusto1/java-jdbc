package br.edu.unirn.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	
	private static Conexao conexao = null;
	private Connection connection;
	
	public static Conexao getInstance() throws ClassNotFoundException {
		if (conexao == null)
			conexao = new Conexao();
		return conexao;
	}
	
	public Conexao() throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		if (connection == null || connection.isClosed())
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/prova-pbd",
					"prova-pbd", 
					"senha123");
			
		
		return connection;
	}
	
	public void criarNovaConexao(boolean autoCommit) throws SQLException, ClassNotFoundException {
		if (!getConnection().isClosed())
			getConnection().close();
		
		connection = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/prova-pbd", 
				"prova-pbd", 
				"senha123");
		
		connection.setAutoCommit(autoCommit);
	}
	
	public Statement getStatement() throws SQLException, ClassNotFoundException {
		return getConnection().createStatement();
	}
	
	public PreparedStatement getPreparedStatement(String consulta) throws SQLException, ClassNotFoundException {
		return getConnection().prepareStatement(consulta);
	}
	
}
