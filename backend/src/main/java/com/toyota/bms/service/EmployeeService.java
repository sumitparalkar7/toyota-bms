package com.toyota.bms.service; import com.toyota.bms.dto.*; import com.toyota.bms.dto.Responses.EmployeeResponse; import com.toyota.bms.entity.EmployeeDailyCount; import com.toyota.bms.exception.ApiExceptions.*; import com.toyota.bms.repository.EmployeeRepository; import org.springframework.data.domain.Sort; import org.springframework.stereotype.Service; import java.time.*; import java.time.format.TextStyle; import java.util.*;
@Service public class EmployeeService {
    private final EmployeeRepository repo;
    public EmployeeService(EmployeeRepository r)
    {repo=r;}
    private String day(LocalDate d){
        return
                d.getDayOfWeek().getDisplayName(TextStyle.FULL,Locale.ENGLISH);}
    private EmployeeResponse out(EmployeeDailyCount e){
        return new EmployeeResponse(e.getId(),
                e.getDate(),
                day(e.getDate()),
                e.getEmployeeCount());}
    public List<EmployeeResponse> all(){
        return repo
                .findAll(Sort.by(Sort.Direction.DESC,"date"))
                .stream()
                .map(this::out)
                .toList();}
    public EmployeeResponse
    one(Long id){return out(repo
            .findById(id)
            .orElseThrow(()->new
                    NotFound("Employee record not found: "+id)));}
    public EmployeeResponse create(EmployeeRequest r){
        if(repo.existsByDate(r.date()))
            throw new Conflict("Employee record already exists for "+r.date());
        EmployeeDailyCount e=new EmployeeDailyCount();
        e.setDate(r.date());
        e.setEmployeeCount(r.employeeCount());
        return out(repo.save(e));}
    public EmployeeResponse
    update(Long id,EmployeeRequest r)
    {EmployeeDailyCount e=repo
            .findById(id)
            .orElseThrow(()->new
                    NotFound("Employee record not found: "+id));
        repo.findByDate(r.date())
            .filter(x->!x
                    .getId()
                    .equals(id))
            .ifPresent(x->{throw new Conflict("Employee record already exists for" +

                    r.date());});
                    e.setDate(r.date());
                    e.setEmployeeCount(r.employeeCount());
                    return out(repo.save(e));}
    public void delete(Long id)
    {if(!repo.existsById(id))throw new
            NotFound("Employee record not found: "+id);
        repo.deleteById(id);}}
