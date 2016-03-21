package fr.lesmenusdumarche.lesmenusdumarche.restservice;

import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Market;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Receipe;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by maxime on 17/03/2016.
 */
public interface ReceipeService extends RestService<Receipe> {
    @GET("receipe/")
    Call<List<Receipe>> list();
}