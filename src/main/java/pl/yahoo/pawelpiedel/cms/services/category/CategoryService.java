package pl.yahoo.pawelpiedel.cms.services.category;

import org.springframework.stereotype.Service;
import pl.yahoo.pawelpiedel.cms.model.Category;

@Service
public interface CategoryService {
    Category findCategoryByName(String name);
}
