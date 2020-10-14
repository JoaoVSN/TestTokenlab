package com.example.backtoken.repository;

import com.example.backtoken.model.Invitetk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvitetkRepository extends PagingAndSortingRepository<Invitetk, Long> {
    @Query("SELECT inv FROM Invitetk inv WHERE inv.id_event = :event AND inv.id_user = :user")
    Invitetk findById_eventAndId_user(@Param("event") long id_event, @Param("user") long id_user);
}
