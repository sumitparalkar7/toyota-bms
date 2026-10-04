package com.toyota.bms.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
@Entity
@Table(name="fruit_daily_counts",
        uniqueConstraints=@UniqueConstraint(name="uk_fruit_date",
                columnNames="record_date"))
public class FruitDailyCount {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="record_date",nullable=false)
    private LocalDate date; @Column(nullable=false,precision=10,scale=2)
    private BigDecimal apple,banana,pear,orange,guava;
    public Long getId()
    {return id;}
    public LocalDate getDate(){return date;}
    public void setDate(LocalDate v){date=v;}
    public BigDecimal getApple(){return apple;}
    public void setApple(BigDecimal v){apple=v;}
    public BigDecimal getBanana(){return banana;}
    public void setBanana(BigDecimal v){banana=v;}
    public BigDecimal getPear(){return pear;}
    public void setPear(BigDecimal v){pear=v;}
    public BigDecimal getOrange(){return orange;}
    public void setOrange(BigDecimal v){orange=v;}
    public BigDecimal getGuava(){return guava;}
    public void setGuava(BigDecimal v){guava=v;} }
