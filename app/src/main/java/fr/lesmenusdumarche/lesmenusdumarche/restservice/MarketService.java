package fr.lesmenusdumarche.lesmenusdumarche.restservice;

import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Market;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by maxime on 17/03/2016.
 */
public interface MarketService {

    @GET("market/")
    Call<List<Market>> list();

}
