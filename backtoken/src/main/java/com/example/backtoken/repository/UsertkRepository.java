package com.example.backtoken.repository;

import com.example.backtoken.model.Usertk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UsertkRepository extends CrudRepository<Usertk, Long> {
    @Query("SELECT us FROM Usertk us WHERE UPPER(us.us_nome) LIKE UPPER (concat('%', :name, '%'))")
    List<Usertk> findByUs_nomeIgnoreCaseContaining(@Param("name") String name);

    @Query("SELECT us FROM Usertk us WHERE us.us_login = :login")
    Usertk findByUs_login(@Param("login") String login);
}
