package test.sdc;

import test.sdc.discount.Discount;
import test.sdc.model.Article;
import test.sdc.model.Price;

import java.util.Optional;

/**
 * Service that decides the price of an article by taking into account information from the catalogue as well as possible discounts.
 */
public final class PricingService {

    private final Catalogue catalogue;
    private final DiscountPolicy discountPolicy;

    /**
     * Constructor.
     *
     * @param catalogue      article catalogue
     * @param discountPolicy discount policy
     */
    public PricingService(final Catalogue catalogue, final DiscountPolicy discountPolicy) {
        super();
        this.catalogue = catalogue;
        this.discountPolicy = discountPolicy;
    }

    /**
     * Compute price of article with input reference.
     *
     * @param articleReference reference of the article in the catalogue
     * @return price of the article including possible discounts
     */
    public Price computePrice(final String articleReference) {
        final Article article = this.catalogue.find(articleReference);
        final Optional<Discount> discount = this.discountPolicy.getApplicableDiscount(article);
        final Price initialPrice = article.getPrice();
        return discount.isPresent()
                ? discount.get().apply(initialPrice)
                : initialPrice;
    }

}