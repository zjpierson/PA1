import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.*;

public class DataChart extends JFreeChart 
{
    public String Name;
    private JFreeChart _DataChart;
    
    public DataChart(String title, String categoryAxisLable, String valueAxisLable, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
    {
        _DataChart = ChartFactory.createLineChart(title, categoryAxisLable, valueAxisLable, createDataset(), orientation, legend, tooltips, urls);
    }

    private DefaultCategoryDataset createDataset( )
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 15 , "schools" , "1970" );
        dataset.addValue( 30 , "schools" , "1980" );
        dataset.addValue( 60 , "schools" ,  "1990" );
        dataset.addValue( 120 , "schools" , "2000" );
        dataset.addValue( 240 , "schools" , "2010" );
        dataset.addValue( 300 , "schools" , "2014" );
        return dataset;
    }
}
