package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController//rest api, response:JSON
//@Controller dynamic app, return:ModelAndView veya String(viewname) //dinemik uygulama ön yüzü olan uygulama var
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //CREATE
    //1-a : Save a Book & Response:Message
    // http://localhost:8080/books + POST + body(JOSN)
    /*
    {
        "id":1
        "title":"Martin Eden"
        "author":"Jack London"
        "publicationDate":"1875"
    }
    //DTO ile id yi almayız.
    */
    @PostMapping
    public ResponseEntity<String> createBook(@Valid @RequestBody BookDTO bookDTO) {//mesaj döndüriceğim icin Spring oluşturduk.
        bookService.saveBook(bookDTO);
        return  new ResponseEntity<>("Kitap başarıyla kayıt edildi", HttpStatus.CREATED);//201
    }

    //2-a : Get All Books
    // http://localhost:8080/books + GET
    //todo:ilerleyen derste return:List<BookDTO>
    @GetMapping
    private ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);//200

    }

    ///Ödev:5 tane kitap ekleyelim

    //3- Get a Book by its ID
    //http://localhost:8080/books/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") Long id){
        BookDTO bookDTO = bookService.getBookDTOById(id);
        return ResponseEntity.ok(bookDTO);//200
    }

    //4- Delete a Book by its ID
    //http://localhost:8080/books/2 + DELETE
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Kitap silme işlemi başarılı...");
    }

















}
