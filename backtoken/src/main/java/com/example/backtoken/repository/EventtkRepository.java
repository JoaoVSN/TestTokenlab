package com.example.backtoken.repository;

import com.example.backtoken.model.Eventtk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventtkRepository extends PagingAndSortingRepository<Eventtk, Long> {
    @Query("SELECT ev FROM Eventtk ev WHERE UPPER(ev.ev_desc) LIKE UPPER (concat('%', :desc, '%'))")
    List<Eventtk> findByEv_descIgnoreCaseContaining(@Param("desc") String ev_desc);
    @Query("SELECT ev FROM Eventtk ev WHERE ev.ev_us_id.us_login LIKE :user ")
    List<Eventtk> findAllByEv_us_login(@Param("user") String ev_us_id);
}
