package guru.springframework.commands;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseCommand {

    private Long id;
    private String description;

}
