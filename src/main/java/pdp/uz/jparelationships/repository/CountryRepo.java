package pdp.uz.jparelationships.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.jparelationships.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {
}
