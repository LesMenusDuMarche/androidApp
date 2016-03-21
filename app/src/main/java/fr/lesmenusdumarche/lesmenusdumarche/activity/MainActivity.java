package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.NavigationStep;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Receipe;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.NavigationStepService;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Receipe> receipes = new ArrayList<>();
        receipes.add(Receipe.builder().title("Toto").body("Tata").build());

        NavigationStepService nsservice = RestManager.getNavigationStepService();

        Call<List<NavigationStep>> call = nsservice.list(receipes);

        call.enqueue(new Callback<List<NavigationStep>>() {
            @Override
            public void onResponse(Call<List<NavigationStep>> call, Response<List<NavigationStep>> response) {
                List<NavigationStep> toto = response.body();
                Log.i("Fait", "Fait");
            }

            @Override
            public void onFailure(Call<List<NavigationStep>> call, Throwable t) {
                Log.i("Fait", "Fait");
            }
        });
    }
}