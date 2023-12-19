package newLog.newLog.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.util.List;

public class AddressDto {

    private Long id;

    @NotEmpty
    @Size(min = 2,max = 2)
    private String countryIsoCode;
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    @NotEmpty
    private String zipCode;
    @NotEmpty
    private String houseNumber;

    @Range(min=-90, max = 90)
    private Double latitude;

    @Range(min=-180, max = 180)
    private Double longitude;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MilestoneDto> milestones;
    public AddressDto() {
    }

    public AddressDto(Long id, String countryIsoCode, String city, String street, String zipCode, String houseNumber, Double latitude, Double longitude, List<MilestoneDto> milestones) {
        this.id = id;
        this.countryIsoCode = countryIsoCode;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.houseNumber = houseNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.milestones = milestones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public List<MilestoneDto> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<MilestoneDto> milestones) {
        this.milestones = milestones;
    }
}
