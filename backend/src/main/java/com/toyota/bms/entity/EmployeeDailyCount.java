package com.toyota.bms.entity;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity
@Table(name="employee_daily_counts",
        uniqueConstraints=@UniqueConstraint(name="uk_employee_date",
                columnNames="record_date"))
public class EmployeeDailyCount { @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
    @Column(name="record_date",nullable=false)
    private LocalDate date;
    @Column(nullable=false)
    private Integer employeeCount;
    public Long getId(){return id;}
    public LocalDate getDate(){return date;}
    public void setDate(LocalDate v){date=v;}
    public Integer getEmployeeCount(){return employeeCount;}
    public void setEmployeeCount(Integer v){employeeCount=v;} }
