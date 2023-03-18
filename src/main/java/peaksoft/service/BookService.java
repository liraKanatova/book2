package peaksoft.service;

import peaksoft.dto.BookRequest;
import peaksoft.dto.BookResponse;
import peaksoft.models.Book;

import java.util.List;

public interface BookService {
    BookResponse save(BookRequest bookRequest);
    List<BookResponse> getAllBooks();
    BookResponse getBookById(Long bookId);
    Book updateBook(Long bookId,BookRequest bookRequest);
    String deleteBook(Long bookId);

}
