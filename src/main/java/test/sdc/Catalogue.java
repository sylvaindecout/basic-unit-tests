package test.sdc;

import test.sdc.model.Article;

/**
 * Catalogue that lists all available articles.
 */
public interface Catalogue {

    /**
     * Find article from reference.
     *
     * @param reference article reference
     * @return article
     */
    Article find(final String reference);

}