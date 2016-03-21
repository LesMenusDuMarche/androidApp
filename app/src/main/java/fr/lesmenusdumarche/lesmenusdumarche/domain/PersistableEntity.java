package fr.lesmenusdumarche.lesmenusdumarche.domain;

import com.orm.SugarRecord;

/**
 * Created by maxime on 17/03/2016.
 *
 * All the entities that should be persisted inherits from this class
 */
public abstract class PersistableEntity extends SugarRecord {
    final public static String REST_URL = null;
}
