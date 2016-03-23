package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import fr.lesmenusdumarche.lesmenusdumarche.cache.RecipeCacher;
import fr.lesmenusdumarche.lesmenusdumarche.command.ICommand;
import fr.lesmenusdumarche.lesmenusdumarche.domain.IngredientInRecipe;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

/**
 * Activity of recipes selection list
 */
public class RecipesActivity extends AppCompatActivity {

    /**
     * Validation button
     */
    private Button validateRecipesButton;

    /**
     * Recipes ListView
     */
    private ListView recipesListView;

    /**
     * Recipes ListView Adapter
     */
    private RecipeListViewAdapter recipeListViewAdapter;

    /**
     * Embedded recipes
     */
    private List<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipes);

        // Cache recipes from REST
        cacheRecipesFromRest();

        // Recipes loading
        recipes = getRecipes();

        // UI Components
        recipesListView = (ListView) findViewById(R.id.recipes_list_view);
        validateRecipesButton = (Button) findViewById(R.id.validate_recipes);

        // Set listener on validate button
        validateRecipesButtonOnClickListener();

        // Setting an adapter on our listview
        recipeListViewAdapter = new RecipeListViewAdapter();
        recipesListView.setAdapter(recipeListViewAdapter);
        loadDataInListView(getRecipes());


    }

    private void cacheRecipesFromRest() {
        RecipeCacher cacher = new RecipeCacher();
        cacher.setOnCachedCommand(new OnRecipesCachedCommand(this));
        cacher.cacheFromRest();
    }

    /**
     * Defines the behavior after click on validate button
     */
    private void validateRecipesButtonOnClickListener(){
        validateRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
                ArrayList<Recipe> recipes = recipeListViewAdapter.getSelectedRecipes();
                for (Recipe recipe : recipes) {
                    text += recipe.getTitle() + "\n";
                }
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(RecipesActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("recipes", recipes);
                startActivity(intent);
            }
        });
    }

    /**
     * Loads all available recipes
     * @return The available recipes
     */
    private List<Recipe> getRecipes(){
        // Load recipes from database
        return Recipe.listAll(Recipe.class);

        /*List<Recipe> recipes = new ArrayList<Recipe>();

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

        return recipes;*/
    }

    public void loadDataInListView(List<Recipe> recipes) {
        recipeListViewAdapter.setRecipes(recipes);
        recipeListViewAdapter.notifyDataSetChanged();
    }


    /**
     * ListViewAdapter to display Recipes objects on a ListView
     */
    private class RecipeListViewAdapter extends BaseAdapter{

        /**
         * List of decoratedRecipes, as we manipulate DecoratedRecipe and not Recipe
         */
        private List<DecoratedRecipe> decoratedRecipes = new ArrayList<DecoratedRecipe>();


        /**
         * Sets recipes on adapter
         * @param recipes Recipes do display on ListView
         */
        public void setRecipes(List<Recipe> recipes){
            decoratedRecipes.clear();
            for(Recipe recipe : recipes){
                decoratedRecipes.add(new DecoratedRecipe(recipe));
            }
        }

        /**
         * Returns the selected recipes
         * @return The selected recipes (as I said above, no surprise.)
         */
        public ArrayList<Recipe> getSelectedRecipes(){
            ArrayList<Recipe> selectedRecipes = new ArrayList<Recipe>();
            for(DecoratedRecipe dr : decoratedRecipes){
                if(dr.isSelected){
                    selectedRecipes.add(dr.getRecipe());
                }
            }
            return selectedRecipes;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getCount() {
            return recipes.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getItem(int position) {
            return recipes.get(position);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * Decorates Recipe class adding a selected/unselected bool
         */
        private class DecoratedRecipe{

            @Getter @Setter
            private boolean isSelected;

            @Getter
            private Recipe recipe;

            public DecoratedRecipe(Recipe recipe){
                this.recipe = recipe;
                isSelected = false;
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
                                .setSelected(((CheckBox) v).isChecked());
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

    private class OnRecipesCachedCommand implements ICommand {

        private RecipesActivity activity;

        public OnRecipesCachedCommand(RecipesActivity recipesActivity) {
            this.activity = recipesActivity;
        }

        /**
         * This command will be ran after caching have been done
         */
        @Override
        public void execute() {
            activity.loadDataInListView(activity.getRecipes());
        }
    }
}

