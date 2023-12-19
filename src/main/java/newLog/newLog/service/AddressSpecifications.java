package newLog.newLog.service;

import newLog.newLog.model.Address;
import newLog.newLog.model.Address_;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecifications {

    public static Specification<Address> likeCity(String city) {
        return (root, cq, cb)-> cb.like(cb.upper(root.get(Address_.city)), city.toUpperCase() + "%");
    }

    public static Specification<Address> likeStreet(String street) {
        return (root, cq, cb)-> cb.like(cb.upper(root.get(Address_.street)), street.toUpperCase() + "%");
    }

    public static Specification<Address> equalcountryIsoCode(String countryIsoCode) {
        return (root, cq, cb)-> cb.equal(root.get(Address_.countryIsoCode), countryIsoCode);
    }

    public static Specification<Address> equalZipCode(String zipCode) {
        return (root, cq, cb)-> cb.equal(root.get(Address_.zipCode), zipCode);
    }
}
