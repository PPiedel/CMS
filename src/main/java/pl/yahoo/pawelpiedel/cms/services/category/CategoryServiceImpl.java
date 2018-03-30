package pl.yahoo.pawelpiedel.cms.services.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.yahoo.pawelpiedel.cms.model.Category;
import pl.yahoo.pawelpiedel.cms.repositories.CategoryRepository;

@Component
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category findCategoryByName(String name) {
        Category category = null;
        if (!name.isEmpty()) {
            category = categoryRepository.findByName(name);
        }
        return category;
    }
}
