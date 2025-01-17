package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.exception.BookNotFoundException;
import com.tpe.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor//sadece zorunlu olan argumanlar yani final olanlar icin oluşturuyor.
public class BookService {

    private final BookRepository bookRepository;


    //1-b
    public void saveBook(@Valid BookDTO bookDTO) {
        //bookDTO --> book dönüştürme
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationDate(bookDTO.getPublicationDate());
        bookRepository.save(book);
    }

    //2-b
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    //3-b (id verilen kitabı bulma methodu)
    public Book getBookById(Long id){
        Book book = bookRepository.findById(id).
                orElseThrow(()->new BookNotFoundException("Book not found by id:"+id));
        return book;
    }

    //3-c
    public BookDTO getBookDTOById(Long id) {
        Book book = getBookById(id);
        return new BookDTO(book);
        //alternatif : repository de JPQL ile doğrudan DTO objeside döndürebilirdik.
    }

    //4-b
    public void deleteBook(Long id) {
        getBookById(id);//bu id ile book yoksa custom exception fırlatmak icin
        bookRepository.deleteById(id);
    }
}
