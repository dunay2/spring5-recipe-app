package guru.springframework.commands;


import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnitOfMeasureCommand extends BaseCommand{

    @Builder
    public UnitOfMeasureCommand(Long id, String description) {
        super(id, description);
    }
}
