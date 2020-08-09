package guru.springframework.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity

public class UnitOfMeasure extends BaseDomain {
    @Builder
    public UnitOfMeasure(Long id, String description) {
        super(id, description);
    }
}