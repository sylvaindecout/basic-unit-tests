package test.sdc.jgiven.stages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import test.sdc.Catalogue;
import test.sdc.DiscountPolicy;
import test.sdc.PricingService;
import test.sdc.model.Article;
import test.sdc.model.Price;

public class WhenServiceComputesPrice
        extends Stage<WhenServiceComputesPrice> {

    @ExpectedScenarioState
    private DiscountPolicy discountPolicy; //?
    @ExpectedScenarioState
    private Catalogue catalogue; //?
    @ExpectedScenarioState
    private Article inputArticle;
    @ProvidedScenarioState
    private Price actualPrice;

    @InjectMocks
    private PricingService service;

    @BeforeStage
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public WhenServiceComputesPrice service_computes_price() {
        this.actualPrice = this.service.computePrice(inputArticle.getReference());
        return self();
    }

}