package test.sdc.discount;

import test.sdc.model.Price;

/**
 * Discount.
 */
public interface Discount {

    /**
     * Apply discount to initial price.
     *
     * @param initialPrice initial price
     * @return price including discount
     */
    Price apply(final Price initialPrice);

}