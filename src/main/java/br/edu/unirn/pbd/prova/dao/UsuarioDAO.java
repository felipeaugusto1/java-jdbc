package br.edu.unirn.pbd.prova.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.unirn.conexao.Conexao;
import br.edu.unirn.pbd.prova.modelos.Usuario;

public class UsuarioDAO {
	
	public UsuarioDAO() throws ClassNotFoundException, SQLException {
		String createUsuario =
				  "CREATE TABLE IF NOT EXISTS usuario("
				  + "id serial NOT NULL, "
				  + "nome text, "
				  + "email text, "
				  + "senha text, "
				  + "CONSTRAINT usuario_pkey PRIMARY KEY (id))";
		
		Conexao.getInstance().getStatement().executeUpdate(createUsuario);
		Conexao.getInstance().getStatement().close();
	}
	
	public void inserir(Usuario usuario) throws SQLException, ClassNotFoundException {
		String insert = "INSERT INTO USUARIO (nome, email, senha) VALUES (?, ?, ?)";
		
		PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(insert);
		preparedStatement.setString(1, usuario.getNome());
		preparedStatement.setString(2, usuario.getEmail());
		preparedStatement.setString(3, usuario.getSenha());
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	public Usuario buscarPorId(int id) throws SQLException, ClassNotFoundException {
		String select = "select * from usuario where id = ?";
		PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(select);
		preparedStatement.setInt(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Usuario usuario = null;
		while (resultSet.next()) {
			usuario = new Usuario();
			usuario.setId(resultSet.getInt("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}
		
		resultSet.close();
		preparedStatement.close();
		
		return usuario;
	}
	
}
