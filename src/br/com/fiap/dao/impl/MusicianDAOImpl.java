package br.com.fiap.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.MusicianDAO;
import br.com.fiap.entity.Musician;

public class MusicianDAOImpl extends DAOImpl<Musician, Integer> implements MusicianDAO{

	public MusicianDAOImpl(EntityManager em) {
		super(em);
	}

}
