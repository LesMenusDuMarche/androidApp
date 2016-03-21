package fr.lesmenusdumarche.lesmenusdumarche.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

/**
 * Created by maxime on 17/03/2016.
 *
 * Represents a cook recipe
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends PersistableEntity implements Parcelable {

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    String body;

    @Getter
    @Setter
    List<IngredientInRecipe> ingredients;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(body);
        dest.writeList(ingredients);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>()
    {
        @Override
        public Recipe createFromParcel(Parcel source)
        {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size)
        {
            return new Recipe[size];
        }
    };

    public Recipe(Parcel in) {
        this.title = in.readString();
        this.body = in.readString();
        in.readList(this.ingredients, null);
    }
}
