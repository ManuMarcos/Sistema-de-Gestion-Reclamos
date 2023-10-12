package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Unidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UnidadDaoImpl implements IUnidadDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Unidad> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Unidad> getQuery = currentSession.createQuery("from unidades", Unidad.class);
		
		return getQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Unidad> findAllByIds(List<Long> ids) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Unidad> getQuery = currentSession.createQuery("FROM unidades WHERE id IN :ids", Unidad.class);

		getQuery.setParameterList("ids", ids);

		return getQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Unidad> findById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		return Optional.ofNullable(currentSession.get(Unidad.class, id));
	}

	@Override
	@Transactional
	public Unidad save(Unidad unidad) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(unidad);
	}

	@Override
	@Transactional
	public Unidad update(Unidad unidad) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(unidad);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Unidad unidadToDelete = currentSession.get(Unidad.class, id);
		
		if(unidadToDelete != null) {
			currentSession.remove(unidadToDelete);
		}

	}

}
