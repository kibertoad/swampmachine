package net.kiberion.swampmachine.utils.charting;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;

public class SwampPieChart {

    public PieChart getChart() {

        // Create Chart
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Pie Chart with 4 Slices").build();

        // Customize Chart
        chart.getStyler().setCircular(false);

        // Series
        chart.addSeries("Pennies", 100);
        chart.addSeries("Nickels", 100);
        chart.addSeries("Dimes", 100);
        chart.addSeries("Quarters", 100);

        return chart;
      }
    
    
    public static void saveBitmap(Chart chart, OutputStream targetStream, BitmapFormat bitmapFormat)
            throws IOException {
        BufferedImage bufferedImage = BitmapEncoder.getBufferedImage(chart);
        ImageIO.write(bufferedImage, bitmapFormat.toString().toLowerCase(), targetStream);
    }  
    
}
