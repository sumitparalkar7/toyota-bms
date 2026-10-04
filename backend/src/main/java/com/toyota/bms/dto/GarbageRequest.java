package com.toyota.bms.dto; import jakarta.validation.constraints.*; import java.math.BigDecimal; import java.time.LocalDate;
public record GarbageRequest
        (@NotNull @PastOrPresent LocalDate date,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal paper,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal plastic,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal glass,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal metal,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal wood,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal eWaste,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal organicFood){}
