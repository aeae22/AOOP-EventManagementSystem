package com.eventmanagement.repository;

import com.eventmanagement.model.EventOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventOrderRepository extends JpaRepository<EventOrder, Long> {
    List<EventOrder> findByAttendeeId(Long attendeeId);
}