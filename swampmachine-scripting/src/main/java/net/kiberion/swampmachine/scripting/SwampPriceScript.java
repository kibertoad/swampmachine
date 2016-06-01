package net.kiberion.swampmachine.scripting;

import lombok.Getter;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesDelta;
import net.kiberion.swampmachine.entities.common.impl.resources.ResourcesStorage;

public class SwampPriceScript{

    @Getter
    private final SwampScript script;
    
    private static final String AVAILABLE_VAR = "availableResources";
    private static final String PRICE_VAR = "calculatedPrice";

    public SwampPriceScript(SwampScript script) {
        this.script = script;
    }
    
    /**
     * @param binding
     * @param availableResources - used for comparison agains calculatedPriceTarget
     * @param calculatedPriceTarget - gets modified by script
     * @return
     */
    public boolean evaluateCanAfford (SwampBinding binding, ResourcesStorage availableResources, ResourcesDelta calculatedPriceTarget) {
        binding.setVariable(PRICE_VAR, calculatedPriceTarget);
        binding.setVariable(AVAILABLE_VAR, calculatedPriceTarget);
        getScript().invoke(binding);
        
        return availableResources.canAfford(calculatedPriceTarget);
    }

}
