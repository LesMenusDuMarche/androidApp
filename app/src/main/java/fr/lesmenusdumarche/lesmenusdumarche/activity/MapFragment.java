package fr.lesmenusdumarche.lesmenusdumarche.activity;

/**
 * Created by tremo on 21/03/16.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.lesmenusdumarche.lesmenusdumarche.R;

public class MapFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}