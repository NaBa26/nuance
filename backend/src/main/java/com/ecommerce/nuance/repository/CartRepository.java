package com.ecommerce.nuance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.nuance.model.Cart;
import com.ecommerce.nuance.model.User;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	@Query("SELECT c FROM Cart c LEFT JOIN FETCH c.book WHERE c.user.id = :userId AND c.deletedAt IS NULL")
    Optional<List<Cart>> findByUser(@Param("userId") Long userId);

    Optional<Cart> findByBookIdAndUserId(long bookId, long userId);

    @Modifying
    @Query("UPDATE Cart c SET c.quantity = :quantity, c.subtotal = :subtotal, c.updatedAt = CURRENT_TIMESTAMP WHERE c.user.id = :userId AND c.book.id = :bookId")
    int updateQuantityAndSubtotal(@Param("quantity") int quantity, @Param("subtotal") double subtotal, @Param("bookId") long bookId, @Param("userId") long userId);

    @Modifying
    @Query("UPDATE Cart c SET c.quantity = 0 WHERE c.book.id = :bookId AND c.user.id = :userId")
    int setQuantityToZero(@Param("bookId") long bookId, @Param("userId") long userId);

    Optional<Cart> findByBookIdAndUserIdAndDeletedAtIsNull(long bookId, long userId);
    
    @Modifying
    @Query("UPDATE Cart c SET c.deletedAt = CURRENT_TIMESTAMP WHERE c.book.id = :bookId AND c.user.id = :userId")
    int softDeleteBook(@Param("bookId") long bookId, @Param("userId") long userId);
    
    @Modifying
    @Query("UPDATE Cart c SET c.deletedAt = CURRENT_TIMESTAMP WHERE c.user.id = :userId AND c.deletedAt IS NULL")
    void softDeleteCartItemsByUserId(@Param("userId") Long userId);

    
    
}
