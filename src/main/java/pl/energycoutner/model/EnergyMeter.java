package pl.energycoutner.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EnergyMeter {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "energyMeter", cascade = CascadeType.REMOVE)
    private Set<Measurement> measurements;

    public EnergyMeter() {
    }

    public EnergyMeter(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EnergyMeter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnergyMeter that = (EnergyMeter) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
