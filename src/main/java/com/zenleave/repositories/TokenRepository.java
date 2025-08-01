package com.zenleave.repositories;

import com.zenleave.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
        select t from Token t inner join User u on t.user.id = u.id
        where u.id = :userId and (t.expired = false and t.revoked = false )
    """)
    List<Token> findAllValidTokensByUser(Integer userId);
    Optional<Token> findByToken(String value);
}
