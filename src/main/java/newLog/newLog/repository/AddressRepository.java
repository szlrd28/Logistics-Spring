package newLog.newLog.repository;

import newLog.newLog.model.Address;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address>  {


    @Query("SELECT a FROM Address a WHERE a.id=:id")
    public Address findAddress(Long id);

}
