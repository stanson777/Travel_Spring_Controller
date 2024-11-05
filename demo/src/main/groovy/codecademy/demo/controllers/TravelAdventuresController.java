package codecademy.demo.controllers;



import codecademy.demo.entities.Adventure;
import codecademy.demo.repositories.AdventureRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        this.adventureRepository = adventureRepo;
    }

    // Add controller methods below:
    @GetMapping()
    public Iterable<Adventure> getAllAdventures(){
        return this.adventureRepository.findAll();

    }

    @GetMapping("/bycountry/{country}")
    public List<Adventure> getByCountry(@PathVariable("country") String country){
        return this.adventureRepository.findByCountry(country);
    }
    @GetMapping("/bystate")
    public List<Adventure> getByState(@RequestParam String state){
        return this.adventureRepository.findByState(state);
    }

    @PostMapping()
    public Adventure addAdventure(@RequestBody Adventure adventure){
        Adventure newAdventure=this.adventureRepository.save(adventure);

        return newAdventure;
    }
    @PutMapping("/{id}")
    public Adventure updateAdventure(@PathVariable("id") Integer id,@RequestBody Adventure adventure){
        Optional<Adventure> adventureOptional=this.adventureRepository.findById(id);
        if(!adventureOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Adventure not found");
        }
        Adventure adventureUp = adventureOptional.get();
        if(!adventure.getBlogCompleted()){
            adventureUp.setBlogCompleted(true);
        }else{
            adventureUp.setBlogCompleted(false);
        }
        Adventure updatedAdventure=this.adventureRepository.save(adventureUp);

        return updatedAdventure;
    }

    @DeleteMapping("/{id}")
    public void removeAdventure(@PathVariable("id") Integer id){
        Optional<Adventure> adventureToRemove=this.adventureRepository.findById(id);
        if(adventureToRemove.isPresent()){
            Adventure adventureToDelete=adventureToRemove.get();
            this.adventureRepository.delete(adventureToDelete);
        }
    }
}

