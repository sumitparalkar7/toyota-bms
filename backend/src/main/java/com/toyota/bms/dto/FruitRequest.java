package com.toyota.bms.dto; import jakarta.validation.constraints.*; import java.math.BigDecimal; import java.time.LocalDate;
public record FruitRequest
        (@NotNull @PastOrPresent LocalDate date,
         @NotNull @DecimalMin("0.0")
         @Digits(integer=8,fraction=2) BigDecimal apple,
         @NotNull @DecimalMin("0.0")
         @Digits(integer=8,fraction=2) BigDecimal banana,
         @NotNull @DecimalMin("0.0")
         @Digits(integer=8,fraction=2) BigDecimal pear,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal orange,
         @NotNull @DecimalMin("0.0") @Digits(integer=8,fraction=2) BigDecimal guava){}
