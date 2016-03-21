package fr.lesmenusdumarche.lesmenusdumarche.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

/**
 * Created by maxime on 17/03/2016.
 *
 * Represents the position and attributes of a seller on a specific market
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerOnMarket {

    @Getter
    @Setter
    Long id;

}
