package fr.lesmenusdumarche.lesmenusdumarche.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    @SerializedName("lat")
    private Long latitude;

    @Getter @Setter
    @SerializedName("long")
    private Long longitude;

    @Getter @Setter
    private Seller seller;

    @Getter @Setter
    private List<IngredientInRecipe> ingredients;

    @Getter @Setter
    private boolean done = false;
}
