package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Address;
import pdp.uz.jparelationships.entity.Region;
import pdp.uz.jparelationships.payload.AddressDto;
import pdp.uz.jparelationships.repository.AddressRepo;
import pdp.uz.jparelationships.repository.RegionRepo;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    RegionRepo regionRepo;

    @GetMapping
    public List<Address> getAllAddress() {
        return addressRepo.findAll();
    }

    @GetMapping("/{id}")
    public Address getOneAddress(@PathVariable Integer id) {
        final Optional<Address> optionalCountry = addressRepo.findById(id);
        return optionalCountry.orElseGet(Address::new);
    }


    @PostMapping
    public String addAddress(@RequestBody AddressDto addressDto) {
        final Optional<Region> optionalRegion = regionRepo.findById(addressDto.getRegionId());
        if (optionalRegion.isPresent()) {
            Address address = new Address();
            address.setStreet(addressDto.getStreet());
            address.setHomeNumber(addressDto.getHomeNumber());
            address.setRegion(optionalRegion.get());
            addressRepo.save(address);
            return "Added";
        }
        return "District not found";
    }

    @PutMapping("/{id}")
    public String updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        final Optional<Address> optionalAddress = addressRepo.findById(id);
        if (optionalAddress.isPresent()) {
            final Optional<Region> optionalRegion = regionRepo.findById(addressDto.getRegionId());
            if (optionalRegion.isPresent()) {
                Address address = new Address();
                address.setStreet(addressDto.getStreet());
                address.setHomeNumber(addressDto.getHomeNumber());
                address.setRegion(optionalRegion.get());
                addressRepo.save(address);
                return "Updated";
            } else {
                return "region not found";
            }
        }
        return "Addres not found";
    }

    @DeleteMapping("/{id}")
    public String deleteContinent(@PathVariable Integer id){
        try {
            addressRepo.deleteById(id);
        }catch (Exception e){
            return "Not Deleted";
        }
        return "Deleted";

    }
}
