package com.ecommerce.nuance.service;

import java.util.List;




import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.nuance.exception.BookNotFoundException;
import com.ecommerce.nuance.model.Book;
import com.ecommerce.nuance.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService { 

	private final BookRepository bookRepository;

	// This constructor is created to automatically update the value in the repository class
	public BookServiceImpl(BookRepository bookRepository) { 
	    this.bookRepository = bookRepository;
	}
	
	
	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}
	
	public Optional<Book> getBookById(Integer bookId)
	{
			return bookRepository.findById(bookId);
	}
	
	
	public Book createBook(Book bookModel)
	{
		return bookRepository.save(bookModel);
	}
	

    public Book updateBook(Integer bookId,Book updatedBook) throws BookNotFoundException 
    {
        
        return bookRepository.findById(bookId).map(book->{ 
        	book.setName(updatedBook.getName());
            book.setGenre(updatedBook.getGenre());
            book.setIsbn(updatedBook.getIsbn());
            book.setPrice(updatedBook.getPrice());
            
            return bookRepository.save(book);
        }).orElse(null);
    }

    @Transactional
    public void deleteBook(Integer bookId)
    {
    	bookRepository.deleteById(bookId);
    }
    
    public List<Book> findByAuthorAndGenre(String author, String genre) {
        return bookRepository.findByAuthorAndGenreIgnoreCase(author,genre);
    }
    
    List<Book> findByPriceLessThanEqual(float price)
    {
    	return bookRepository.findByPriceLessThanEqual(price);
    }


	@Override
	public List<Book> getBookByGenre(String genre) {
		return bookRepository.findByGenre(genre);
	}


	@Override
	public Optional<Book> getBookByNameAndId(String bookName, Long id) {
	    return bookRepository.findByIdAndName(id, bookName);
	}
}
