package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.dto.BookRequest;
import peaksoft.dto.BookResponse;
import peaksoft.models.Book;
import peaksoft.repository.BookRepository;
import peaksoft.service.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    @Override
    public BookResponse save(BookRequest bookRequest) {
        Book book = new Book();
        book.setName(bookRequest.getName());
        book.setAuthor(bookRequest.getAuthor());
        book.setPrice(bookRequest.getPrice());
         bookRepository.save(book);
         return new BookResponse(book.getId(),
                 book.getName(),
                 book.getAuthor(),
                 book.getPrice());
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.getAllByBooks();
    }

    @Override
    public BookResponse getBookById(Long bookId) {
        return bookRepository.getBookById(bookId)
                .orElseThrow(()-> new NoSuchElementException("Book with id: "+bookId+" doesn't exist"));
    }

    @Override
    public Book updateBook(Long bookId, BookRequest bookRequest) {
        Book book1 = bookRepository.findById(bookId)
                .orElseThrow(()->new NoSuchElementException("Book with id: "+bookId+" doesn't exist"));
        book1.setName(bookRequest.getName());
        book1.setAuthor(bookRequest.getAuthor());
        book1.setPrice(bookRequest.getPrice());
        bookRepository.save(book1);
        return book1;
    }

    @Override
    public String deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if(!exists){
            throw new NoSuchElementException("Book with id: "+bookId+" doesn't exist");
        }
        bookRepository.deleteById(bookId);
        return "Book with id: "+bookId+" is deleted";
    }
}
