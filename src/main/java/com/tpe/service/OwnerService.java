package com.tpe.service;

import com.tpe.domain.Owner;
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
}
