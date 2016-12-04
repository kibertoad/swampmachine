package net.kiberion.swampmachine.utils.charting;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class PieChartTest {

    @Test
    public void testPieChart() throws Exception {
        SwampPieChart chart = new SwampPieChart();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        chart.saveBitmap(chart.getChart(), bos, BitmapFormat.PNG);

        InputStream bis = new ByteArrayInputStream(bos.toByteArray());
    }

}
