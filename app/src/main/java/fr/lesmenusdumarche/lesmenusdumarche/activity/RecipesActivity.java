package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.cache.RecipeCacher;
import fr.lesmenusdumarche.lesmenusdumarche.domain.CheckedReceipe;

public class RecipesActivity extends AppCompatActivity {
    private ListView maListViewPerso;
    private SimpleAdapter mListAdapter;
    private List<HashMap<String, String>> listItem;

    private List<String> checkedReceipeNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipes);

        // Load receipes from REST to DB
        RecipeCacher recipeCacher = new RecipeCacher();
        recipeCacher.cacheFromRest();

        // Initialize listView
        initList();

        //initialisation des pour les recettes cochées
        this.checkedReceipeNames = new ArrayList<>();

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        mListAdapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.list_recette_item,
                new String[] {"recette_nom", "recette_description"}, new int[] {R.id.recette_nom, R.id.recette_description});

        //On attribue à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mListAdapter);

    }

    private void initList() {
        //Récupération de la listview créée dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.list_recette);

        //TODO modifier quand base faite
        //Création de la ArrayList qui nous permettra de remplire la listView
        listItem = new ArrayList<HashMap<String, String>>();

        //temporaire pour les tests
        HashMap<String, String> map;
        map = new HashMap<String, String>();
        map.put("recette_nom", "Recette");
        map.put("recette_description", "Descripion");
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);

        map = new HashMap<String, String>();
        map.put("recette_nom", "Recette2");
        map.put("recette_description", "Descripion2");
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);

        //appeler load() pour charger en base
        //load();
        //TODO fin
    }

    //Recharge les recettes TODO quand la base sera faite
    private void load() {

        //TODO charger les éléments depuis la base

    }

    public void click_on_checkbox(View v){
        AppCompatCheckBox checkBox = (AppCompatCheckBox)v;
        if(checkBox.isChecked()){
            checkedReceipeNames.add(((AppCompatCheckBox) v).getText().toString());
        }
    }

    public void valider_choix_recettes(View v) {
        Intent intent = new Intent(this, MainActivity.class);

        CheckedReceipe checkedReceipe = new CheckedReceipe(this.checkedReceipeNames);

        intent.putExtra("checkedReceipe",checkedReceipe);

        setResult(RESULT_OK, intent);
        finish();
    }
}
