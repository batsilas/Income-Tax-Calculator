package incometaxcalculator.data.io;

public class XMLLogWriter extends LogWriter {

  protected final String getFileExtension() {
    return "_LOG.xml";
  }

  protected final String getNameBeginTag() {
    return "<Name> ";
  }

  protected final String getNameEndTag() {
    return " </Name>";
  }

  protected final String getAFMBeginTag() {
    return "<AFM> ";
  }

  protected final String getAFMEndTag() {
    return " </AFM>";
  }

  protected final String getIncomeBeginTag() {
    return "<Income> ";
  }

  protected final String getIncomeEndTag() {
    return " </Income>";
  }

  protected final String getBasicTaxBeginTag() {
    return "<Basic Tax> ";
  }

  protected final String getBasicTaxEndTag() {
    return " </Basic Tax>";
  }

  protected final String getTaxDecreaseBeginTag() {
    return "<Tax Decrease> ";
  }

  protected final String getTaxDecreaseEndTag() {
    return " </Tax Decrease>";
  }

  protected final String getTaxIncreaseBeginTag() {
    return "<Tax Increase> ";
  }

  protected final String getTaxIncreaseEndTag() {
    return " </Tax Increase>";
  }

  protected final String getTotalTaxBeginTag() {
    return "<Total Tax> ";
  }

  protected final String getTotalTaxEndTag() {
    return " </Total Tax>";
  }

  protected final String getTotalReceiptsGatheredBeginTag() {
    return "<TotalReceiptsGatheredx> ";
  }

  protected final String getTotalReceiptsGatheredEndTag() {
    return " </TotalReceiptsGathered>";
  }

  protected final String getEntertainmentBeginTag() {
    return "<Entertainment> ";
  }

  protected final String getEntertainmentEndTag() {
    return " </Entertainment>";
  }

  protected final String getBasicBeginTag() {
    return "<Basic> ";
  }

  protected final String getBasicEndTag() {
    return " </Basic>";
  }

  protected final String getTravelBeginTag() {
    return "<Travel> ";
  }

  protected final String getTravelEndTag() {
    return " </Travel>";
  }

  protected final String getHealthBeginTag() {
    return "<Health> ";
  }

  protected final String getHealthEndTag() {
    return " </Health>";
  }

  protected final String getOtherBeginTag() {
    return "<Other> ";
  }

  protected final String getOtherEndTag() {
    return " </Other>";
  }

}
