package fr.lesmenusdumarche.lesmenusdumarche.cache;

import android.util.Log;

import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Market;
import fr.lesmenusdumarche.lesmenusdumarche.domain.PersistableEntity;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by maxime on 17/03/2016.
 *
 * EntityCacher class
 * Caches in database the response of rest webservice, for all persisted entities of domain
 */
public abstract class EntityCacher<RestService, T extends PersistableEntity> {

    /**
     * Rest call
     */
    Call<List<T>> restEntities = null;

    /**
     * Loads data from REST webservice and caches it in database, asynchronously
     */
    public void cacheFromRest() {
        // Make the request and define the callback
        restEntities.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                // If request succeeded, save data
                saveInDatabase(response.body());
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                // If not succeed, log the error
                Log.e("APP", "Error when getting markets");
            }
        });
    }

    /**
     * Saves all the rest found entities in database
     * @param entities The REST found entities
     */
    protected void saveInDatabase(List<T> entities) {
        for(T e : entities) {
            // Persists the element in db
            e.save();
        }
    }
}
