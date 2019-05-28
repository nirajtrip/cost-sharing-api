package com.infoaa.sharing.etracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.infoaa.sharing.etracker.model.UploadImage;

public interface ImageRepository extends CrudRepository<UploadImage, Integer> {

}
