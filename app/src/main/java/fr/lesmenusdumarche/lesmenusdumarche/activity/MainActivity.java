package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.NavigationStep;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Call<List<NavigationStep>> stepsRequest = RestManager.getNavigationStepService().list(new ArrayList<Recipe>());

        stepsRequest.enqueue(new Callback<List<NavigationStep>>() {
            @Override
            public void onResponse(Call<List<NavigationStep>> call, Response<List<NavigationStep>> response) {
                List<NavigationStep> steps = response.body();
                Log.i("Toto", "toto");
            }

            @Override
            public void onFailure(Call<List<NavigationStep>> call, Throwable t) {
                Log.i("Toto", "toto");
            }
        });*/

    }

    static final int PICK_CONTACT_REQUEST = 1;  // The request code

    public void Selection_recettes(View v) {
        Intent intent = new Intent(this, Selection.class);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}