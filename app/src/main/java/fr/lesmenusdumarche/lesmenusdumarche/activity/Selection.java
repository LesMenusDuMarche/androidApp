package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import fr.lesmenusdumarche.lesmenusdumarche.R;

public class Selection extends AppCompatActivity {
    private ListView maListViewPerso;
    private SimpleAdapter mListAdapter;
    private ArrayList<HashMap<String, String>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        initList();

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        mListAdapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.list_recette_item,
                new String[] {"recette_nom", "recette_description"}, new int[] {R.id.recette_nom, R.id.recette_description});

        //On attribue à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mListAdapter);

    }

    private void initList() {
        //Récupération de la listview créée dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.list_recette);

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
    }

    //Recharge les recettes TODO quand la base sera faite, modifier le test
    private void load() {

        //TODO charger les éléments depuis la base
        mListAdapter.notifyDataSetChanged();

    }

    //TODO
    public void valider_choix_recettes(View v){

        for(int i = 0; i < maListViewPerso.getCount(); i++) {
            HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(i);
            map.get("recette_nom");
        }
    }

    //TODO
    public void click_on_checkbox(View v){
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            v = null;
        }
    }
}
