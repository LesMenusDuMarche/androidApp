package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.location.Location;
import android.os.Bundle;


import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.NavigationStep;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import fr.lesmenusdumarche.lesmenusdumarche.fragment.CheckListFragment;
import fr.lesmenusdumarche.lesmenusdumarche.fragment.MapFragment;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.NavigationStepService;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;
import io.nlopez.smartlocation.SmartLocation;
import retrofit2.Call;
import retrofit2.Response;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends FragmentActivity {

    // Extra keys
    public final static String EXTRA_RECEIPES = "RECEIPES";

    // UI Components
    MapFragment mapFragment;
    CheckListFragment checkListFragment;

    // Data
    protected List<Recipe> recipes = new ArrayList<>();
    protected List<NavigationStep> steps = new ArrayList<>();
    protected NavigationStepService navigationStepService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        // UI Components
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        checkListFragment = (CheckListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_checklist);

        // Initialize REST service
        navigationStepService = RestManager.getNavigationStepService();

        // Get extras (receipes)
        recipes = getIntent().getParcelableArrayListExtra(EXTRA_RECEIPES);

        // Make request to get points
        Call<List<NavigationStep>> navigationStepsCall = navigationStepService.list(recipes);
        navigationStepsCall.enqueue(new NavigationStepsCallback());

        // Sensors
        configureLocationListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SmartLocation.with(this).location().stop();
    }

    private void configureLocationListener() {

        SmartLocation.with(this).location()
                        .start(new OnLocationUpdatedListener());
    }

    private void loadDataInFragments(List<NavigationStep> navigationSteps) {
        mapFragment.setNavigationSteps(navigationSteps);
        checkListFragment.setNavigationSteps(navigationSteps);
    }

    private class OnLocationUpdatedListener implements io.nlopez.smartlocation.OnLocationUpdatedListener {

        @Override
        public void onLocationUpdated(Location location) {
            Log.i("NAV","Location updated " + location.toString());
        }
    }

    private class NavigationStepsCallback implements retrofit2.Callback<List<NavigationStep>> {
        @Override
        public void onResponse(Call<List<NavigationStep>> call, Response<List<NavigationStep>> response) {
            loadDataInFragments(response.body());
        }

        @Override
        public void onFailure(Call<List<NavigationStep>> call, Throwable t) {
            Log.i("NAV","FAIL"); // TODO: Failure behavior
        }
    }
}
