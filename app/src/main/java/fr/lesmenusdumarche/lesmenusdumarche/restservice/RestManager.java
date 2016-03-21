package fr.lesmenusdumarche.lesmenusdumarche.restservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by maxime on 17/03/2016.
 */
public class RestManager {

    //private static final String API_URL = "http://api.lesmenusdumarche.fr";
    private static final String API_URL = "http://46.101.224.193";

    private static final Retrofit REST_ADAPTER = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final MarketService MARKET_SERVICE = REST_ADAPTER.create(MarketService.class);
    private static final ReceipeService RECEIPE_SERVICE = REST_ADAPTER.create(ReceipeService.class);

    public static MarketService getMarketService() {
        return MARKET_SERVICE;
    }
    public static ReceipeService getReceipeService() {
        return RECEIPE_SERVICE;
    }
}