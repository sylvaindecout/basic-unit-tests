package test.sdc.jupiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.sdc.Catalogue;
import test.sdc.DiscountPolicy;
import test.sdc.PricingService;
import test.sdc.discount.AbsoluteDiscount;
import test.sdc.discount.RelativeDiscount;
import test.sdc.model.Article;
import test.sdc.model.Price;

import java.util.Optional;

import static com.google.common.collect.Range.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;
import static test.sdc.ArticleMatcher.articleWithPriceIn;

class PricingServiceJupiterTest {

    @Mock
    private DiscountPolicy discountPolicy;
    @Mock
    private Catalogue catalogue;
    @InjectMocks
    private PricingService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        /* Setup test discount policy. */
        when(this.discountPolicy.getApplicableDiscount(articleWithPriceIn(atMost(50.))))
                .thenReturn(Optional.empty());
        when(this.discountPolicy.getApplicableDiscount(articleWithPriceIn(closedOpen(50., 100.))))
                .thenReturn(Optional.of(RelativeDiscount.of(0.1)));
        when(this.discountPolicy.getApplicableDiscount(articleWithPriceIn(atLeast(100.))))
                .thenReturn(Optional.of(AbsoluteDiscount.of(Price.fromDollars(30.))));
    }

    @Test
    void should_get_info_from_catalogue_and_discount_policy() {
        final Article testArticle = Article.Builder.withReference("123").withPrice(Price.fromDollars(45.59)).build();
        given(this.catalogue.find(testArticle.getReference()))
                .willReturn(testArticle);

        final Price actual = this.service.computePrice(testArticle.getReference());

        then(this.catalogue).should(only()).find(testArticle.getReference());
        then(this.discountPolicy).should(only()).getApplicableDiscount(testArticle);
    }

    @ParameterizedTest(name = "initial price: {0} -> with discount: {1}")
    @CsvSource({"0., 0.",
            "0.1, 0.1",
            "49., 49.",
            "50., 45.",
            "90., 81.",
            "100., 70.",
            "200., 170."})
    void should_compute_price_properly(final Double cataloguePriceAsDollars, final Double expectedAsDollars) {
        final Price cataloguePrice = Price.fromDollars(cataloguePriceAsDollars);
        final Price expected = Price.fromDollars(expectedAsDollars);
        final Article testArticle = Article.Builder.withReference("123").withPrice(cataloguePrice).build();
        given(this.catalogue.find(testArticle.getReference()))
                .willReturn(testArticle);

        final Price actual = this.service.computePrice(testArticle.getReference());

        assertThat(actual).as("catalogue price: %s", cataloguePrice)
                .isEqualTo(expected);
    }

}