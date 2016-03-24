package fr.lesmenusdumarche.lesmenusdumarche.cache;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Recipe;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;

/**
 * Created by maxime on 17/03/2016.
 *
 * Cacher for Receipe entities
 */
public class RecipeCacher extends EntityCacher<Recipe> {

    public RecipeCacher() {
        restEntities = RestManager.getRecipeService().list();
    }
}
