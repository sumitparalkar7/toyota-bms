package com.toyota.bms.repository;
import com.toyota.bms.entity.EmployeeDailyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface EmployeeRepository extends JpaRepository<EmployeeDailyCount,Long>
{Optional<EmployeeDailyCount>
findByDate(LocalDate date);
    boolean existsByDate(LocalDate date);}
