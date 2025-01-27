package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.domain.Owner;
import com.tpe.dto.BookDTO;
import com.tpe.dto.OwnerDTO;
import com.tpe.exception.BookNotFoundException;
import com.tpe.exception.ConflictException;
import com.tpe.exception.OwnerNotFoundException;
import com.tpe.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final OwnerService ownerService;

    //1-b
    public void saveBook(BookDTO bookDTO) {
        //bookDTO-->book
        Book book=new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationDate(bookDTO.getPublicationDate());
        bookRepository.save(book);
    }

    //2-b
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    //3-b
    public Book getBookById(Long id){
        Book book=bookRepository.findById(id).
                orElseThrow(()->new BookNotFoundException("Book not found by id:"+id));
        return book;
    }
    //3-c
    public BookDTO getBookDTOById(Long id) {
        Book book=getBookById(id);
        return new BookDTO(book);
        //alternatif:repositoryde JPQL ile doğrudan DTO objesi de döndürebiliriz.
    }

    //4-b
    public void deleteBook(Long id) {
        getBookById(id);//bu id ile book yoksa custom exception fırlatmak için
        bookRepository.deleteById(id);
    }

    //6-b
    public List<Book> filterBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    //7-b
    public Page<Book> getBooksByPage(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }

    //8-b
    public void updateBook(Long id, BookDTO bookDTO) {
        Book foundBook=getBookById(id);
        foundBook.setTitle(bookDTO.getTitle());
        foundBook.setAuthor(bookDTO.getAuthor());
        foundBook.setPublicationDate(bookDTO.getPublicationDate());
        bookRepository.save(foundBook);//merge: update .. set ..
    }

    //9-b
    public List<Book> filterBooksByAuthor(String author) {
        List<Book> bookList=bookRepository.findByAuthorWithJPQL(author);
        if (bookList.isEmpty()){
            throw new BookNotFoundException("Yazara ait kitap bulunamadı!");
        }
        return bookList;
    }

    //10-b
    public void addBookToOwner(Long bookId, Long ownerId) {
        Book foundBook=getBookById(bookId);//owner:başka bir üye,aynı üye
        Owner foundOwner= ownerService.getOwnerById(ownerId);

        //belirtilen id ye sahip olan kitap daha önce ownera verilmiş mi
        if (foundOwner.getBookList().contains(foundBook)){//kendisinde
            throw new ConflictException("Bu kitap üyenin listesinde zaten var!");
        } else if(foundBook.getOwner()!=null){//başka üyede
            throw new ConflictException("Bu kitap başka bir üyededir!");
        }else{
            //aktif olan kitabı belirtilen ownera ekleyebiliriz.
            foundBook.setOwner(foundOwner);
            bookRepository.save(foundBook);
        }

    }

    //2-b-DTO
    public List<BookDTO> getAllAsDto() {
        List<Book> bookList=bookRepository.findAll();
        return bookList.stream().map(book->new BookDTO(book)).collect(Collectors.toList());
    }

    public OwnerDTO showOwner(Long bookId) {
        Book book=getBookById(bookId);
        Owner owner=book.getOwner();
        if (owner!=null){
            return new OwnerDTO(owner);
        }else {
            throw new OwnerNotFoundException("Kitap bir üyeye ait değil!");
        }
    }
}