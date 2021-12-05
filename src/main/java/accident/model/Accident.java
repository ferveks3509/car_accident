package accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType accidentType;

    @OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "accident_rule",
            joinColumns = {@JoinColumn(name = "accident_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "rule_id", updatable = false)})
    private Set<Rule> rules = new HashSet<>();

    public Accident(int id, String name, String text, String address, AccidentType accidentType, Set<Rule> rule) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.accidentType = accidentType;
        this.rules = rule;
    }

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(AccidentType accidentType) {
        this.accidentType = accidentType;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Accident{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", address='" + address + '\'' +
                ", accidentType=" + accidentType +
                ", rules=" + rules +
                '}';
    }
}
