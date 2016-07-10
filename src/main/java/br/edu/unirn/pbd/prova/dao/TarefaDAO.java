package br.edu.unirn.pbd.prova.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.unirn.conexao.Conexao;
import br.edu.unirn.pbd.prova.modelos.Tarefa;

public class TarefaDAO {
	
	public TarefaDAO() throws ClassNotFoundException, SQLException {
		String createTarefa =
				  "CREATE TABLE IF NOT EXISTS tarefa ("
				  + "id serial NOT NULL, "
				  + "titulo text, "
				  + "descricao text,"
				  + "datacriacao date, "
				  + "datafechamento date, "
				  + "porcentagem integer, "
				  + "projeto integer, "
				  + "usuarioabertura integer, "
				  + "usuariofechamento integer, "
				  + "usuarioResponsavel integer, "
				  + "CONSTRAINT tarefa_pkey PRIMARY KEY (id), "
				  + "CONSTRAINT tarefa_projeto_fkey FOREIGN KEY (projeto) "
				  + "REFERENCES projeto (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, "
				  + "CONSTRAINT tarefa_usuarioResponsavel_fkey FOREIGN KEY (usuarioResponsavel) REFERENCES usuario (id) MATCH SIMPLE "
				  + "ON UPDATE NO ACTION ON DELETE NO ACTION, "
				  + "CONSTRAINT tarefa_usuarioabertura_fkey FOREIGN KEY (usuarioabertura) "
				  + "REFERENCES usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, "
				  + "CONSTRAINT tarefa_usuariofechamento_fkey FOREIGN KEY (usuariofechamento) "
				  + "REFERENCES usuario (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)";
		
		Conexao.getInstance().getStatement().executeUpdate(createTarefa);
		Conexao.getInstance().getStatement().close();
	}
	
	public void inserir(Tarefa tarefa) throws SQLException, ClassNotFoundException {
		String insertComDataFechamento = "INSERT INTO TAREFA (titulo, descricao, dataCriacao, "
				+ "porcentagem, projeto, usuarioResponsavel, usuarioAbertura, usuarioFechamento, dataFechamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String insertSemDataFechamento = "INSERT INTO TAREFA (titulo, descricao, dataCriacao, "
				+ "porcentagem, projeto, usuarioResponsavel, usuarioAbertura) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(tarefa.getUsuarioFechamento() == null ? insertSemDataFechamento : insertComDataFechamento);
		preparedStatement.setString(1, tarefa.getTitulo());
		preparedStatement.setString(2, tarefa.getDescricao());
		preparedStatement.setDate(3, new Date(tarefa.getDataCriacao().getTime()));
		preparedStatement.setInt(4, tarefa.getPorcentagem());
		preparedStatement.setInt(5, tarefa.getProjeto().getId());
		preparedStatement.setInt(6, tarefa.getUsuarioResponsavel().getId());
		preparedStatement.setInt(7, tarefa.getUsuarioAbertura().getId());

		if (tarefa.getUsuarioFechamento() != null) {
			preparedStatement.setInt(8, tarefa.getUsuarioFechamento().getId());
			preparedStatement.setDate(9, new Date(tarefa.getDataFechamento().getTime()));
		}
			
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	public Tarefa buscarPorId(int id) throws SQLException, ClassNotFoundException {
		ProjetoDAO projetoDAO = new ProjetoDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		String select = "select * from tarefa where id = ?";
		PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(select);
		preparedStatement.setInt(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		Tarefa tarefa = null;
		while (resultSet.next()) {
			tarefa = new Tarefa();
			tarefa.setId(resultSet.getInt("id"));
			tarefa.setTitulo(resultSet.getString("titulo"));
			tarefa.setDescricao(resultSet.getString("descricao"));
			tarefa.setPorcentagem(resultSet.getInt("porcentagem"));
			tarefa.setDataCriacao(resultSet.getDate("dataCriacao"));
			tarefa.setDataFechamento(resultSet.getDate("dataFechamento") == null ? null : resultSet.getDate("dataFechamento"));
			tarefa.setProjeto(projetoDAO.buscarPorId(resultSet.getInt("projeto")));
			tarefa.setUsuarioResponsavel(usuarioDAO.buscarPorId(resultSet.getInt("usuarioResponsavel")));
			tarefa.setUsuarioAbertura(usuarioDAO.buscarPorId(resultSet.getInt("usuarioAbertura")));
			tarefa.setUsuarioFechamento(resultSet.getInt("usuarioFechamento") > 0 ? usuarioDAO.buscarPorId(resultSet.getInt("usuarioFechamento")) : null);
		}
		
		resultSet.close();
		preparedStatement.close();
		
		return tarefa;
	}
	
	public void alterar(Tarefa tarefa) throws SQLException, ClassNotFoundException {
		String updateComDataFechamento = "update tarefa set titulo = ?, descricao = ?, porcentagem = ?, projeto = ?, usuarioResponsavel = ?, usuarioFechamento = ?, dataFechamento = ? where id = ?";
		String updateSemDataFechamento = "update tarefa set titulo = ?, descricao = ?, porcentagem = ?, projeto = ?, usuarioResponsavel = ? where id = ?";
		
		PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(tarefa.getDataFechamento() == null ? updateSemDataFechamento : updateComDataFechamento);
		preparedStatement.setString(1, tarefa.getTitulo());
		preparedStatement.setString(2, tarefa.getDescricao());
		preparedStatement.setInt(3, tarefa.getPorcentagem());
		preparedStatement.setInt(4, tarefa.getProjeto().getId());
		preparedStatement.setInt(5, tarefa.getUsuarioResponsavel().getId());
		
		if (tarefa.getDataFechamento() != null) {
			preparedStatement.setInt(6, tarefa.getUsuarioFechamento().getId());
			preparedStatement.setDate(7, new Date(tarefa.getDataFechamento().getTime()));
			preparedStatement.setInt(8, tarefa.getId());
		} else {
			preparedStatement.setInt(6, tarefa.getId());
		}
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

}
