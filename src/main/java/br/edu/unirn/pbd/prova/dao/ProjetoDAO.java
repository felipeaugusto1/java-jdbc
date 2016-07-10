package br.edu.unirn.pbd.prova.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.unirn.conexao.Conexao;
import br.edu.unirn.pbd.prova.modelos.Projeto;

public class ProjetoDAO {
	
	public ProjetoDAO() throws ClassNotFoundException, SQLException {
		String createProjeto =
				  "CREATE TABLE IF NOT EXISTS projeto ("
				  + "id serial NOT NULL, "
				  + "descricao text, "
				  + "dataInicio date, "
				  + "dataFim date, "
				  + "CONSTRAINT projeto_pkey PRIMARY KEY (id))";
		
		Conexao.getInstance().getStatement().executeUpdate(createProjeto);
		Conexao.getInstance().getStatement().close();
	}
	
	public void inserir(Projeto projeto) throws SQLException, ClassNotFoundException {
		String insert = "INSERT INTO PROJETO (descricao, dataInicio, dataFim) VALUES (?, ?, ?)";
		
		PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(insert);
		preparedStatement.setString(1, projeto.getDescricao());
		preparedStatement.setDate(2, new Date(projeto.getDataInicio().getTime()));
		preparedStatement.setDate(3, new Date(projeto.getDataFim().getTime()));
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	public Projeto buscarPorId(int id) throws SQLException, ClassNotFoundException {
		String select = "select * from projeto where id = ?";
		PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(select);
		preparedStatement.setInt(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Projeto projeto = null;
		while (resultSet.next()) {
			projeto = new Projeto();
			projeto.setId(resultSet.getInt("id"));
			projeto.setDescricao(resultSet.getString("descricao"));
			projeto.setDataInicio(resultSet.getDate("dataInicio"));
			projeto.setDataFim(resultSet.getDate("dataFim"));
		}
		
		resultSet.close();
		preparedStatement.close();
		
		return projeto;
	}

}
