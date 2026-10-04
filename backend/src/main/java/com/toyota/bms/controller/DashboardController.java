package com.toyota.bms.controller; import com.toyota.bms.dto.Responses.DashboardSummary; import com.toyota.bms.service.DashboardService; import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController
{private final DashboardService s;public DashboardController
        (DashboardService s){this.s=s;}
    @GetMapping("/summary")
    public DashboardSummary summary(){
    return s.summary();}}
