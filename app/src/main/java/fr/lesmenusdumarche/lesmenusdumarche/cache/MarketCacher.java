package fr.lesmenusdumarche.lesmenusdumarche.cache;

import fr.lesmenusdumarche.lesmenusdumarche.domain.Market;
import fr.lesmenusdumarche.lesmenusdumarche.restservice.RestManager;

/**
 * Created by maxime on 17/03/2016.
 *
 * Cacher for Market entities
 */
public class MarketCacher extends EntityCacher<Market> {

    public MarketCacher() {
        service = RestManager.getMarketService();
    }
}
