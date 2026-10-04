package com.toyota.bms.service; import com.toyota.bms.dto.*; import com.toyota.bms.dto.Responses.GarbageResponse; import com.toyota.bms.entity.GarbageDailyCount; import com.toyota.bms.exception.ApiExceptions.*; import com.toyota.bms.repository.GarbageRepository; import org.springframework.data.domain.Sort; import org.springframework.stereotype.Service; import java.time.*; import java.time.format.TextStyle; import java.util.*;
@Service public class GarbageService {
    private final GarbageRepository repo;
    public GarbageService(GarbageRepository r)
    {repo=r;}
    private String day(LocalDate d)
    {return
            d.getDayOfWeek().
                    getDisplayName(TextStyle.FULL,Locale.ENGLISH);}
    private GarbageResponse out(GarbageDailyCount e){
        return new GarbageResponse(
                e.getId(),
                e.getDate(),
                day(e.getDate()),
                e.getPaper(),
                e.getPlastic(),
                e.getGlass(),
                e.getMetal(),
                e.getWood(),
                e.geteWaste(),
                e.getOrganicFood());}
    public List<GarbageResponse> all(){
        return repo
                .findAll(Sort.by(Sort.Direction.DESC,"date"))
                .stream()
                .map(this::out).toList();}
    public GarbageResponse one(Long id){
        return out(repo.findById(id)
                .orElseThrow(()->new
                        NotFound("Garbage record not found: "+id)));}
    private void fill(GarbageDailyCount e,
                      GarbageRequest r){
        e.setDate(r.date());
        e.setPaper(r.paper());
        e.setPlastic(r.plastic());
        e.setGlass(r.glass());
        e.setMetal(r.metal());
        e.setWood(r.wood());
        e.seteWaste(r.eWaste());
        e.setOrganicFood(r.organicFood());}
    public GarbageResponse create(GarbageRequest r){
        if(repo.existsByDate(r.date()))
            throw new Conflict("Garbage record already exists for "+r.date());
        GarbageDailyCount e=new GarbageDailyCount();
        fill(e,r);
        return out(repo.save(e));}
    public GarbageResponse update(Long id,GarbageRequest r)
    {GarbageDailyCount e=repo
            .findById(id)
            .orElseThrow(()->new NotFound("Garbage record not found: "+id));
        repo.findByDate(r.date())
                .filter(x->!x.getId()
                        .equals(id))
                .ifPresent(x->{throw new Conflict("Garbage record already exists for "
                        +r.date());});
        fill(e,r);
        return out(repo.save(e));}
    public void delete(Long id){if(!repo.existsById(id))
        throw new NotFound("Garbage record not found: "+id);
        repo.deleteById(id);}}
