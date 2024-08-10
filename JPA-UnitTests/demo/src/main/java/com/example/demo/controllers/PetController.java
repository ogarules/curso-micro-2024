package com.example.demo.controllers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Pet;
import com.example.demo.services.PetService;
import com.example.demo.validations.OnAdd;
import com.example.demo.validations.OnUpdate;

import javax.validation.groups.Default;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import javax.validation.constraints.Min;
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pets")
@Validated
public class PetController {
    private final PetService service;
    private final SmartValidator validator;

    // @PostMapping()
    // public ResponseEntity<Pet> addPet(@RequestBody Pet entity, BindingResult bindingResult) {
    //     log.info("Adding pet {}", entity);
        
    //     this.validator.validate(entity, bindingResult);
    //     if(bindingResult.hasErrors()){
    //         return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
    //     }

    //     return ResponseEntity.ok(service.addPet(entity));
    // }

    @PostMapping()
    public Pet addPet(@Validated({OnAdd.class, Default.class}) @RequestBody Pet entity) {
        log.info("Adding pet {}", entity);

        return service.addPet(entity);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        log.info("Getting pet {}", id);
        return service.getPetById(id);
    }
    
    @GetMapping
    public Iterable<Pet> getAllPets(@QuerydslPredicate(root = Pet.class) Predicate predicate,
                                    @SortDefault(sort = "name", direction = Direction.ASC)
                                    @PageableDefault(size = 5, page = 0)
                                    Pageable pageable) {
        log.info("Getting all pets");
        return service.getAllPets(predicate, pageable);
    }
    
    @DeleteMapping("/{id}")
    public void deletePetById(@PathVariable Long id){
        Pet pet = service.deletePetById(id);
        log.info("Pet deleted {}", pet);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable @Min(1) Long id, @Validated({OnUpdate.class, Default.class}) @RequestBody Pet entity) {
        log.info("Updating pet {}", entity);
        return service.updatePet(entity, id);
    }
}
