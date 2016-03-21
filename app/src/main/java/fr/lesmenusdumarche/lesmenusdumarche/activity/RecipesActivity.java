package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.IngredientInRecipe;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;

public class RecipesActivity extends AppCompatActivity {

    private Button validateRecipesButton;
    private ListView recipesListView;
    private RecipeListViewAdapter recipeListViewAdapter;
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipes);

        // Récupération des recettes
        recipes = getRecipes();
        recipeListViewAdapter = new RecipeListViewAdapter(recipes);

        // Références aux widgets de la vue
        recipesListView = (ListView) findViewById(R.id.recipes_list_view);
        validateRecipesButton = (Button) findViewById(R.id.validate_recipes);

        // Listener sur le bouton de validation
        onValidateListener();

        // Affichage
        recipesListView.setAdapter(recipeListViewAdapter);

    }

    private void onValidateListener(){
        validateRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                ArrayList<Recipe> recipes =  recipeListViewAdapter.getSelectedRecipes();
               for(Recipe recipe : recipes){
                   text += recipe.getTitle() + "\n";
               }
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }

    private List<Recipe> getRecipes(){

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


    private class RecipeListViewAdapter extends BaseAdapter{

        private List<DecoratedRecipe> decoratedRecipes = new ArrayList<DecoratedRecipe>();

        // Constructeur qui instancie la liste de recette décorée
        public RecipeListViewAdapter(List<Recipe> recipes){
            for(Recipe recipe : recipes){
                decoratedRecipes.add(new DecoratedRecipe(recipe));
            }
        }

        public ArrayList<Recipe> getSelectedRecipes(){
            ArrayList<Recipe> selectedRecipes = new ArrayList<Recipe>();
            for(DecoratedRecipe dr : decoratedRecipes){
                if(dr.isSelected){
                    selectedRecipes.add(dr.getRecipe());
                }
            }
            return selectedRecipes;
        }

        @Override
        public int getCount() {
            return recipes.size();
        }

        @Override
        public Object getItem(int position) {
            return recipes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        // Classe contenant une recette et un booléen selectionné/pas sélectionné
        private class DecoratedRecipe{

            private boolean isSelected;
            private Recipe recipe;

            public DecoratedRecipe(Recipe recipe){
                this.recipe = recipe;
                isSelected = false;
            }

            public boolean isSelected() {
                return isSelected;
            }

            public void setIsSelected(boolean isSelected) {
                this.isSelected = isSelected;
            }

            public Recipe getRecipe(){
                return recipe;
            }
        }

        private class RecipeHolder {
            public CheckBox recipeCheckbox;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            RecipeHolder holder = new RecipeHolder();

            if (convertView == null) {

                // On inflate la listView
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.list_recette_item, null);

                // On récupère la checkbox et on lui assigne un onClickListener pour mettre à jour le booléen correspondant
                final CheckBox recipeCheckbox = (CheckBox) v.findViewById(R.id.recipe_checkbox);
                recipeCheckbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        decoratedRecipes
                                .get(position)
                                .setIsSelected(((CheckBox) v).isChecked());
                    }
                });
                holder.recipeCheckbox = recipeCheckbox;
                v.setTag(holder);
            }
            else
                holder = (RecipeHolder) v.getTag();

            // Affichage du texte
            Recipe recipe = decoratedRecipes.get(position).getRecipe();
            holder.recipeCheckbox.setText(recipe.getTitle());

            return v;
        }
    }
}

