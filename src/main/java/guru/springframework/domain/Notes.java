package guru.springframework.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Data
@EqualsAndHashCode(exclude = {"recipe"}, callSuper = true)
@Entity
public class Notes extends BaseDomain {

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}