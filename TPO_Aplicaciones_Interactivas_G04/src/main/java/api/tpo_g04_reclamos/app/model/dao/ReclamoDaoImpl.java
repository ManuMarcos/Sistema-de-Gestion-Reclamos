package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Reclamo;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ReclamoDaoImpl implements IReclamoDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Reclamo> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Reclamo> getQuery = currentSession.createQuery("from reclamos", Reclamo.class);
		
		return getQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reclamo> findByEstado(EstadoReclamo estado) {
		Session currentSession = entityManager.unwrap(Session.class);

		TypedQuery<Reclamo> typedQuery = currentSession.createQuery("SELECT r FROM reclamos r WHERE r.estado = :estado", Reclamo.class);

		typedQuery.setParameter("estado", estado);

		return typedQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Reclamo> findById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		return Optional.ofNullable(currentSession.get(Reclamo.class, id));
	}

	@Override
	@Transactional
	public Reclamo save(Reclamo unidad) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(unidad);
	}

	@Override
	@Transactional
	public Reclamo update(Reclamo unidad) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(unidad);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);

		Reclamo reclamoToDelete = currentSession.get(Reclamo.class, id);
		
		if(reclamoToDelete != null) {
			currentSession.remove(reclamoToDelete);
		}

	}

}
