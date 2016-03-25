package fr.lesmenusdumarche.lesmenusdumarche.fragment;

/**
 * Created by tremo on 21/03/16.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.IngredientInRecipe;
import fr.lesmenusdumarche.lesmenusdumarche.domain.NavigationStep;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

public class CheckListFragment extends Fragment {

    // UI Elements
    View fragment;
    ListView listViewChecklist;

    // Data
    ChecklistListViewAdapter listViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment = inflater.inflate(R.layout.fragment_checklist, container, false);

        // UI Elements
        listViewChecklist = (ListView) fragment.findViewById(R.id.checklist_list_view);

        listViewAdapter = new ChecklistListViewAdapter();
        listViewChecklist.setAdapter(listViewAdapter);

        return fragment;
    }

    /**
     * Loads data in listview
     * @param navigationSteps
     */
    public void setNavigationSteps(List<NavigationStep> navigationSteps) {
        // Extract ingredients from navigationsteps
        List<IngredientInRecipe> ingredients = new ArrayList<>();
        for(NavigationStep ns : navigationSteps) {
            if(ns.getIngredients() != null) {
                ingredients.addAll(ns.getIngredients());
            }
        }
        listViewAdapter.setIngredients(ingredients);
        listViewAdapter.notifyDataSetChanged();
    }

    /**
     * ListViewAdapter to display Recipes objects on a ListView
     */
    private class ChecklistListViewAdapter extends BaseAdapter{

        /**
         * List of decoratedIngredients, as we manipulate DecoratedRecipe and not Recipe
         */
        private List<DecoratedIngrdientInReceipe> decoratedIngredients = new ArrayList<DecoratedIngrdientInReceipe>();


        /**
         * Sets recipes on adapter
         * @param recipes Recipes do display on ListView
         */
        public void setIngredients(List<IngredientInRecipe> ingredients){
            decoratedIngredients.clear();
            for(IngredientInRecipe ingredient : ingredients){
                decoratedIngredients.add(new DecoratedIngrdientInReceipe(ingredient));
            }
        }
        /**
         * Returns the selected recipes
         * @return The selected recipes (as I said above, no surprise.)
         */
        public boolean isComplete(){
            for(DecoratedIngrdientInReceipe dr : decoratedIngredients){
                if(!dr.isSelected){
                    return false;
                }
            }
            return true;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public int getCount() {
            return decoratedIngredients.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object getItem(int position) {
            return decoratedIngredients.get(position);
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
        private class DecoratedIngrdientInReceipe {

            @Getter @Setter
            private boolean isSelected;

            @Getter
            private IngredientInRecipe ingredient;

            public DecoratedIngrdientInReceipe(IngredientInRecipe ingredient){
                this.ingredient = ingredient;
                isSelected = false;
            }
        }

        private class IngredientHolder {
            public CheckBox ingredientCheckbox;
            public TextView ingredientAmount;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;
            IngredientHolder holder = new IngredientHolder();

            if (convertView == null) {

                // On inflate la listView
                LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.listview_checklist_row, null);

                // On récupère la checkbox et on lui assigne un onClickListener pour mettre à jour le booléen correspondant
                final CheckBox recipeCheckbox = (CheckBox) v.findViewById(R.id.ingredien_checkbox);
                final TextView recipeAmount = (TextView) v.findViewById(R.id.ingredientAmountView);
                recipeCheckbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        decoratedIngredients
                                .get(position)
                                .setSelected(((CheckBox) v).isChecked());
                        if(isComplete()){
                            Toast.makeText(getContext(), "Vous avez fini vos courses, bravo !", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                holder.ingredientCheckbox = recipeCheckbox;
                holder.ingredientAmount = recipeAmount;
                v.setTag(holder);
        }
            else
                holder = (IngredientHolder) v.getTag();

            // Affichage du texte
            IngredientInRecipe ingredient = decoratedIngredients.get(position).getIngredient();
            holder.ingredientCheckbox.setText(ingredient.getLabel());
            holder.ingredientAmount.setText(ingredient.getAmount());

            return v;
        }
    }
}