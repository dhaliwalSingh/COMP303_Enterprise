package com.Harshbir.FinalTest_COMP303.repository;

import com.Harshbir.FinalTest_COMP303.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Optional<Bank> findByBankName(String bankName);
    void deleteByBankName(String bankName);
}
