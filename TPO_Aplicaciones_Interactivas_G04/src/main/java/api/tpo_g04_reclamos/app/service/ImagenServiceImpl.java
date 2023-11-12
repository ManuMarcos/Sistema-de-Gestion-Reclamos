package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IImagenDao;
import api.tpo_g04_reclamos.app.model.entity.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements IImagenService {

	@Autowired
	private IImagenDao imagenDao;
	
	@Override
	public Optional<Imagen> findById(Long id) {
		return imagenDao.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.imagenExiste(id);

		imagenDao.deleteById(id);
	}

	@Override
	public Imagen save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            throw new IllegalArgumentException(String.format("invalid path: %s", fileName));
        }
		try {
			Imagen img = new Imagen(fileName, file.getContentType(), file.getBytes());
			return imagenDao.save(img);
		} catch (IOException ex) {
			throw new IllegalArgumentException("getbytes failed", ex);
		}

	}

	@Override
	public List<Imagen> findAllByIds(List<Long> ids) {
		return imagenDao.findAllByIds(ids);
	}

	private boolean imagenExiste(Long id) {
		Optional<Imagen> imagen = this.findById(id);

		if(imagen.isEmpty()) {
			throw new ItemNotFoundException("La imagen no existe");
		}

		return true;
	}

}
