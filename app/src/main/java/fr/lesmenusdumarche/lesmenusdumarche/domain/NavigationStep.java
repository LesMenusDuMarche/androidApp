package fr.lesmenusdumarche.lesmenusdumarche.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by maxime on 21/03/2016.
 *
 * Represents a navigation point for the
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NavigationStep {

    @Getter @Setter
    private Long latitude;

    @Getter @Setter
    private Long longitude;

    @Getter @Setter
    private IngredientInReceipe ingredient;

    @Getter @Setter
    private boolean done;
}
