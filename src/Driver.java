
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;


public class Driver {

    static SimpleDirectedGraph<Integer, DefaultEdge> collatzGraph = new SimpleDirectedGraph<>(DefaultEdge.class);
    static XYSeriesCollection dataset = new XYSeriesCollection();
    static XYSeries series1 = new XYSeries("Collatz");

    static int step = 1;

    public static void main(String[] args) throws IOException {


        int value = 3;

        recursiveThreePlusOne(value);


        dataset.addSeries(series1);

        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                "Collatz", // Chart title
                "Step", // X-Axis Label
                "Value", // Y-Axis Label
                dataset // Dataset for the Chart
        );

        XYPlot plot = (XYPlot) scatterPlot.getPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShape(0, new Ellipse2D.Double(-1, -1, 2, 2));
        renderer.setSeriesStroke(0, new BasicStroke(0.7f));

        renderer.setSeriesLinesVisible(0, true);
        plot.setRenderer(renderer);

        String fileName = "Collatz Conjecture Graph for Value " + value + ".png";

        ChartUtils.saveChartAsPNG(new File(fileName), scatterPlot, 600, 400);

    }


    public static void recursiveThreePlusOne(int n) {
        series1.add(step, n);
        step++;

        if (n == 1) {
            return;
        }
        else if (n%2 == 0) {
            recursiveThreePlusOne(n / 2);
        }
        else {
            recursiveThreePlusOne((3 * n) + 1);
        }
    }

}
