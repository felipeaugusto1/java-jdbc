package br.edu.unirn.pbd.prova.principal;

import java.util.Date;

import br.edu.unirn.pbd.prova.dao.LogTarefaDAO;
import br.edu.unirn.pbd.prova.dao.ProjetoDAO;
import br.edu.unirn.pbd.prova.dao.TarefaDAO;
import br.edu.unirn.pbd.prova.dao.UsuarioDAO;
import br.edu.unirn.pbd.prova.modelos.LogTarefa;
import br.edu.unirn.pbd.prova.modelos.Projeto;
import br.edu.unirn.pbd.prova.modelos.Tarefa;
import br.edu.unirn.pbd.prova.modelos.Usuario;

/**
 * 
 * @author Felipe Augusto Galdino de Almeida
 * matricula: 2016P140131
 *
 */
public class Main {

	public static void main(String[] args) {

		UsuarioDAO usuarioDAO = null;
		ProjetoDAO projetoDAO = null;
		TarefaDAO tarefaDAO = null;
		LogTarefaDAO logTarefaDAO = null;
		
		try {
			usuarioDAO = new UsuarioDAO();
			projetoDAO = new ProjetoDAO();
			tarefaDAO = new TarefaDAO();
			logTarefaDAO = new LogTarefaDAO();
		} catch (Exception e) {
			System.out.println("Erro ao criar DAOs. " +e.getMessage());
		}
		
		try {
			projetoDAO.inserir(new Projeto("projeto descricao 1", new Date(), new Date()));
			projetoDAO.inserir(new Projeto("projeto descricao 2", new Date(), new Date()));
			projetoDAO.inserir(new Projeto("projeto descricao 3", new Date(), new Date()));
		} catch (Exception e) {
			System.out.println("Erro ao inserir projetos. " +e.getMessage());
		}
		
		try {
			usuarioDAO.inserir(new Usuario("felipe 1", "felipe@gmail.com", "123"));
			usuarioDAO.inserir(new Usuario("felipe 2", "felipe@gmail.com", "123"));
		} catch (Exception e) {
			System.out.println("Erro ao inserir usuarios. " +e.getMessage());
		}
		
		try {
			Tarefa tarefa1 = new Tarefa("tarefa 1", "descricao", new Date(), null, 0, projetoDAO.buscarPorId(1), usuarioDAO.buscarPorId(1), usuarioDAO.buscarPorId(1), null);
			Tarefa tarefa2 = new Tarefa("tarefa 2", "descricao", new Date(), null, 0, projetoDAO.buscarPorId(2), usuarioDAO.buscarPorId(2), usuarioDAO.buscarPorId(2), null);
			
			tarefaDAO.inserir(tarefa1);
			tarefaDAO.inserir(tarefa2);
		} catch (Exception e) {
			System.out.println("Erro ao inserir tarefas. " +e.getMessage());
		}
	
		try {
			LogTarefa logTarefa1 = new LogTarefa();
			logTarefa1.setTarefa(tarefaDAO.buscarPorId(1));
			logTarefa1.setPorcentagem("30%");
			logTarefa1.setUsuario(usuarioDAO.buscarPorId(1));
			
			logTarefaDAO.inserir(logTarefa1);
			
			logTarefa1 = new LogTarefa();
			logTarefa1.setTarefa(tarefaDAO.buscarPorId(1));
			logTarefa1.setPorcentagem("100%");
			logTarefa1.setUsuario(usuarioDAO.buscarPorId(1));
			
			logTarefaDAO.inserir(logTarefa1);
			
			Tarefa tarefaPosAlteracao = tarefaDAO.buscarPorId(1);
			System.out.println(tarefaPosAlteracao.toString());
			System.out.println("Tarefa 1 está concluída? " +tarefaPosAlteracao.isConcluida());
			
			
			LogTarefa logTarefa2 = new LogTarefa();
			logTarefa2.setTarefa(tarefaDAO.buscarPorId(2));
			logTarefa2.setPorcentagem("30%");
			logTarefa2.setUsuario(usuarioDAO.buscarPorId(1));
			
			logTarefaDAO.inserir(logTarefa2);
			
			logTarefa2 = new LogTarefa();
			logTarefa2.setTarefa(tarefaDAO.buscarPorId(2));
			logTarefa2.setUsuario(usuarioDAO.buscarPorId(1));
			
			logTarefaDAO.inserir(logTarefa2);
			
			logTarefa2 = new LogTarefa();
			logTarefa2.setTarefa(tarefaDAO.buscarPorId(2));
			logTarefa2.setPorcentagem("100%");
			logTarefa2.setUsuario(usuarioDAO.buscarPorId(1));
			
			logTarefaDAO.inserir(logTarefa2);
			
			tarefaPosAlteracao = tarefaDAO.buscarPorId(2);
			
			System.out.println(tarefaPosAlteracao.toString());
			System.out.println("Tarefa 2 está concluída? " +tarefaPosAlteracao.isConcluida());
		} catch (Exception e) {
			System.out.println("Erro ao inserir logs. " +e.getMessage());
		}
		
		
	}
	
}
