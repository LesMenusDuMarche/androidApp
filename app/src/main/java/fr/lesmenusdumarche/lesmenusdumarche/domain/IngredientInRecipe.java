package fr.lesmenusdumarche.lesmenusdumarche.domain;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

/**
 * Created by maxime on 17/03/2016.
 *
 * Represents an ingredient, used in a recipe
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientInRecipe implements Parcelable {

    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    String label;

    @Getter
    @Setter
    String amount;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(label);
        dest.writeString(amount);
    }
}