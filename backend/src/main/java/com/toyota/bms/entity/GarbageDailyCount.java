package com.toyota.bms.entity;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDate;
@Entity
@Table(name="garbage_daily_counts",
        uniqueConstraints=@UniqueConstraint(name="uk_garbage_date",
                columnNames="record_date"))
public class GarbageDailyCount {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; @Column(name="record_date",nullable=false)
    private LocalDate date; @Column(nullable=false,precision=10,scale=2)
    private BigDecimal paper,plastic,glass,metal,wood;
    @Column(name="e_waste",nullable=false,precision=10,scale=2)
    private BigDecimal eWaste;
    @Column(name="organic_food",nullable=false,precision=10,scale=2)
    private BigDecimal organicFood; public Long getId(){return id;}
    public LocalDate getDate(){return date;}
    public void setDate(LocalDate v){date=v;}
    public BigDecimal getPaper(){return paper;}
    public void setPaper(BigDecimal v){paper=v;}
    public BigDecimal getPlastic(){return plastic;}
    public void setPlastic(BigDecimal v){plastic=v;}
    public BigDecimal getGlass(){return glass;}
    public void setGlass(BigDecimal v){glass=v;}
    public BigDecimal getMetal(){return metal;}
    public void setMetal(BigDecimal v){metal=v;}
    public BigDecimal getWood(){return wood;}
    public void setWood(BigDecimal v){wood=v;}
    public BigDecimal geteWaste(){return eWaste;}
    public void seteWaste(BigDecimal v){eWaste=v;}
    public BigDecimal getOrganicFood(){return organicFood;}
    public void setOrganicFood(BigDecimal v){organicFood=v;} }
