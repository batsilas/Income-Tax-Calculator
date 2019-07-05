package incometaxcalculator.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RefineryUtilities;

class ChartDisplay {

  static JFrame createPieChart(final double entertainment, final double basic, final double travel, final double health,
      final double other) {

    JFrame pieChartFrame = new JFrame("Analysis of receipts");
    pieChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pieChartFrame.setLayout(new BorderLayout(0, 5));
    pieChartFrame.add(createPieChartPanel(entertainment, basic, travel, health, other),
        BorderLayout.CENTER);
    pieChartFrame.pack();
    RefineryUtilities.centerFrameOnScreen(pieChartFrame);
    pieChartFrame.setVisible(true);
    return pieChartFrame;
  }

  private static ChartPanel createPieChartPanel(final double entertainment, final double basic, final double travel,
      final double health, final double other) {
    JFreeChart pieChart = ChartFactory.createPieChart(
        "Percentage of the total amount of each kind of receipt.",
        createDefaultPieDataset(entertainment, basic, travel, health, other), true, true, false);
    ChartPanel barChartPanel = new ChartPanel(pieChart);
    barChartPanel.setPreferredSize(new java.awt.Dimension(450, 550));
    return barChartPanel;
  }

  private static DefaultPieDataset createDefaultPieDataset(final double entertainment, final double basic,
      final double travel, final double health, final double other) {

    DefaultPieDataset pieChartDataset = new DefaultPieDataset();
    pieChartDataset.setValue("Entertainment", entertainment);
    pieChartDataset.setValue("Basic", basic);
    pieChartDataset.setValue("Travel", travel);
    pieChartDataset.setValue("Health", health);
    pieChartDataset.setValue("Other", other);
    return pieChartDataset;
  }

  static JFrame createBarChart(final double basicTax, final double taxVariation, final double totalTax) {
    JFrame barChartFrame = new JFrame("Bar Chart");
    barChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    barChartFrame.setLayout(new BorderLayout(0, 5));
    barChartFrame.add(createBarChartPanel(basicTax, taxVariation, totalTax), BorderLayout.CENTER);
    barChartFrame.pack();
    RefineryUtilities.centerFrameOnScreen(barChartFrame);
    barChartFrame.setVisible(true);
    return barChartFrame;
  }

  private static ChartPanel createBarChartPanel(final double basicTax, final double taxVariation,
      final double totalTax) {
    JFreeChart barChart = ChartFactory.createBarChart("", "", "Tax analysis in $",
        createDefaultCategoryDataset(basicTax, taxVariation, totalTax), PlotOrientation.VERTICAL,
        true, true, false);
    ChartPanel barChartPanel = new ChartPanel(barChart);
    barChartPanel.setPreferredSize(new java.awt.Dimension(450, 550));
    return barChartPanel;
  }

  private static DefaultCategoryDataset createDefaultCategoryDataset(final double basicTax,
      final double taxVariation, final double totalTax) {
    DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
    barChartDataset.addValue(basicTax, "Tax", "Basic");
    if (taxVariation > 0) {
      barChartDataset.addValue(taxVariation, "Tax", "Increase");
    } else {
      barChartDataset.addValue(-taxVariation, "Tax", "Decrease");
    }
    barChartDataset.addValue(totalTax, "Tax", "Total");
    return barChartDataset;
  }
}