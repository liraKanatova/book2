package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.BookResponse;
import peaksoft.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select new peaksoft.dto.BookResponse(b.id,b.name,b.author,b.price) from Book b")
List<BookResponse>getAllByBooks();

    Optional<BookResponse> getBookById(Long bookId);
}