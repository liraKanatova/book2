package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.BookRequest;
import peaksoft.dto.BookResponse;
import peaksoft.models.Book;
import peaksoft.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookApi {
    private final BookService bookService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public BookResponse saveBook(@RequestBody BookRequest bookRequest){
       return bookService.save(bookRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDOR','CUSTOMER')")
    @GetMapping
    public List<BookResponse>getAllBooks(){
       return bookService.getAllBooks();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDOR','CUSTOMER')")
    @GetMapping("/{bookId}")
    public BookResponse getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','VENDOR')")
    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId,@RequestBody BookRequest bookRequest){
        return bookService.updateBook(bookId,bookRequest);
    }
   @PreAuthorize("hasAnyAuthority('ADMIN','VENDOR')")
    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable Long bookId){
        return bookService.deleteBook(bookId);
    }
}
