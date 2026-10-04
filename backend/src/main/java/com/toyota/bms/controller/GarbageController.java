package com.toyota.bms.controller; import com.toyota.bms.dto.*; import com.toyota.bms.dto.Responses.GarbageResponse; import com.toyota.bms.service.GarbageService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController
@RequestMapping("/api/garbage")
public class GarbageController
{private final GarbageService s;
    public GarbageController(GarbageService s)
    {this.s=s;}

    @GetMapping
    public List<GarbageResponse> all(){return s.all();}
    @GetMapping
            ("/{id}") public GarbageResponse one
            (@PathVariable Long id){return s.one(id);}
    @PostMapping
    public ResponseEntity<GarbageResponse>
    create(@Valid @RequestBody GarbageRequest r){
        return ResponseEntity.status(HttpStatus.CREATED).body(s.create(r));}
    @PutMapping("/{id}") public GarbageResponse
    update(@PathVariable Long id,
           @Valid @RequestBody GarbageRequest r){return s.update(id,r);}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) public void
    delete(@PathVariable Long id){s.delete(id);}}
