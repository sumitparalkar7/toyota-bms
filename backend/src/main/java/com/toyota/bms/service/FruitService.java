package com.toyota.bms.service; import com.toyota.bms.dto.*; import com.toyota.bms.dto.Responses.FruitResponse; import com.toyota.bms.entity.FruitDailyCount; import com.toyota.bms.exception.ApiExceptions.*; import com.toyota.bms.repository.FruitRepository; import org.springframework.data.domain.Sort; import org.springframework.stereotype.Service; import java.time.*; import java.time.format.TextStyle; import java.util.*;
@Service public class FruitService
{private final
FruitRepository repo;
    public FruitService(FruitRepository r){
        repo=r;}
    private String day(LocalDate d){
        return d.getDayOfWeek()
                .getDisplayName(TextStyle.FULL,Locale.ENGLISH);}
    private FruitResponse out(FruitDailyCount e){
        return new FruitResponse(e.getId(),
                e.getDate(),
                day(e.getDate()),
                e.getApple(),
                e.getBanana(),
                e.getPear(),
                e.getOrange(),
                e.getGuava());}
    public List<FruitResponse>
    all(){return repo
            .findAll(Sort.by(Sort.Direction.DESC,"date"))
            .stream()
            .map(this::out)
            .toList();}
    public FruitResponse one(Long id){
        return out(repo.findById(id)
                .orElseThrow(()->new
                        NotFound("Fruit record not found: "+id)));}
    private void fill(FruitDailyCount e,
                      FruitRequest r){e.setDate(r.date());
        e.setApple(r.apple());
        e.setBanana(r.banana());
        e.setPear(r.pear());
        e.setOrange(r.orange());
        e.setGuava(r.guava());}
    public FruitResponse
    create(FruitRequest r){if(repo.existsByDate(r.date()))
        throw new Conflict("Fruit record already exists for "+r.date());
        FruitDailyCount e=new FruitDailyCount();
        fill(e,r);
        return out(repo.save(e));}
    public FruitResponse update(Long id,FruitRequest r)
    {FruitDailyCount e=repo.findById(id).orElseThrow(()->new NotFound("Fruit record not found: "+id));
        repo.findByDate(r.date())
                .filter(x->!x
                        .getId()
                        .equals(id))
                .ifPresent(x->{throw new Conflict("Fruit record already exists for "+
                        r.date());});
        fill(e,r);
        return out(repo.save(e));}
    public void delete(Long id){if(!repo.existsById(id))
        throw new NotFound("Fruit record not found: "+id);
        repo.deleteById(id);}}
