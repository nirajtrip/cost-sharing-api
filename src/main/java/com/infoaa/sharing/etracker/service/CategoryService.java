package com.infoaa.sharing.etracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infoaa.sharing.etracker.model.Category;
import com.infoaa.sharing.etracker.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired

	public List<String> getOnlyCategories(){

		return categoryRepository.getOnlyCategories();
	}

	public List<Category> getCategory(){
		List<Category> categories = new ArrayList<Category>();
		categoryRepository.findAll()
		.forEach(categories::add);
		return categories;
	}

	public void addCategory(Category category){
		categoryRepository.save(category);
	}

	public int getCategoryId(String category){
		return categoryRepository.getIdForCategory(category);
	}

	public void deleteCategory(String categoryId){

		Category category = categoryRepository.findByCategory(categoryId);
		if(category!=null){
			categoryRepository.delete(category);
		}
	}
	
	public int saveOrgetCategory(String category){
		int id=0;
		category.trim();

		List<String> categories = getOnlyCategories();
		if(categories.contains(category)){
			id = categoryRepository.getIdForCategory(category);
			return id;
		}
		else{
			Category obj = new Category();
			obj.setCategory(category);
			addCategory(obj);
			return categoryRepository.getIdForCategory(category);
		}
	}

	public Optional<Category> getCategoryById(int id){
		return categoryRepository.findById(id);
	}


	public Map<String, Double> getPieData(){

		List<Object[]> object=categoryRepository.getPieData();
		Map<String, Double> pie = new HashMap<String, Double>();
		for (int i=0; i<object.size(); i++){
			Object[] pieRow = object.get(i);
			String category= (String)pieRow[0];
			Double amount = (Double)pieRow[1];
			pie.put(category,amount);
		}
		return pie;
	}

	public Map<String, Double> getPieData(Date fDate, Date tDate){

		List<Object[]> object=categoryRepository.getPieDataByDate(fDate, tDate);
		Map<String, Double> pie = new HashMap<String, Double>();
		for (int i=0; i<object.size(); i++){
			Object[] pieRow = object.get(i);
			String category= (String)pieRow[0];
			Double amount = (Double)pieRow[1];
			pie.put(category,amount);
		}
		return pie;
	}


}
