package com.sxd.swapping.bigDecimal;

import com.sxd.swapping.SwappingApplicationTests;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalTest extends SwappingApplicationTests {

      static Long BASE_TENTHOUSAND  = 1000L;
    @Test
    public void discount(){
        long l = BigDecimal.valueOf(3800L * 1000L).divide(BigDecimal.valueOf(980), 0, BigDecimal.ROUND_HALF_UP).longValue();
        System.out.println(l);

    }

}
