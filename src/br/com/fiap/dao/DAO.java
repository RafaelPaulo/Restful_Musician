package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exceptions.DBCommitException;
import br.com.fiap.exceptions.IdNotFoundException;

/**
 * Inteface que define as funcionalidades 
 * genéricas do DAO (CRUD)
 * 
 * @author Rafael Paulo S. Queiroz 
 *
 * @param <T> - Classe da Entidade
 * @param <K> - Classe do Tipo da chave primária
 */
public interface DAO<T,K> {

	void insert(T entity) throws DBCommitException;
	void update(T entity) throws DBCommitException;
	void delete(K id) throws DBCommitException, 
								IdNotFoundException;
	T findById(K id);
	
	List<T> list();
	
}
