package com.aazizova.springboottesttask.model.dao;

import com.aazizova.springboottesttask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User repository.
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * User with username.
     *
     * @param username String
     *
     * @return User
     */
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User userWithUsername(@Param("username") String username);
}
