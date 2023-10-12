package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class AreaComunDaoImpl implements IAreaComunDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public List<AreaComun> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<AreaComun> getQuery = currentSession.createQuery("from areas_comunes", AreaComun.class);
		
		return getQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<AreaComun> findById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		return Optional.ofNullable(currentSession.get(AreaComun.class, id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<AreaComun> findAllByIds(List<Long> ids) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<AreaComun> getQuery = currentSession.createQuery("FROM areas_comunes WHERE id IN :ids", AreaComun.class);

		getQuery.setParameterList("ids", ids);

		return getQuery.getResultList();
	}

	@Override
	@Transactional
	public AreaComun save(AreaComun areaComun) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(areaComun);
	}

	@Override
	@Transactional
	public AreaComun update(AreaComun areaComun) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(areaComun);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		AreaComun areaComunToDelete = currentSession.get(AreaComun.class, id);
		
		if(areaComunToDelete != null) {
			currentSession.remove(areaComunToDelete);
		}

	}

}
