package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.tpo_g04_reclamos.app.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Usuario> getQuery = currentSession.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios = getQuery.getResultList();
		return usuarios;
	}

	@Transactional(readOnly = true)
	public Usuario findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Usuario usuario = currentSession.get(Usuario.class, id);
		return usuario;
	}

	@Transactional
	public void save(Usuario usuario) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(usuario);
	}
	
	@Transactional
	public void update(Usuario usuario) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.merge(usuario);
	}

	@Transactional
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Usuario usuario = currentSession.get(Usuario.class, id);
		if(usuario != null)
			currentSession.remove(usuario);
		else
			throw new IllegalArgumentException("Id usuario no válido");
	}
}
