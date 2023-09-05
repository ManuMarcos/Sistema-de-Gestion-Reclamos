package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EdificioDaoImpl implements IEdificioDao{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Edificio> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Edificio> getQuery = currentSession.createQuery("from Edificio", Edificio.class);
		
		List<Edificio> edificios = getQuery.getResultList();
		
		return edificios;
	}

	@Override
	@Transactional(readOnly = true)
	public Edificio findById(int id) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Edificio edificio = currentSession.find(Edificio.class, id);
		
		return edificio;
	}

	
	@Override
	@Transactional
	public void save(Edificio edificio) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.persist(edificio);	
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Edificio edificioToDelete = currentSession.get(Edificio.class, id);
		
		if(edificioToDelete != null) {
			currentSession.remove(edificioToDelete);
		}
		
	}

}
