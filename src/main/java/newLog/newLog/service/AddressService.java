package newLog.newLog.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import newLog.newLog.dto.AddressSearchDto;
import newLog.newLog.exception.InvalidAddressException;
import newLog.newLog.model.Address;
import newLog.newLog.repository.AddressRepository;
import newLog.newLog.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

@Autowired
MilestoneService milestoneService;
    @Autowired
    AddressRepository addressRepository;



    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /*@Transactional
    public Optional<Address> saveAddress(Address address) {

        return Optional.of(addressRepository.save(address));
    }*/
    @Transactional
    public Address updateId(Address updatedAddress) {
        return addressRepository.findById(updatedAddress.getId())
                .map(existingAddress -> {

                    existingAddress.setCountryIsoCode(updatedAddress.getCountryIsoCode());
                    existingAddress.setCity(updatedAddress.getCity());
                    existingAddress.setStreet(updatedAddress.getStreet());
                    existingAddress.setZipCode(updatedAddress.getZipCode());
                    existingAddress.setHouseNumber(updatedAddress.getHouseNumber());
                    existingAddress.setLatitude(updatedAddress.getLatitude());
                    existingAddress.setLongitude(updatedAddress.getLongitude());


                    return addressRepository.save(existingAddress);
                }).orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + updatedAddress.getId()));
    }

    /*@Transactional
    public Address updateId(Address address) {
        if(!addressRepository.existsById(address.getId())) {
            return null;
        } else {
            return addressRepository.save(address);
        }
    }*/

    @Transactional
    public Address saveAddress(Address address) throws InvalidAddressException {
        if (address.getId() != null) {
            throw new InvalidAddressException("Id must be null for a new Address");
        }
        validateAddress(address);
        return addressRepository.save(address);
    }

    private void validateAddress(Address address) throws InvalidAddressException {
        if (address.getCountryIsoCode() == null || address.getCountryIsoCode().isEmpty() ||
                address.getCity() == null || address.getCity().isEmpty() ||
                address.getZipCode() == null || address.getZipCode().isEmpty() ||
                address.getStreet() == null || address.getStreet().isEmpty() ||
                address.getHouseNumber() == null || address.getHouseNumber().isEmpty()) {
            throw new InvalidAddressException("Invalid address fields");
        }
    }
    @Transactional
    public Address delete(Long id) {
        if (addressRepository.findById(id).isPresent()) {
            if (!milestoneService.findByAddressId(id).isEmpty())
                return null;
            addressRepository.deleteById(id);
        }
        return null;
    }
    public Address getAddressId(Long id) {
        return addressRepository.findAddress(id);
    }
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }



    public Page<Address> findAddressesByExample(AddressSearchDto exampleDto,
                                                @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = Integer.MAX_VALUE) Pageable pageable) {
        Specification<Address> spec = (root, query, cb) -> cb.conjunction();

        if (exampleDto.getCity()!=null) {
            spec = spec.and(AddressSpecifications.likeCity(exampleDto.getCity()));
        }

        if (exampleDto.getStreet()!=null) {
            spec = spec.and(AddressSpecifications.likeStreet(exampleDto.getStreet()));
        }

        if (exampleDto.getZipCode()!=null) {
            spec = spec.and(AddressSpecifications.equalZipCode(exampleDto.getZipCode()));
        }

        if (exampleDto.getCountryIsoCode()!=null) {
            spec = spec.and(AddressSpecifications.equalcountryIsoCode(exampleDto.getCountryIsoCode()));
        }

        return addressRepository.findAll(spec, pageable);
    }
}
