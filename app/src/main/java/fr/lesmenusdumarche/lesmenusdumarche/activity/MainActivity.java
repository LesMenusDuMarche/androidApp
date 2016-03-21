package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.lesmenusdumarche.lesmenusdumarche.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    static final int PICK_CONTACT_REQUEST = 1;  // The request code

    public void Selection_recettes(View v) {
        Intent intent = new Intent(this, Selection.class);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }
}