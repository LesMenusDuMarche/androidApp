package fr.lesmenusdumarche.lesmenusdumarche.domain;

import java.text.SimpleDateFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

/**
 * Created by maxime on 17/03/2016.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    @Getter
    @Setter
    Double id;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String dayOfWeek;

    @Getter
    @Setter
    SimpleDateFormat hourStart;

    @Getter
    @Setter
    SimpleDateFormat hourEnd;

    @Getter
    @Setter
    GeoPoint position;

    @Getter
    @Setter
    List<Receipe> receipes;
}
