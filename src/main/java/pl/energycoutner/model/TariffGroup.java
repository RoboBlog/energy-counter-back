package pl.energycoutner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class TariffGroup {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String code;
    private BigDecimal price;
    private String costCalculationExpressionMethod;

    public TariffGroup(String code, BigDecimal price, String costCalculationExpressionMethod) {
        this.code = code;
        this.price = price;
        this.costCalculationExpressionMethod = costCalculationExpressionMethod;
    }

    public TariffGroup() {
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCostCalculationExpressionMethod() {
        return costCalculationExpressionMethod;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TariffGroup{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", costCalculationExpressionMethod='" + costCalculationExpressionMethod + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TariffGroup that = (TariffGroup) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return costCalculationExpressionMethod != null ? costCalculationExpressionMethod.equals(that.costCalculationExpressionMethod) : that.costCalculationExpressionMethod == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (costCalculationExpressionMethod != null ? costCalculationExpressionMethod.hashCode() : 0);
        return result;
    }
}
