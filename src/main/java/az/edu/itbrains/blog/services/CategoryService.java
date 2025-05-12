package az.edu.itbrains.blog.services;

import az.edu.itbrains.blog.dtos.category.CategoryCreateDto;
import az.edu.itbrains.blog.dtos.category.CategoryDto;
import az.edu.itbrains.blog.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.blog.payloads.ApiResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    ApiResponse createCategory(CategoryCreateDto categoryCreateDto);
    CategoryUpdateDto getUpdatedCategory(Long id);
    ApiResponse updateCategory(Long id, CategoryUpdateDto categoryUpdateDto);
}
