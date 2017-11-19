package test.sdc.jgiven.stages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import test.sdc.Catalogue;
import test.sdc.DiscountPolicy;
import test.sdc.model.Article;
import test.sdc.model.Price;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ThenPriceIncludesDiscount
        extends Stage<ThenPriceIncludesDiscount> {

    @ExpectedScenarioState
    private DiscountPolicy discountPolicy;
    @ExpectedScenarioState
    private Catalogue catalogue;
    @ExpectedScenarioState
    private Article inputArticle;
    @ExpectedScenarioState
    private Price actualPrice;

    public ThenPriceIncludesDiscount price_with_discount_is_$_dollars(final Double expectedPriceAsDollars) {
        final Price expectedPrice = Price.fromDollars(expectedPriceAsDollars);
        assertThat(this.actualPrice).isEqualTo(expectedPrice);
        return self();
    }

    public ThenPriceIncludesDiscount catalogue_service_is_called_once() {
        verify(this.catalogue, times(1))
                .find(inputArticle.getReference());
        return self();
    }

    public ThenPriceIncludesDiscount discount_policy_service_is_called_once() {
        verify(this.discountPolicy, times(1))
                .getApplicableDiscount(inputArticle);
        return self();
    }

}