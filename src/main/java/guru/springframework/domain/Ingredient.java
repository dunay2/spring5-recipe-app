package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by jt on 6/13/17.
 */
@EqualsAndHashCode(exclude = {"recipe"}, callSuper = true)
@Data
@Entity
public class Ingredient extends BaseDomain {

    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    @Builder
    public Ingredient(Long id, String description, BigDecimal amount, UnitOfMeasure uom) {
        super(id, description);
        this.amount = amount;
        this.uom = uom;
    }
}










