package fr.lesmenusdumarche.lesmenusdumarche.restservice;

import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.domain.NavigationStep;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Receipe;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by maxime on 17/03/2016.
 */
public interface NavigationStepService {
    @POST("navigationstep/")
    Call<List<NavigationStep>> list(@Body List<Receipe> receipes);
}