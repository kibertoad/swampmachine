package net.kiberion.swampmachine.utils.common;

import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopWatch {

    private static final Logger log = LogManager.getLogger();
    
    private long startTime;
    private long endTime;
    
    public void start () {
        startTime = System.currentTimeMillis();
    }
    
    
    public void endAndLog (String marker) {
        endTime = System.currentTimeMillis();
        
        double diff = (endTime-startTime);
        diff = diff / 1000;
        DecimalFormat df = new DecimalFormat("#.000"); 
        log.info(marker+". Time elapsed: "+(df.format(diff))+" secs");
    }
    
}
