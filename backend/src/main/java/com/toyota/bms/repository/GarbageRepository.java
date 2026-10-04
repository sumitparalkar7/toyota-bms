package com.toyota.bms.repository;
import com.toyota.bms.entity.GarbageDailyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.*;
public interface GarbageRepository extends JpaRepository<GarbageDailyCount,Long>
{Optional<GarbageDailyCount>
findByDate(LocalDate date);
    boolean existsByDate(LocalDate date);}
