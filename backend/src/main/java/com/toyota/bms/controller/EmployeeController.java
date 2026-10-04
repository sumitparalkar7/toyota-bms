package com.toyota.bms.controller; import com.toyota.bms.dto.*; import com.toyota.bms.dto.Responses.EmployeeResponse; import com.toyota.bms.service.EmployeeService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController
{private final EmployeeService s;
    public EmployeeController(EmployeeService s){this.s=s;}
    @GetMapping public List<EmployeeResponse> all(){return s.all();}
    @GetMapping("/{id}")
    public EmployeeResponse one(@PathVariable Long id){return s.one(id);}
    @PostMapping public ResponseEntity<EmployeeResponse> create(

            @Valid @RequestBody EmployeeRequest r){
        return ResponseEntity.status(HttpStatus.CREATED).body(s.create(r));}
    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id,
                                   @Valid @RequestBody EmployeeRequest r)
    {return s.update(id,r);}@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT) public void delete
        (@PathVariable Long id){s.delete(id);}}
