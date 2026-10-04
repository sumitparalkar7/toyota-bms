package com.toyota.bms.service; import com.toyota.bms.dto.Responses.DashboardSummary; import com.toyota.bms.entity.*; import com.toyota.bms.repository.*; import org.springframework.stereotype.Service; import java.math.*; import java.time.*; import java.util.*;
@Service public class DashboardService {
    private final EmployeeRepository er;
    private final FruitRepository fr;
    private final GarbageRepository gr;
    public DashboardService(EmployeeRepository e,
                            FruitRepository f,
                            GarbageRepository g){er=e;fr=f;gr=g;}
    public DashboardSummary summary(){
        LocalDate today=LocalDate.now(),start=today.withDayOfMonth(1);
        BigDecimal emp=er
                .findByDate(today)
                .map(x->BigDecimal
                        .valueOf(x.getEmployeeCount()))
                .orElse(BigDecimal.ZERO);BigDecimal fruit=fr.findByDate(today)
                .map(x->sum(x.getApple(),
                        x.getBanana(),
                        x.getPear(),
                        x.getOrange(),
                        x.getGuava())).orElse(BigDecimal.ZERO);
                BigDecimal garbage=gr
                        .findByDate(today)
                        .map(x->sum(x.getPaper(),
                                x.getPlastic(),
                                x.getGlass(),
                                x.getMetal(),
                                x.getWood(),
                                x.geteWaste(),
                                x.getOrganicFood()))
                        .orElse(BigDecimal.ZERO);
                List<EmployeeDailyCount> emps=er
                        .findAll()
                        .stream()
                        .filter(x->!x.getDate()
                                .isBefore(start)&&!x
                                .getDate()
                                .isAfter(today))
                        .toList();BigDecimal avg=emps
                .isEmpty()?BigDecimal.ZERO:BigDecimal
                .valueOf(emps.stream()
                        .mapToInt(EmployeeDailyCount::getEmployeeCount)
                        .average().orElse(0))
                .setScale(1,RoundingMode.HALF_UP);
                        BigDecimal mf=fr
                                .findAll()
                                .stream()
                                .filter(x->!x.getDate()
                                        .isBefore(start)&&!x
                                        .getDate()
                                        .isAfter(today))
                                .map(x->sum(x.getApple(),x
                                        .getBanana(),x
                                        .getPear(),
                                        x.getOrange(),
                                        x.getGuava()))
                                .reduce(BigDecimal.ZERO,BigDecimal::add);
                        BigDecimal mg=gr.findAll()
                                .stream()
                                .filter(x->!x.getDate()
                                        .isBefore(start)&&!x.getDate()
                                        .isAfter(today)).map(x->sum(x.getPaper(),
                                        x.getPlastic(),
                                        x.getGlass(),
                                        x.getMetal(),
                                        x.getWood(),
                                        x.geteWaste(),
                                        x.getOrganicFood()))
                                .reduce(BigDecimal.ZERO,BigDecimal::add);
                        return new DashboardSummary(emp,fruit,garbage,avg,mf,mg);}
    private BigDecimal sum(BigDecimal...v)
    {return Arrays
            .stream(v)
            .reduce(BigDecimal.ZERO,BigDecimal::add);}}
