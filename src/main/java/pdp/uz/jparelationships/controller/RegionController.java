package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Country;
import pdp.uz.jparelationships.entity.Region;
import pdp.uz.jparelationships.payload.RegionDto;
import pdp.uz.jparelationships.repository.CountryRepo;
import pdp.uz.jparelationships.repository.RegionRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionRepo regionRepo;

    @Autowired
    CountryRepo countryRepo;

    @GetMapping
    public List<Region> getAllRegions() {
        return regionRepo.findAll();
    }

    @GetMapping("/{id}")
    public Region getOneRegion(@PathVariable Integer id) {
        final Optional<Region> optionalRegion = regionRepo.findById(id);
        return optionalRegion.orElseGet(optionalRegion::get);
    }

    @PostMapping
    public String addRegion(@RequestBody RegionDto regionDto) {
        final Optional<Country> optionalCountry = countryRepo.findById(regionDto.getCountryId());
        if (optionalCountry.isPresent()) {
            try {
                regionRepo.save(new Region(null, regionDto.getName(), optionalCountry.get()));
                return "Region added";
            } catch (Exception e) {
                return "already exist";
            }
        }
        return "country not found";
    }

    @PutMapping("/{id}")
    public String updateRegion(@PathVariable Integer id, @RequestBody RegionDto regionDto) {
        final Optional<Region> optionalRegion = regionRepo.findById(id);
        if (optionalRegion.isPresent()) {
            final Optional<Country> optionalCountry = countryRepo.findById(regionDto.getCountryId());
            if (optionalCountry.isPresent()) {
                try {
                    regionRepo.save(new Region(id, regionDto.getName(), optionalCountry.get()));
                    return "Updated";
                } catch (Exception e) {
                    return "Region already exist";
                }

            } else {
                return " Country not found";
            }
        }
        return "Region not found";
    }

    @DeleteMapping("/{id}")
    public String deleteRegion(@PathVariable Integer id) {
        try {
            regionRepo.deleteById(id);
        }catch (Exception e){
            return "Not  Deleted";
        }
        return "Deleted";
    }
}
