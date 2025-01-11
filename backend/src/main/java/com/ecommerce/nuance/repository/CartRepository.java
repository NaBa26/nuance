package com.ecommerce.nuance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.nuance.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.book WHERE c.user.id = :userId")
    Optional<Cart> findByUserId(@Param("userId") long userId);

    Optional<Cart> findByBookIdAndUserId(long bookId, long userId);

    @Modifying
    @Query("UPDATE Cart c SET c.quantity = :quantity, c.subtotal = :subtotal, c.updatedAt = CURRENT_TIMESTAMP WHERE c.user.id = :userId AND c.book.id = :bookId")
    int updateQuantityAndSubtotal(@Param("quantity") int quantity, @Param("subtotal") double subtotal, @Param("bookId") long bookId, @Param("userId") long userId);

    @Modifying
    @Query("UPDATE Cart c SET c.quantity = 0, c.subtotal = 0, c.deletedAt = CURRENT_TIMESTAMP, c.updatedAt = CURRENT_TIMESTAMP WHERE c.user.id = :userId AND c.book.id = :bookId")
    int setQuantityToZero(@Param("bookId") long bookId, @Param("userId") long userId);

    Optional<Cart> findByBookIdAndUserIdAndDeletedAtIsNull(long bookId, long userId);
}
