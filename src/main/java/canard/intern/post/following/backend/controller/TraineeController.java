package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.enums.Gender;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController //controller pour les API
@RequestMapping("/api/trainees")
public class TraineeController {



    @GetMapping
    public List<TraineeDto> getAll(){
        return List.of(
                TraineeDto.builder()
                        .id(1)
                        .lastName("Bond")
                        .firstName("James")
                        .gender(Gender.M)
                        .birthDate(LocalDate.of(1945,6,16))
                        .build(),
                TraineeDto.builder()
                        .id(2)
                        .lastName("MoneyPenny")
                        .firstName("Miss")
                        .gender(Gender.F)
                        .birthDate(LocalDate.of(1965,2,23))
                        .build(),
                TraineeDto.builder()
                        .id(3)
                        .lastName("Neymar")
                        .firstName("Jean")
                        .gender(Gender.X)
                        .birthDate(LocalDate.of(1999,1,14))
                        .build()

        );
    }

    /**
     * GET /api/trainees/{id}
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public TraineeDto getById(@PathVariable("id") int id){
        return TraineeDto.builder()
                .id(id) // mirror
                .lastName("Neymar")
                .firstName("Jean")
                .gender(Gender.X)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeDto create(@Valid @RequestBody TraineeDto traineeDto){
        traineeDto.setId(10);
        return traineeDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // obligatoire dans le cas d'un void
    public void delete(@PathVariable("id") int id){

    }

   @PutMapping("/{id}")
   public TraineeDto update(
           @PathVariable("id") int id,
           @Valid @RequestBody TraineeDto traineeDto){
        if(Objects.nonNull(traineeDto.getId() ) &&  (traineeDto.getId() != id) ){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("id <%d> from path does not match id <%d> from body",
                            id, traineeDto.getId())
            );
            // NB: you can use also: MessageFormat.format or StringBuilder
        }
        return traineeDto;

    }
}

