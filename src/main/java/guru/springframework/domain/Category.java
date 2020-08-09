package guru.springframework.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude={"recipes"})
@Entity
public class Category extends BaseDomain {

    @ManyToMany(mappedBy = "categories" )
    private Set<Recipe>recipes= new HashSet<>();

}
