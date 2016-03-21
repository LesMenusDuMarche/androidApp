package fr.lesmenusdumarche.lesmenusdumarche.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

/**
 * Created by maxime on 17/03/2016.
 *
 * Represents a seller
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Getter
    @Setter
    Long id;

    @Getter
    @Setter
    String name;
}
