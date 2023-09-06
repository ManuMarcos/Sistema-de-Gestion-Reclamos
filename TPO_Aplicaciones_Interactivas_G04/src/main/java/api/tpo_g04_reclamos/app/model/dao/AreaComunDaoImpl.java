package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AreaComunDaoImpl implements IAreaComunDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IEdificioDao edificioDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<AreaComun> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<AreaComun> getQuery = currentSession.createQuery("from AreaComun", AreaComun.class);
		
		return getQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public AreaComun findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		return currentSession.get(AreaComun.class, id);
	}

	@Override
	@Transactional
	public void save(AreaComun areaComun) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(areaComun);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		AreaComun areaComunToDelete = currentSession.get(AreaComun.class, id);
		
		if(areaComunToDelete != null) {
			currentSession.remove(areaComunToDelete);
		}

	}

}
