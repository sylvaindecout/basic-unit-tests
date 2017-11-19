package test.sdc.discount;

import test.sdc.model.Price;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Discount with a relative value (eq. percentage).
 */
public final class RelativeDiscount
        implements Discount {

    private final Double value;

    /**
     * Private constructor.
     *
     * @param value value
     */
    private RelativeDiscount(final Double value) {
        super();
        this.value = value;
    }

    /**
     * Initialize new instance from input value.
     *
     * @param value discount percentage
     * @return discount
     */
    public static RelativeDiscount of(final Double value) {
        checkArgument(value > 0, "Discount (%s) cannot be negative", value);
        checkArgument(value <= 1, "Discount (%s) cannot be higher than 100%", value);
        return new RelativeDiscount(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Price apply(final Price initialPrice) {
        return Price.fromDollars(initialPrice.asDollars() * (1 - this.value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("-%s%%", this.value * 100);
    }

}