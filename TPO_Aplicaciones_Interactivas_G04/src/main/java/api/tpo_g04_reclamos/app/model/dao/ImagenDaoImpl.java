package api.tpo_g04_reclamos.app.model.dao;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import api.tpo_g04_reclamos.app.model.entity.Imagen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ImagenDaoImpl implements IImagenDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public Imagen findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Imagen imagen = currentSession.get(Imagen.class, id);
		if(imagen == null)
			throw new IllegalArgumentException("Id imagen no válido");
		return imagen;
	}

	@Override
	@Transactional
	public void save(Imagen imagen) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(imagen);
	}

	@Override
	@Transactional()
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Imagen imagen = currentSession.get(Imagen.class, id);
		if(imagen == null)
			throw new IllegalArgumentException("Id imagen no válido");
		currentSession.remove(imagen);
	}
}
