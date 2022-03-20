package com.example.cashcenter.cashmanagement;

import com.example.cashcenter.cashmanagement.enums.CashManagementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CashManagementRepository extends JpaRepository<CashManagement,Integer> {

    Optional<CashManagement> findById(int id);
    Optional<List<CashManagement>> findByEmpID(String empID);
    Optional<List<CashManagement>> findByStatus(CashManagementStatus status);

}
