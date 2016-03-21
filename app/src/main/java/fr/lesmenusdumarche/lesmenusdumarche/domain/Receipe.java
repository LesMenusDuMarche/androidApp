package fr.lesmenusdumarche.lesmenusdumarche.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

/**
 * Created by maxime on 17/03/2016.
 *
 * Represents a cook receipe
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Receipe extends PersistableEntity {

    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    String body;

    @Getter
    @Setter
    List<IngredientInReceipe> ingredients;
}
