package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.NavigationStep;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<List<NavigationStep>> stepsRequest = RestManager.getNavigationStepService().list(new ArrayList<Recipe>());

        /*try {
            List<NavigationStep> steps = stepsRequest.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    static final int PICK_CONTACT_REQUEST = 1;  // The request code

    public void Selection_recettes(View v) {
        Intent intent = new Intent(this, Selection.class);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}