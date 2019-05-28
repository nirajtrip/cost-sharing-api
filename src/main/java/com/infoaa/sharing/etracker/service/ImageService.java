package com.infoaa.sharing.etracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoaa.sharing.etracker.model.UploadImage;
import com.infoaa.sharing.etracker.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public void saveImage(UploadImage image){
		imageRepository.save(image);
	}

	public Optional<UploadImage> getUploadImage(int expenseId){
		return imageRepository.findById(expenseId);
	}

	public void deleteUploadImage(int[] expenseID){
		for (int i : expenseID) {
			imageRepository.deleteById(i);
		}
	}
}
