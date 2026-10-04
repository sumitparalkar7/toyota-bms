package com.toyota.bms.dto; import java.math.BigDecimal; import java.time.LocalDate;
public final class Responses
{private Responses(){} public record
EmployeeResponse(Long id,
                 LocalDate date,
                 String day,
                 Integer employeeCount){}
    public record FruitResponse
            (Long id,
             LocalDate date,
             String day,
             BigDecimal apple,
             BigDecimal banana,
             BigDecimal pear,
             BigDecimal orange,
             BigDecimal guava){}
    public record GarbageResponse
            (Long id,LocalDate date,
             String day,
             BigDecimal paper,
             BigDecimal plastic,
             BigDecimal glass,
             BigDecimal metal,
             BigDecimal wood,
             BigDecimal eWaste,
             BigDecimal organicFood){}
    public record DashboardSummary
            (BigDecimal todayEmployees,
             BigDecimal todayFruitKg,

             BigDecimal todayGarbageKg,
             BigDecimal monthlyAverageEmployees,
             BigDecimal monthlyFruitKg,
             BigDecimal monthlyGarbageKg){} }
