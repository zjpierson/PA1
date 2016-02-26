import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.*;
//import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.*;

public class DataChart extends JFreeChart
{
    public DataChart(String title, String categoryAxisLable, String valueAxisLable, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
    {
        super();

//        this = ChartFactory.createLineChart(title, categoryAxisLable, valueAxisLable, createDataset(), orientation, legend, tooltips, urls);
    }

    public DataChart(String type)
    {
        super();
    }
}
