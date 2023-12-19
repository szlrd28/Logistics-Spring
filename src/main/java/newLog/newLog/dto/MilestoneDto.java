package newLog.newLog.dto;

import newLog.newLog.model.Address;

import java.time.LocalDateTime;

public class MilestoneDto {

    private Long id;
    private LocalDateTime plannedTime;
    private Address address;

    public MilestoneDto() {}

    public MilestoneDto(Long id, LocalDateTime plannedTime, Address address) {
        super();
        this.id = id;
        this.plannedTime = plannedTime;
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getPlannedTime() {
        return plannedTime;
    }
    public void setPlannedTime(LocalDateTime plannedTime) {
        this.plannedTime = plannedTime;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
}
