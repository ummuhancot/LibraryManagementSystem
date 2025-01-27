package com.tpe.service;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.OwnerNotFoundException;
import com.tpe.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    //2-b
    public List<Owner> getAll() {

        List<Owner> owners=ownerRepository.findAll();
        if (owners.isEmpty()){
            throw new OwnerNotFoundException("Hiç üye bulunamadı!");
        }
        return owners;
    }

    //1-b
    public void saveOwner(OwnerDTO ownerDTO) {

        boolean exists=ownerRepository.existsByEmail(ownerDTO.getEmail());
        if (exists){
            throw new ConflictException("Bu email zaten kullanılıyor!");
        }

        //dto-->entity:mapOwnerDtoToOwner(mapper metodlar)
        Owner owner=new Owner();
        owner.setName(ownerDTO.getName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        owner.setEmail(ownerDTO.getEmail());

        ownerRepository.save(owner);

    }

    //3-b
    //1.yöntem:repodan entity-->DTO
    //2.yöntem:JPQL ile doğrudan DTO
    public OwnerDTO getOwnerDTOById(Long id) {
        OwnerDTO ownerDTO=ownerRepository.findOwnerDTOById(id).
                orElseThrow(()->new OwnerNotFoundException("Üye bulunamadı. ID : "+id));
        return ownerDTO;
    }

    //idsi verilen ownerı entity olarak alalım
    public Owner getOwnerById(Long id){
        return ownerRepository.findById(id).
                orElseThrow(()->new OwnerNotFoundException("Üye bulunamadı. ID : "+id));
    }
}