package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;

    public PetDTO addPet(PetDTO dto) {
        Pet pet = new Pet();
        pet.setName(dto.getName());
        pet.setBirthDate(dto.getBirthDate());
        pet.setNotes(dto.getNotes());
        pet.setType(dto.getType());
        Customer customer = customerRepository.findById(dto.getOwnerId());
        pet.setOwner(customer);
        Pet savedPet = petRepository.save(pet);
        customerService.addPet(customer,savedPet);


        return toDTO(savedPet);
    }
    public PetDTO findPetById(long id){
        Pet pet = petRepository.findById(id);
        return toDTO(pet);
    }

    public List<PetDTO> findPetsByOwner(long ownerId){
        Customer customer = customerRepository.findById(ownerId);
        Iterator<Pet> allList;
        if(null != customer.getPets()) {

            allList = customer.getPets().iterator();
        }else {

            allList = petRepository.findPetsByOwner(customer).iterator();

        }
        List<PetDTO> returnList = new ArrayList<PetDTO>();

        while(allList.hasNext()) {

            Pet tmpPet = allList.next();

            returnList.add(toDTO(tmpPet));

        }


        return returnList;
    }

    public List<PetDTO> getAll() {

        Iterator<Pet> allList = petRepository.findAll().iterator();
        List<PetDTO> returnList = new ArrayList<PetDTO>();

        while(allList.hasNext()) {

            Pet tmpPet = allList.next();

            returnList.add(toDTO(tmpPet));

        }

        return returnList;
    }


    private PetDTO toDTO(Pet entity) {
        PetDTO dto = new PetDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirthDate(entity.getBirthDate());
        dto.setNotes(entity.getNotes());
        dto.setType(entity.getType());
        dto.setOwnerId(entity.getOwner().getId());

        return dto;
    }

}
