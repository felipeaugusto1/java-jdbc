package br.edu.unirn.pbd.prova.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import br.edu.unirn.conexao.Conexao;
import br.edu.unirn.pbd.prova.modelos.LogTarefa;
import br.edu.unirn.pbd.prova.modelos.Tarefa;

public class LogTarefaDAO {
	
	public LogTarefaDAO() throws ClassNotFoundException, SQLException {
		String createLogTarefa =
				  "CREATE TABLE IF NOT EXISTS " + LogTarefa.getNomeTabela()  +" ("
				  + "id serial NOT NULL, "
				  + "porcentagem text, "
				  + "tarefa integer, "
				  + "usuario integer, "
				  + "CONSTRAINT log_tarefa_pkey PRIMARY KEY (id), "
				  + "CONSTRAINT log_tarefa_tarefa_fkey FOREIGN KEY (tarefa) "
				  + "REFERENCES tarefa (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,"
				  + "CONSTRAINT log_tarefa_usuario_fkey FOREIGN KEY (usuario) "
				  + "REFERENCES usuario (id) MATCH SIMPLE "
				  + " ON UPDATE NO ACTION ON DELETE NO ACTION)";
		
		Conexao.getInstance().getStatement().executeUpdate(createLogTarefa);
		Conexao.getInstance().getStatement().close();
	}
	
	public void inserir(LogTarefa log) {
		String insert = "INSERT INTO " + LogTarefa.getNomeTabela() + " (tarefa, porcentagem, usuario) VALUES (?, ?, ?)";
		
		try {
			Conexao.getInstance().criarNovaConexao(false);
			
			PreparedStatement preparedStatement = Conexao.getInstance().getPreparedStatement(insert);
			preparedStatement.setInt(1, log.getTarefa().getId());
			preparedStatement.setString(2, log.getPorcentagem());
			preparedStatement.setInt(3, log.getUsuario().getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			if ( (log.getPorcentagem() != null && log.getPorcentagem().equals("100%")) || log.getTarefa().getUsuarioResponsavel().getId() != log.getUsuario().getId()); {
				Tarefa tarefa = log.getTarefa();
				
				if (log.getPorcentagem() != null) {
					int porcentagem = Integer.parseInt(log.getPorcentagem().replace("%", ""));
					tarefa.setPorcentagem(porcentagem);
					
					if (log.getPorcentagem().contentEquals("100%")) {
						tarefa.setDataFechamento(new Date());
						tarefa.setUsuarioFechamento(log.getUsuario());
					}
				}
				
				tarefa.setUsuarioResponsavel(log.getUsuario());
				
				alterarTarefa(tarefa);
			}
			
			Conexao.getInstance().getConnection().commit();
		} catch (Exception e) {
			
			try {
				Conexao.getInstance().getConnection().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			
			e.printStackTrace();
		} finally {
			try {
				Conexao.getInstance().criarNovaConexao(true);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private void alterarTarefa(Tarefa tarefa) throws ClassNotFoundException, SQLException {
		TarefaDAO tarefaDAO = new TarefaDAO();
		tarefaDAO.alterar(tarefa);
	}

}
