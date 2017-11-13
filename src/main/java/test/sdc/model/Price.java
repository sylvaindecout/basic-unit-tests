package test.sdc.model;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public final class Price {

    private final Double value;

    private Price(final Double value) {
        super();
        this.value = value;
    }

    public static Price fromDollars(final Double inputValue) {
        //TODO: check number of decimals, etc.
        checkArgument(inputValue >= 0, "A price cannot be negative");
        return new Price(inputValue);
    }

    public Double asDollars() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object other) {
        return other instanceof Price
                && Objects.equals(this.value, ((Price) other).value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%1.2f$", this.value);
    }

}