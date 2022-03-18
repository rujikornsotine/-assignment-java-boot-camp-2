package com.example.cashcenter.cashbox;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CashBoxRepository extends JpaRepository<CashBox,Integer> {

     Optional<CashBox> findById(int id);
     Optional<List<CashBox>> findByDeliveryID(String deliveryID);
     Optional<List<CashBox>> findByEmpID(String empID);
     Optional<List<CashBox>> findByCreateDateBetween(LocalDateTime startDate,LocalDateTime endDate);
}
