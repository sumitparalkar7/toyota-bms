package com.toyota.bms.repository;
import com.toyota.bms.entity.FruitDailyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface FruitRepository extends JpaRepository<FruitDailyCount,Long>
{Optional<FruitDailyCount> findByDate(LocalDate date);
    boolean existsByDate(LocalDate date);}
