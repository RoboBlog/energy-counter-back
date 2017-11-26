package pl.energycoutner.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
public class Measurement {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private EnergyMeter energyMeter;
    private YearMonth date;
    @OneToOne
    private TariffGroup tariffGroup;
    private BigDecimal price;
    private BigDecimal powerConsumption;

    public Measurement() {
    }

    public Measurement(EnergyMeter energyMeter, YearMonth date, BigDecimal price, BigDecimal powerConsumption, TariffGroup tariffGroup) {
        this.energyMeter = energyMeter;
        this.date = date;
        this.price = price;
        this.powerConsumption = powerConsumption;
        this.tariffGroup = tariffGroup;
    }

    public TariffGroup getTariffGroup() {
        return tariffGroup;
    }

    public BigDecimal getPowerConsumption() {
        return powerConsumption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnergyMeter getEnergyMeter() {
        return energyMeter;
    }

    public YearMonth getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", energyMeter=" + energyMeter +
                ", date=" + date +
                ", tariffGroup=" + tariffGroup +
                ", price=" + price +
                ", powerConsumption=" + powerConsumption +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurement that = (Measurement) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (energyMeter != null ? !energyMeter.equals(that.energyMeter) : that.energyMeter != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (tariffGroup != null ? !tariffGroup.equals(that.tariffGroup) : that.tariffGroup != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return powerConsumption != null ? powerConsumption.equals(that.powerConsumption) : that.powerConsumption == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (energyMeter != null ? energyMeter.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (tariffGroup != null ? tariffGroup.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (powerConsumption != null ? powerConsumption.hashCode() : 0);
        return result;
    }
}
