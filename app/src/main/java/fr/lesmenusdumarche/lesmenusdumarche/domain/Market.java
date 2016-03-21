package fr.lesmenusdumarche.lesmenusdumarche.domain;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.text.SimpleDateFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

/**
 * Created by maxime on 17/03/2016.
 *
 * Represents a market
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market extends PersistableEntity {

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    Integer dayOfWeek;

    @Getter
    @Setter
    String hourStart;

    @Getter
    @Setter
    String hourEnd;

    @Getter
    @Setter
    GeoPoint position;
}
