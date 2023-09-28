package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		Query<Usuario> getQuery = currentSession.createQuery("from usuarios", Usuario.class);
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
	public Optional<Usuario> findUser(String username, String password) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Usuario> query = currentSession.createQuery("FROM usuarios WHERE nombre = :username", Usuario.class);
		query.setParameter("username", username);
		Usuario usuario = query.uniqueResult();
		
		if(usuario != null && checkPassword(password, usuario.getPassword())) {
			return Optional.of(usuario);
		}
		return Optional.empty();
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
	
	
	/**
	 * Compara la password con la password guardada en la base de datos
	 * @param password password sin codificar
	 * @param passwordDB password codificado que esta guardado en la base de datos
	 * @return
	 */
	private boolean checkPassword(String password, String passwordDB) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode(password);
		return passwordEncoder.matches(password, passwordDB);
	}

	
}
