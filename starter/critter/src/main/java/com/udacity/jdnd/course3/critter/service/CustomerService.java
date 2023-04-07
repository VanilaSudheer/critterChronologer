package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Component
@Transactional
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PetRepository petRepository;
    public CustomerDTO addCustomer(CustomerDTO dto) {
        Customer savedCustomer =customerRepository.save(toEntity(dto));

        return toDTO(savedCustomer);
    }
    public List<CustomerDTO> getAll() {

        Iterator<Customer> allCustomerList = customerRepository.findAll().iterator();
        List<CustomerDTO> returnList = new ArrayList<CustomerDTO>();

        while(allCustomerList.hasNext()) {

            Customer tmpCustomer = allCustomerList.next();

            if(null == tmpCustomer.getPets()) {
                tmpCustomer.setPets(petRepository.findPetsByOwner(tmpCustomer));
            }

            returnList.add(toDTO(tmpCustomer));

        }

        return returnList;
    }
    public CustomerDTO getOwnerByPet(long petId){

        Pet pet = petRepository.findById(petId);

        Customer petOwner = pet.getOwner();

        if(null == petOwner.getPets()) {
            petOwner.setPets(petRepository.findPetsByOwner(petOwner));
        }

        return toDTO(petOwner);

    }

    public void addPet(Customer customer, Pet pet){
        Set<Pet> pets = customer.getPets();
        if (pets == null) {
            pets = new HashSet<>();
            customer.setPets(pets);
        }
        pets.add(pet);
        customerRepository.save(customer);
    }

    private CustomerDTO toDTO(Customer entity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(entity.getId());
        customerDTO.setName(entity.getName());
        customerDTO.setPhoneNumber(entity.getPhoneNumber());
        customerDTO.setNotes(entity.getNotes());

        List<Long> petIds = new ArrayList<Long>();

        if(null != entity.getPets()) {
            for(Pet pet : entity.getPets()) {
                petIds.add(pet.getId());
            }
        }

        customerDTO.setPetIds(petIds);


        return customerDTO;
    }
    private Customer toEntity(CustomerDTO dto) {
        Customer entity = new Customer();
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setNotes(dto.getNotes());
        return entity;
    }

}
