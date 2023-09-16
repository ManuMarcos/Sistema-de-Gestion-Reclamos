package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Usuario> getQuery = currentSession.createQuery("from Usuario", Usuario.class);
		return getQuery.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Usuario usuario = currentSession.get(Usuario.class, id);
		return Optional.ofNullable(usuario);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(usuario);
	}

	@Override
	@Transactional
	public Usuario update(Usuario usuario) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(usuario);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Usuario usuario = currentSession.get(Usuario.class, id);
		if(usuario != null)
			currentSession.remove(usuario);
	}
}
