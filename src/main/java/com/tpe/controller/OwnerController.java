package com.tpe.controller;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OwnerController {

    //ResponseEntity:body(cevap) + status(200,300,400)
    //MyResponse:response body + status + mesaj

    private final OwnerService ownerService;


    //2- Find All Owners
    // http://localhost:8080/owners+GET
    @RequestMapping("/owners")
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners(){
        List<Owner> owners=ownerService.getAll();
        return ResponseEntity.ok(owners);
    }

    //1- Save an Owner
    // http://localhost:8080/owner/save + JSON + POST
    @RequestMapping("/owner/save")
    @PostMapping
    public ResponseEntity<String> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO){

        ownerService.saveOwner(ownerDTO);

        return new ResponseEntity<>("Üye başarılı bir şekilde oluşturuldu.", HttpStatus.CREATED);//201
    }

    //3- Find an Owner By ID
    // http://localhost:8080/owner/2 + GET
    @RequestMapping("/owner/{id}")
    @GetMapping
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable("id") Long id){
        OwnerDTO ownerDTO=ownerService.getOwnerDTOById(id);
        return ResponseEntity.ok(ownerDTO);
    }

}