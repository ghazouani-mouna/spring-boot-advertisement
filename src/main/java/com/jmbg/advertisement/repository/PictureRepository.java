package com.jmbg.advertisement.repository;

import com.jmbg.advertisement.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
}