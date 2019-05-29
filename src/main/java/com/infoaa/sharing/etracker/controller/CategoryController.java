package com.infoaa.sharing.etracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import com.infoaa.sharing.etracker.model.Category;
import com.infoaa.sharing.etracker.service.CategoryService;

@RestController
@RequestMapping("/rest")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(method = RequestMethod.POST, value = "/category")
    @ApiOperation(value = "Save Category",
            notes = "To save a Category")
	public void addCategory(@RequestBody Category category){
		categoryService.addCategory(category);
	}

	@RequestMapping(method = RequestMethod.GET, value ="/category")
    @ApiOperation(value = "Find Category by id",
            notes = "Also returns a link to retrieve all Category with rel - all-Category")
    public List<Category> getCategory(){
		return categoryService.getCategory();
	}

	@RequestMapping(method = RequestMethod.GET, value ="/CategoryList")
    @ApiOperation(value = "Find all Category",
            notes = "Returns all Category")
    public List<String> getOneCategory(){
		return categoryService.getOnlyCategories();
	}
}
