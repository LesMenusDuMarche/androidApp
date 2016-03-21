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
import fr.lesmenusdumarche.lesmenusdumarche.domain.IngredientInRecipe;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;

public class RecipesActivity extends AppCompatActivity {
    private ListView maListViewPerso;
    private SimpleAdapter mListAdapter;
    private List<HashMap<String, String>> listItem;

    private List<String> checkedReceipeNames;

    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipes);

        recipes = initList();

        //initialisation des pour les recettes cochées
        this.checkedReceipeNames = new ArrayList<>();

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        mListAdapter = new SimpleAdapter (
                getBaseContext(),
                listItem,
                R.layout.list_recette_item,
                new String[] {"recette_nom", "recette_description"},
                new int[] {R.id.recette_nom, R.id.recette_description});

        //On attribue à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mListAdapter);

    }

    private void loadReceipes() {
        RecipeCacher recipeCacher = new RecipeCacher();
    }

    private List<Recipe> initList() {

        List<Recipe> recipes = new ArrayList<Recipe>();

        Recipe recipe = new Recipe();
        recipe.setBody("<html/>");
        recipe.setTitle("Porc au Caramel");
        List<IngredientInRecipe> ingredients = new ArrayList<IngredientInRecipe>();
        ingredients.add(new IngredientInRecipe(0L, "Echine de porc", "1.5Kg"));
        ingredients.add(new IngredientInRecipe(1L, "Oignons", "10"));
        recipe.setIngredients(ingredients);
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.setBody("<html/>");
        recipe.setTitle("Crumble aux pommes");
        ingredients = new ArrayList<IngredientInRecipe>();
        ingredients.add(new IngredientInRecipe(0L, "Pommes", "4"));
        ingredients.add(new IngredientInRecipe(1L, "Cassonade", "150g"));
        recipe.setIngredients(ingredients);
        recipes.add(recipe);

        return recipes;
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
        mListAdapter.get
        CheckedReceipe checkedReceipe = new CheckedReceipe(this.checkedReceipeNames);

        intent.putExtra("checkedReceipe",checkedReceipe);

        setResult(RESULT_OK, intent);
        finish();
    }
}
