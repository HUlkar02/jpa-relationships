package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.District;
import pdp.uz.jparelationships.entity.Region;
import pdp.uz.jparelationships.payload.DistrictDto;
import pdp.uz.jparelationships.repository.DistrictRepo;
import pdp.uz.jparelationships.repository.RegionRepo;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    DistrictRepo districtRepo;

    @Autowired
    RegionRepo regionRepo;


    @GetMapping
    public List<District> getAllDistrict() {
        return districtRepo.findAll();
    }


    @GetMapping("/{id}")
    public District getOneDistrict(@PathVariable Integer id) {
        final Optional<District> optionalDistrict = districtRepo.findById(id);
        return optionalDistrict.orElseGet(District::new);
    }

    @PostMapping
    public String addDistrict(@RequestBody DistrictDto districtDto) {
        final Optional<Region> optionalRegion = regionRepo.findById(districtDto.getRegionId());
        if (optionalRegion.isPresent()) {
                districtRepo.save(new District(null, districtDto.getName(),optionalRegion.get()));
                return "Added";
        }
        return "Not found";
    }

    @PutMapping("/{id}")
    public String updateDistrict(@PathVariable Integer id,@RequestBody DistrictDto districtDto){
        final Optional<District> optionalDistrict = districtRepo.findById(id);
        if (optionalDistrict.isPresent()){
            final Optional<Region> optionalRegion = regionRepo.findById(districtDto.getRegionId());
            if (optionalRegion.isPresent()){
                districtRepo.save(new District(id,districtDto.getName(),optionalRegion.get()));
                return "Updated";
            }else {
                return "region not found";
            }
        }
        return "District not found";
    }

    @DeleteMapping("/{id}")
    public String deleteCountry(@PathVariable Integer id){
        try {
            districtRepo.deleteById(id);
        }catch (Exception e){
            return "Not Deleted";
        }
        return "Deleted";

    }
}
