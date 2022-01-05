package com.example.demologin.repository;

import com.example.demologin.entity.User;
import com.example.demologin.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//    User findByEmailAndPassword(String email, String pass);
//
//    User findById(int id);

    // Khi được gắn @Query, thì tên của method không còn tác dụng nữa
    // Đây là JPQL
//    @Query("select u from User u where u.email = ?1")
//    User myCustomQuery(String email);
//
//    // Đây là Native SQL
//    @Query(value = "select * from user u where u.email = ?1", nativeQuery = true)
//    User myCustomQuery2(String email);
    User findByEmail(String email);
}
