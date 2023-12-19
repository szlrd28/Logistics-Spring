package newLog.newLog.model;

import jakarta.persistence.*;


@Entity
public class Section {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Milestone fromMilestone;

    @OneToOne
    private Milestone toMilestone;

    @ManyToOne
    @JoinColumn(name = "transportlan_id")
    private TransportPlan transportplan;

    private Integer sectNumber;

    public Section() {}

    public Section(Integer sectNumber, Milestone fromMilestone, Milestone toMilestone) {
        this.fromMilestone = fromMilestone;
        this.toMilestone = toMilestone;
        this.sectNumber = sectNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Milestone getFromMilestone() {
        return fromMilestone;
    }

    public void setFromMilestone(Milestone fromMilestone) {
        this.fromMilestone = fromMilestone;
    }

    public Milestone getToMilestone() {
        return toMilestone;
    }

    public void setToMilestone(Milestone toMilestone) {
        this.toMilestone = toMilestone;
    }

    public Integer getSectNumber() {
        return sectNumber;
    }

    public void setSectNumber(Integer sectNumber) {
        this.sectNumber = sectNumber;
    }

    public TransportPlan getTransportplan() {
        return transportplan;
    }

    public void setTransportplan(TransportPlan transportplan) {
        this.transportplan = transportplan;
    }
}