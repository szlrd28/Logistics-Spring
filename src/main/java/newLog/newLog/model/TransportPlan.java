package newLog.newLog.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TransportPlan {
        @Id
        @GeneratedValue
        private Long id;

        private Long income;

        @OneToMany(mappedBy="transportplan")
        private List<Section> sections;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getIncome() {
                return income;
        }

        public void setIncome(Long income) {
                this.income = income;
        }

        public List<Section> getSections() {
                return sections;
        }

        public void setSections(List<Section> sections) {
                this.sections = sections;
        }

        public void addSection(Section section) {
                if(this.sections == null)
                        this.sections = new ArrayList<>();
                this.sections.add(section);
                section.setTransportplan(this);
        }
}

