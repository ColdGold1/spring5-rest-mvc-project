package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperImplTest {

    public static String NAME = "name";
    public static Long ID = 1L;
    CategoryMapper mapper = CategoryMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void categoryToCategoryDto() {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = mapper.categoryToCategoryDTO(category);

        assertNotNull(categoryDTO);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}