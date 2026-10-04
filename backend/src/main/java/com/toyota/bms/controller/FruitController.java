package com.toyota.bms.controller; import com.toyota.bms.dto.*; import com.toyota.bms.dto.Responses.FruitResponse; import com.toyota.bms.service.FruitService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController
@RequestMapping("/api/fruits")
public class FruitController
{private final FruitService s;public FruitController(FruitService s){this.s=s;}
    @GetMapping public List<FruitResponse> all(){return s.all();}
    @GetMapping("/{id}") public FruitResponse one(
            @PathVariable Long id){return s.one(id);}
    @PostMapping
    public ResponseEntity<FruitResponse>
    create(@Valid @RequestBody FruitRequest r){
    return ResponseEntity.status(HttpStatus.CREATED).body(s.create(r));}
    @PutMapping("/{id}") public FruitResponse update(
            @PathVariable Long id,
            @Valid @RequestBody FruitRequest r){return s.update(id,r);}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) public void
    delete(@PathVariable Long id){s.delete(id);}}
