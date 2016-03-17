package fr.lesmenusdumarche.lesmenusdumarche.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by maxime on 17/03/2016.
 */
public class IngredientInReceipe {

    @Getter
    @Setter
    Double id;

    @Getter
    @Setter
    String label;

    @Getter
    @Setter
    String amount;
}
