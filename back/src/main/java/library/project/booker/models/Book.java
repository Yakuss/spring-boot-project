package library.project.booker.models;

import library.project.booker.models.User;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(nullable = false)
    private boolean favorite;
    
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-books")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_type_id", nullable = false)
    @JsonBackReference("booktype-books")
    private BookType bookType;

 // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public boolean isFavorite() {
        return favorite;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }

    public User getUser() {
        return user;
    }

    public BookType getBookType() {
        return bookType;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}

