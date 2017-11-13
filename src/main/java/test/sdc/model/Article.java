package test.sdc.model;

import static java.util.Objects.requireNonNull;

public final class Article {

    private String reference;
    private Price price;

    /**
     * Private constructor.
     */
    private Article() {
        super();
    }

    public String getReference() {
        return this.reference;
    }

    private void setReference(final String reference) {
        this.reference = reference;
    }

    public Price getPrice() {
        return this.price;
    }

    private void setPrice(final Price price) {
        this.price = price;
    }

    public static final class Builder {

        private final Article instance = new Article();

        public static Builder withReference(final String inputReference) {
            final Builder builder = new Builder();
            builder.instance.setReference(inputReference);
            return builder;
        }

        public Builder withPrice(final Price inputPrice) {
            this.instance.setPrice(inputPrice);
            return this;
        }

        public Article build() {
            requireNonNull(this.instance.price, "Price attribute has not been initialized");
            return this.instance;
        }
    }

}