package api.tpo_g04_reclamos.app.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import api.tpo_g04_reclamos.app.model.dao.IImagenDao;
import api.tpo_g04_reclamos.app.model.entity.Imagen;

@Service
public class ImagenServiceImpl implements IImagenService {

	@Autowired
	IImagenDao imagenDao;
	
	@Override
	public Imagen findById(String id) {
		return imagenDao.findById(id);
	}

	@Override
	public void deleteById(String id) {
		imagenDao.deleteById(id);
	}

	@Override
	public Imagen save(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            throw new IllegalArgumentException("invalid path" + fileName);
        }
		try {
			Imagen img = new Imagen(fileName, file.getContentType(), file.getBytes());
			imagenDao.save(img);
			return img;
		} catch (IOException ex) {
			throw new IllegalArgumentException("getbytes failed", ex);
		}

	}

}
