package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.app.FragmentManager;
import android.os.Bundle;


import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import fr.lesmenusdumarche.lesmenusdumarche.fragment.CheckListFragment;
import fr.lesmenusdumarche.lesmenusdumarche.fragment.MapFragment;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

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

    // Sensors

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        // UI Components
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        checkListFragment = (CheckListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_checklist);

        // Get extras (receipes)
        recipes = getIntent().getParcelableArrayListExtra(EXTRA_RECEIPES);

        // Sensors
        // TODO: Get GPS location and pass it to map
    }
}
