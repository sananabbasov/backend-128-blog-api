package az.edu.itbrains.blog.controllers;


import az.edu.itbrains.blog.dtos.category.CategoryCreateDto;
import az.edu.itbrains.blog.dtos.category.CategoryDto;
import az.edu.itbrains.blog.dtos.category.CategoryUpdateDto;
import az.edu.itbrains.blog.payloads.ApiResponse;
import az.edu.itbrains.blog.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDto>> getAll(){
        List<CategoryDto> categoryDtoList = categoryService.getAll();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse> create(@RequestBody CategoryCreateDto categoryCreateDto){
        ApiResponse apiResponse = categoryService.createCategory(categoryCreateDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }


    @GetMapping("/update/{id}")
    public ResponseEntity<CategoryUpdateDto> update(@PathVariable Long  id){
        CategoryUpdateDto categoryUpdateDto = categoryService.getUpdatedCategory(id);
        return new ResponseEntity<>(categoryUpdateDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody CategoryUpdateDto categoryUpdateDto){
        ApiResponse apiResponse = categoryService.updateCategory(id, categoryUpdateDto);
        return new ResponseEntity<>(apiResponse, apiResponse.getHttpStatus());
    }




}
