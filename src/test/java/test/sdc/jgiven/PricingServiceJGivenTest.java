package test.sdc.jgiven;

import com.tngtech.jgiven.junit.ScenarioTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import test.sdc.discount.AbsoluteDiscount;
import test.sdc.discount.RelativeDiscount;
import test.sdc.jgiven.stages.GivenArticleAndDiscountPolicy;
import test.sdc.jgiven.stages.ThenPriceIncludesDiscount;
import test.sdc.jgiven.stages.WhenServiceComputesPrice;
import test.sdc.jgiven.tags.Business;
import test.sdc.jgiven.tags.Communication;
import test.sdc.model.Price;

import static com.google.common.collect.Range.*;

@RunWith(JUnitParamsRunner.class)
public class PricingServiceJGivenTest
        extends ScenarioTest<GivenArticleAndDiscountPolicy,
        WhenServiceComputesPrice,
        ThenPriceIncludesDiscount> {

    private static Object[] price_mapping() {
        return new Object[][]{
                {0., 0.},
                {0.1, 0.1},
                {49., 49.},
                {50., 45.},
                {90., 81.},
                {100., 70.},
                {200., 170.},
        };
    }

    @Test
    @Communication
    public void should_get_info_from_catalogue_and_discount_policy() {
        given().article_worth_$_dollars(45.59)
                .and().no_discount_for_articles_with_worth_in(atMost(50.))
                .and().$_discount_for_articles_with_worth_in(RelativeDiscount.of(0.1), closedOpen(50., 100.))
                .and().$_discount_for_articles_with_worth_in(AbsoluteDiscount.of(Price.fromDollars(30.)), atLeast(100.));
        when().service_computes_price();
        then().catalogue_service_is_called_once()
                .and().discount_policy_service_is_called_once();
    }

    @Test
    @Business
    @Parameters(method = "price_mapping")
    public void should_compute_price_properly(final Double cataloguePrice, final Double expectedPrice) {
        given().article_worth_$_dollars(cataloguePrice)
                .and().no_discount_for_articles_with_worth_in(atMost(50.))
                .and().$_discount_for_articles_with_worth_in(RelativeDiscount.of(0.1), closedOpen(50., 100.))
                .and().$_discount_for_articles_with_worth_in(AbsoluteDiscount.of(Price.fromDollars(30.)), atLeast(100.));
        when().service_computes_price();
        then().price_with_discount_is_$_dollars(expectedPrice);
    }

}