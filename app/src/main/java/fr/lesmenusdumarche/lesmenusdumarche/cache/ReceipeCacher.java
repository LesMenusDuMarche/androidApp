package fr.lesmenusdumarche.lesmenusdumarche.cache;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Market;
import fr.lesmenusdumarche.lesmenusdumarche.domain.Receipe;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;

/**
 * Created by maxime on 17/03/2016.
 *
 * Cacher for Receipe entities
 */
public class ReceipeCacher extends EntityCacher<Receipe> {

    public ReceipeCacher() {
        service = RestManager.getReceipeService();
    }
}
