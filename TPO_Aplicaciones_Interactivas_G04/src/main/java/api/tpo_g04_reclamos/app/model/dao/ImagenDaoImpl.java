package api.tpo_g04_reclamos.app.model.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.tpo_g04_reclamos.app.model.entity.Imagen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Repository
public class ImagenDaoImpl implements IImagenDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Imagen> findById(String id) {
		Session currentSession = entityManager.unwrap(Session.class);
		return Optional.ofNullable(currentSession.get(Imagen.class, id));
	}

	@Override
	@Transactional
	public Imagen save(Imagen imagen) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.merge(imagen);
	}

	@Override
	@Transactional()
	public void deleteById(String id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Imagen imagen = currentSession.get(Imagen.class, id);
		if(imagen == null)
			throw new IllegalArgumentException("Id imagen no válido");
		currentSession.remove(imagen);
	}
}
