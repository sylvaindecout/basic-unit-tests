package test.sdc;

import com.google.common.collect.Range;
import org.mockito.ArgumentMatcher;
import test.sdc.model.Article;

import static org.mockito.ArgumentMatchers.argThat;

/**
 * Matcher used to add articles based on their price range as arguments in mockito.
 */
public final class ArticleMatcher implements ArgumentMatcher<Article> {

    private final Range<Double> range;

    private ArticleMatcher(final Range<Double> range) {
        this.range = range;
    }

    /**
     * Initialize custom argument matcher for an catalogue based on a price range.
     *
     * @param range price range, with prices in dollars
     * @return catalogue argument matcher
     */
    public static Article articleWithPriceIn(final Range<Double> range) {
        return argThat(new ArticleMatcher(range));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matches(final Article article) {
        return article != null && this.range.contains(article.getPrice().asDollars());
    }

}