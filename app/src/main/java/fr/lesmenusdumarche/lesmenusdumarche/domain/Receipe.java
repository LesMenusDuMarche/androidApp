package fr.lesmenusdumarche.lesmenusdumarche.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 * Created by maxime on 17/03/2016.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Receipe {

    @Getter
    @Setter
    Double id;

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
