package com.toyota.bms.dto; import jakarta.validation.constraints.*; import java.time.LocalDate;
public record EmployeeRequest
        (@NotNull @PastOrPresent LocalDate date,
         @NotNull @Min(0) @Max(1000000) Integer employeeCount){}
