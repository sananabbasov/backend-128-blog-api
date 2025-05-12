package az.edu.itbrains.blog.services.impls;

import az.edu.itbrains.blog.dtos.category.CategoryCreateDto;
import az.edu.itbrains.blog.dtos.category.CategoryDto;
import az.edu.itbrains.blog.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.blog.exceptions.ResourceNotFoundException;
import az.edu.itbrains.blog.models.Category;
import az.edu.itbrains.blog.payloads.ApiResponse;
import az.edu.itbrains.blog.repositories.CategoryRepository;
import az.edu.itbrains.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(c-> new CategoryDto(c.getId(), c.getName())).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public ApiResponse createCategory(CategoryCreateDto categoryCreateDto) {
        try {
            Category findCategory = categoryRepository.findByNameIgnoreCase(categoryCreateDto.getName());
            if (findCategory != null){
                return new ApiResponse("Category already exist.", false, HttpStatus.OK);
            }

            Category category = new Category();
            category.setName(categoryCreateDto.getName());
            categoryRepository.save(category);
            return new ApiResponse("Category created successfully.",true, HttpStatus.CREATED);
        }catch (Exception e){
            return new ApiResponse(e.getMessage(), false, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public CategoryUpdateDto getUpdatedCategory(Long id) {
        Category findCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","id", id));
        CategoryUpdateDto categoryUpdateDto = new CategoryUpdateDto();
        categoryUpdateDto.setName(findCategory.getName());
        return categoryUpdateDto;
    }

    @Override
    public ApiResponse updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category findCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","id", id));
        findCategory.setName(categoryUpdateDto.getName());
        categoryRepository.save(findCategory);
        return new ApiResponse("Category updated successfully.", true, HttpStatus.OK);
    }
}
