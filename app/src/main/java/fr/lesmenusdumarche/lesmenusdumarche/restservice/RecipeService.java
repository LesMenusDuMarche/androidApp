package fr.lesmenusdumarche.lesmenusdumarche.restservice;

import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by maxime on 17/03/2016.
 */
public interface RecipeService {
    @GET("recipe/")
    Call<List<Recipe>> list();
}