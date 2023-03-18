package peaksoft.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
    ,generator = "book_id_gen")
    @SequenceGenerator(name = "book_id_gen",
    sequenceName = "book_id_seq",
    allocationSize = 1)
    private Long id;
    private String name;
    private String author;
    private int price;

    public Book(String name, String author, int price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }
}
