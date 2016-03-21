package fr.lesmenusdumarche.lesmenusdumarche.restservice;

import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Market;
import retrofit2.Call;

/**
 * Created by maxime on 17/03/2016.
 */
public interface RestService<T> {
    Call<List<T>> list();
}
