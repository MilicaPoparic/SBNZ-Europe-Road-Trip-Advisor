package com.sbnz.controller;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.dto.CategoryDTO;
import com.sbnz.model.Category;
import com.sbnz.model.RegisteredUser;
import com.sbnz.service.CategoryService;
import com.sbnz.service.RegisteredUserService;

@RestController
@RequestMapping(value = "/api/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	@Autowired
	private CategoryService CategoryService;

	@Autowired
	private RegisteredUserService registeredUserService;

	public CategoryController() {
		super();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		List<Category> Categories = CategoryService.findAll();

		return new ResponseEntity<>(toCategoryDTOList(Categories), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
		Category category = CategoryService.findOne(id);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(category.toDTO(),HttpStatus.OK);
	}

//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ResponseEntity<Page<CategoryDTO>> loadCategoryPage(Pageable pageable) {
//		Page<Category> Categorys = CategoryService.findAll(pageable);
//		if (Categorys == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		Page<CategoryDTO> CategoryDTO = toCategoryDTOPage(Categorys);
//		return new ResponseEntity<>(CategoryDTO, HttpStatus.OK);
//	}

	@RequestMapping(method = RequestMethod.POST)
	//@PreAuthorize("hasRole('REGISTERED_USER')")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
		Category category;
//		if (!this.validateCategoryDTO(categoryDTO)) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
		try {
			category = categoryDTO.toEntity();
			category = CategoryService.create(category);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(category.toDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('REGISTERED_USER')")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
		Category category;
//		if (!this.validateCategoryDTO(categoryDTO))
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			category = CategoryService.update(categoryDTO.toEntity(), id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(category.toDTO(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	//@PreAuthorize("hasRole('REGISTERED_USER')")
	@CrossOrigin(origins = "http://localhost:8080")
	public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
		try {
			CategoryService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>("OK", HttpStatus.OK);
	}


	private List<CategoryDTO> toCategoryDTOList(List<Category> Categories) {
		List<CategoryDTO> CategoryDTOS = new ArrayList<>();
		for (Category category : Categories) {
			CategoryDTOS.add(category.toDTO());
		}
		return CategoryDTOS;
	}

//	private Page<CategoryDTO> toCategoryDTOPage(Page<Category> rates) {
//		Page<CategoryDTO> dtoPage = rates.map(new Function<Category, CategoryDTO>() {
//			@Override
//			public CategoryDTO apply(Category entity) {
//				CategoryDTO dto = entity.toDTO();
//				return dto;
//			}
//		});
//		return dtoPage;
//	}

//	private boolean validateCategoryDTO(CategoryDTO CategoryDTO) {
//		if (CategoryDTO.getUserId() == null)
//			return false;
//		if (CategoryDTO.getCulturalOfferId() == null)
//			return false;
//		if (CategoryDTO.getDate() == null)
//			return false;
//		if (CategoryDTO.getText() == null)
//			return false;
//
//		return true;
//	}
}
