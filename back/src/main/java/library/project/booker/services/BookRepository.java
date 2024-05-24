package library.project.booker.services;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import library.project.booker.models.Book;
import library.project.booker.models.BookType;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String keyword);
    List<Book> findByBookType(BookType bookType);
    List<Book> findByBookTypeId(Long id);
    List<Book> findByUserId(Long userId);
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title% AND b.user.id = :userId")
    List<Book> findByTitleContainingAndUserId(@Param("title") String title, @Param("userId") Long userId);
    @Query("SELECT b FROM Book b WHERE b.bookType.id = :bookTypeId AND b.user.id = :userId")
    List<Book> findByBookTypeIdAndUserId(@Param("bookTypeId") Long bookTypeId, @Param("userId") Long userId);
    
}


