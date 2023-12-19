package newLog.newLog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Milestone {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private LocalDateTime plannedTime;

    public Milestone(LocalDateTime plannedTime, Address address) {
        this.plannedTime = plannedTime;
        this.address = address;
    }

    public Milestone(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public LocalDateTime getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(LocalDateTime plannedTime) {
        this.plannedTime = plannedTime;
    }
}
