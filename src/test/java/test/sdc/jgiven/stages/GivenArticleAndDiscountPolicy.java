package test.sdc.jgiven.stages;

import com.google.common.collect.Range;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import test.sdc.Catalogue;
import test.sdc.DiscountPolicy;
import test.sdc.discount.Discount;
import test.sdc.model.Article;
import test.sdc.model.Price;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static test.sdc.ArticleMatcher.articleWithPriceIn;

public class GivenArticleAndDiscountPolicy
        extends Stage<GivenArticleAndDiscountPolicy> {

    private final AtomicInteger articleId = new AtomicInteger();

    @ProvidedScenarioState
    @Mock
    private DiscountPolicy discountPolicy;
    @ProvidedScenarioState
    @Mock
    private Catalogue catalogue;

    @ProvidedScenarioState
    private Article inputArticle;

    @BeforeStage
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public GivenArticleAndDiscountPolicy article_worth_$_dollars(final Double price) {
        this.inputArticle = Article.Builder
                .withReference(String.valueOf(articleId.getAndIncrement()))
                .withPrice(Price.fromDollars(price))
                .build();
        Mockito.when(this.catalogue.find(this.inputArticle.getReference()))
                .thenReturn(this.inputArticle);
        return self();
    }

    public GivenArticleAndDiscountPolicy no_discount_for_articles_with_worth_in(final Range<Double> range) {
        Mockito.when(this.discountPolicy.getApplicableDiscount(articleWithPriceIn(range)))
                .thenReturn(Optional.empty());
        return self();
    }

    public GivenArticleAndDiscountPolicy $_discount_for_articles_with_worth_in(final Discount discount, final Range<Double> range) {
        Mockito.when(this.discountPolicy.getApplicableDiscount(articleWithPriceIn(range)))
                .thenReturn(Optional.of(discount));
        return self();
    }

}