package incometaxcalculator.data.io;

public class TXTLogWriter extends LogWriter {

  protected final String getFileExtension() {
    return "_LOG.txt";
  }

  protected final String getNameBeginTag() {
    return "Name: ";
  }

  protected final String getNameEndTag() {
    return "";
  }

  protected final String getAFMBeginTag() {
    return "AFM: ";
  }

  protected final String getAFMEndTag() {
    return "";
  }

  protected final String getIncomeBeginTag() {
    return "Income: ";
  }

  protected final String getIncomeEndTag() {
    return "";
  }

  protected final String getBasicTaxBeginTag() {
    return "Basic Tax:";
  }

  protected final String getBasicTaxEndTag() {
    return "";
  }

  protected final String getTaxDecreaseBeginTag() {
    return "Tax Decrease: ";
  }

  protected final String getTaxDecreaseEndTag() {
    return "";
  }

  protected final String getTaxIncreaseBeginTag() {
    return "Tax Increase: ";
  }

  protected final String getTaxIncreaseEndTag() {
    return "";
  }

  protected final String getTotalTaxBeginTag() {
    return "Total Tax: ";
  }

  protected final String getTotalTaxEndTag() {
    return "";
  }

  protected final String getTotalReceiptsGatheredBeginTag() {
    return "TotalReceiptsGatheredx: ";
  }

  protected final String getTotalReceiptsGatheredEndTag() {
    return "";
  }

  protected final String getEntertainmentBeginTag() {
    return "Entertainment: ";
  }

  protected final String getEntertainmentEndTag() {
    return "";
  }

  protected final String getBasicBeginTag() {
    return "Basic: ";
  }

  protected final String getBasicEndTag() {
    return "";
  }

  protected final String getTravelBeginTag() {
    return "Travel: ";
  }

  protected final String getTravelEndTag() {
    return "";
  }

  protected final String getHealthBeginTag() {
    return "Health: ";
  }

  protected final String getHealthEndTag() {
    return "";
  }

  protected final String getOtherBeginTag() {
    return "Other: ";
  }

  protected final String getOtherEndTag() {
    return "";
  }

}
