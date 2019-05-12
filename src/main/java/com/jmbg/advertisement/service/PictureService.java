package com.jmbg.advertisement.service;

import com.jmbg.advertisement.component.PictureConverter;
import com.jmbg.advertisement.entity.Picture;
import com.jmbg.advertisement.model.PictureModel;
import com.jmbg.advertisement.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PictureService {

    @Autowired
    private PictureRepository repository;
    @Autowired
    private PictureConverter converter;

    public PictureModel findById (Integer id) {
        return converter.entityToModel(findEntityById(id));
    }

    public Picture findEntityById (Integer id) {
        Optional<Picture> picture = repository.findById(id);
        if (!picture.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Picture with Id " + id + " not exist");
        }
        return picture.get();
    }

    public Picture create(PictureModel picture) {
        return repository.save(converter.modelToEntity(picture));
    }
}
