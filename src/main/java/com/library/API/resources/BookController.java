package com.library.API.resources;

import com.library.API.dto.BookDTO;
import com.library.API.entities.Book;
import com.library.API.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    BookService bookService;

    ModelMapper modelMapper;

    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookDTO dto){
        Book entity = modelMapper.map( dto , Book.class);
        entity = bookService.save(entity);
        return modelMapper.map(entity , BookDTO.class);
    }

}
