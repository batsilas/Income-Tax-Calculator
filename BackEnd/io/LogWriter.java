package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

import incometaxcalculator.data.management.TaxpayerManager;

public abstract class LogWriter extends FileWriter {

  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;

  public LogWriter() {
    super();
  }

  public final void generateFile(final int taxRegistrationNumber) throws IOException {
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + getFileExtension()));
    outputStream.println(getNameBeginTag()
        + new TaxpayerManager().getTaxpayerName(taxRegistrationNumber) + getNameEndTag());
    outputStream.println(getAFMBeginTag() + taxRegistrationNumber + getAFMEndTag());
    outputStream.println(
        getIncomeBeginTag() + getTaxpayerIncome(taxRegistrationNumber) + getIncomeEndTag());
    outputStream.println(getBasicTaxBeginTag()
        + new TaxpayerManager().getTaxpayerBasicTax(taxRegistrationNumber) + getBasicTaxEndTag());
    if (getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream.println(getTaxIncreaseBeginTag()
          + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + getTaxIncreaseEndTag());
    } else {
      outputStream.println(getTaxDecreaseBeginTag()
          + getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + getTaxDecreaseEndTag());
    }
    outputStream.println(getTotalTaxBeginTag()
        + new TaxpayerManager().getTaxpayerTotalTax(taxRegistrationNumber) + getTotalTaxEndTag());
    outputStream.println(getTotalReceiptsGatheredBeginTag()
        + new TaxpayerManager().getTaxpayerTotalReceiptsGathered(taxRegistrationNumber)
        + getTotalReceiptsGatheredEndTag());
    outputStream.println(getEntertainmentBeginTag()
        + new TaxpayerManager().getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT)
        + getEntertainmentEndTag());
    outputStream.println(getBasicBeginTag()
        + new TaxpayerManager().getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC)
        + getTravelEndTag());
    outputStream.println(getTravelBeginTag()
        + new TaxpayerManager().getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL)
        + getTravelEndTag());
    outputStream.println(getHealthBeginTag()
        + new TaxpayerManager().getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH)
        + getHealthEndTag());
    outputStream.println(getOtherBeginTag()
        + new TaxpayerManager().getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER)
        + getOtherEndTag());
    outputStream.close();
  }

  protected abstract String getFileExtension();

  protected abstract String getNameBeginTag();

  protected abstract String getNameEndTag();

  protected abstract String getAFMBeginTag();

  protected abstract String getAFMEndTag();

  protected abstract String getIncomeBeginTag();

  protected abstract String getIncomeEndTag();

  protected abstract String getBasicTaxBeginTag();

  protected abstract String getBasicTaxEndTag();

  protected abstract String getTaxDecreaseBeginTag();

  protected abstract String getTaxDecreaseEndTag();

  protected abstract String getTaxIncreaseBeginTag();

  protected abstract String getTaxIncreaseEndTag();

  protected abstract String getTotalTaxBeginTag();

  protected abstract String getTotalTaxEndTag();

  protected abstract String getTotalReceiptsGatheredBeginTag();

  protected abstract String getTotalReceiptsGatheredEndTag();

  protected abstract String getEntertainmentBeginTag();

  protected abstract String getEntertainmentEndTag();

  protected abstract String getBasicBeginTag();

  protected abstract String getBasicEndTag();

  protected abstract String getTravelBeginTag();

  protected abstract String getTravelEndTag();

  protected abstract String getHealthBeginTag();

  protected abstract String getHealthEndTag();

  protected abstract String getOtherBeginTag();

  protected abstract String getOtherEndTag();
}
