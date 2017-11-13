package test.sdc;

import test.sdc.discount.Discount;
import test.sdc.model.Article;

import java.util.Optional;

/**
 * Policy that defines how discounts are dispatched.
 */
public interface DiscountPolicy {

    /**
     * Get discount that is applicable to input article.
     *
     * @param article article
     * @return discount
     */
    Optional<Discount> getApplicableDiscount(final Article article);

}