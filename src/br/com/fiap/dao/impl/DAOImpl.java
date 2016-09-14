package br.com.fiap.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.DAO;
import br.com.fiap.exceptions.DBCommitException;
import br.com.fiap.exceptions.IdNotFoundException;

/**
 * Classe que implementa as funcionalidades 
 * do DAO genérico
 * 
 * @author Rafael Paulo S. Queiroz
 *
 * @param <T> - Classe da entidade
 * @param <K> - Classe do Tipo da chave primária
 */
public class DAOImpl<T, K> implements DAO<T, K> {

	protected EntityManager em;
	
	private Class<T> classe;

	@SuppressWarnings("unchecked")
	public DAOImpl(EntityManager em) {
		this.em = em;
		classe = 
			(Class<T>) ((ParameterizedType)
					getClass().getGenericSuperclass())
						.getActualTypeArguments()[0];
	}

	@Override
	public void insert(T entity) throws DBCommitException {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao persistir");
		}
	}

	@Override
	public void update(T entity) throws DBCommitException {
		try{
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		}catch(Exception e){
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao atualizar");
		}
	}

	@Override
	public void delete(K id) throws DBCommitException, IdNotFoundException {
		T entity = findById(id);
		if (entity == null) 
			throw new IdNotFoundException();
		try{
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		}catch(Exception e){
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new DBCommitException("Erro ao Remover");
		}
	}

	@Override
	public T findById(K id) {
		return em.find(classe, id);
	}

	@Override
	public List<T> list() {
		return em.createQuery("from " + 
				classe.getName(),classe)
				.getResultList();
	}

}





