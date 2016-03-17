package fr.lesmenusdumarche.lesmenusdumarche.domain;

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
public class Seller {

    @Getter
    @Setter
    Double id;

    @Getter
    @Setter
    String name;
}
