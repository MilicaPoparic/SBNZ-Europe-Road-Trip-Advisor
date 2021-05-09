package com.sbnz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.model.Category;
import com.sbnz.repository.CategoryRepository;

@Service
public class CategoryService implements ServiceInterface<Category> {

	@Autowired
	private CategoryRepository CategoryRepository;

	@Override
	public List<Category> findAll() {
		return CategoryRepository.findByActive(true);
	}

	@Override
	public Category findOne(Long id) {
		return CategoryRepository.findByIdAndActive(id, true).orElse(null);
	}

	@Override
	public Category create(Category entity) throws Exception {
		Category c = new Category();
		c.setName(entity.getName());
		c.setActive(true);
		c = CategoryRepository.save(c);
		return c;
	}

	@Override
	public Category update(Category entity, Long id) throws Exception {
		Optional<Category> optCategory = CategoryRepository.findById(id);
		if (!optCategory.isPresent()) {
			throw new Exception("Category with given id doesn't exist");
		}
		Category existingCategory = optCategory.orElse(null);

		existingCategory.setName(entity.getName());
		return CategoryRepository.save(existingCategory);
	}

	@Override
	public void delete(Long id) throws Exception {
		Optional<Category> optCategory = CategoryRepository.findById(id);
		if (!optCategory.isPresent()) {
			throw new Exception("Category with given id doesn't exist");
		}
		Category existingCategory = optCategory.orElse(null);
		existingCategory.setActive(false);
		CategoryRepository.save(existingCategory);
	}

	public List<Category> findAllActive() {
		return CategoryRepository.findByActive(true);
	}

	public Optional<Category> findCategoryById(Long id) {
		return CategoryRepository.findByIdAndActive(id, true);
	}

}
