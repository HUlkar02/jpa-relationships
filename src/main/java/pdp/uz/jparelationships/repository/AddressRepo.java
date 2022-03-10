package pdp.uz.jparelationships.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.jparelationships.entity.Address;

public interface AddressRepo extends JpaRepository<Address,Integer> {
}
