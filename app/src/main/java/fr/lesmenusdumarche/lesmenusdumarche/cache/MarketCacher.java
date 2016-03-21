package fr.lesmenusdumarche.lesmenusdumarche.cache;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Market;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.MarketService;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;

/**
 * Created by maxime on 17/03/2016.
 *
 * Cacher for Market entities
 */
public class MarketCacher extends EntityCacher<MarketService, Market> {

    public MarketCacher() {
        restEntities = RestManager.getMarketService().list();
    }
}
