package com.library.API.services;

import com.library.API.entities.Book;
import com.library.API.repositories.BookRepository;
import com.library.API.services.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp(){
        this.service = new BookServiceImpl(repository);

    }

    @Test
    @DisplayName("Must save a book")
    public void savedBookTest(){
        //cenário
        Book book = Book.builder().author("Fulano").title("As aventuras").isbn("123").build();

        //simulando o comportamento do repository.
        Mockito.when(repository.save(book)).thenReturn(Book.builder()
                .id(1l).author("Fulano").title("As aventuras").isbn("123").build());

        //execução
        Book savedBook = service.save(book);

        //verificações
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("As aventuras");
        assertThat(savedBook.getAuthor()).isEqualTo("Fulano");
        assertThat(savedBook.getIsbn()).isEqualTo("123");
    }
}
