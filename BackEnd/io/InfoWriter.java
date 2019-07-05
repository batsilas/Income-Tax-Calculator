package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class InfoWriter extends FileWriter {

  public InfoWriter() {
    super();
  }

  public final void generateFile(final int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + getFileExtension()));
    outputStream.println(getNameBeginTag()
        + new TaxpayerManager().getTaxpayerName(taxRegistrationNumber) + getNameEndTag());
    outputStream.println(getAFMBeginTag() + taxRegistrationNumber + getAFMEndTag());
    outputStream.println(
        getStatusBeginTag() + getTaxpayerStatus(taxRegistrationNumber) + getStatusEndTag());
    outputStream
        .println(geIncomeBeginTag() + getTaxpayerIncome(taxRegistrationNumber) + getIncomeEndTag());
    outputStream.println();
    outputStream.println(getReceiptsTag());
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }

  protected abstract String getFileExtension();

  protected abstract String getNameBeginTag();

  protected abstract String getNameEndTag();

  protected abstract String getAFMBeginTag();

  protected abstract String getAFMEndTag();

  protected abstract String getStatusBeginTag();

  protected abstract String getStatusEndTag();

  protected abstract String geIncomeBeginTag();

  protected abstract String getIncomeEndTag();

  protected abstract String getReceiptsTag();

  protected abstract void generateTaxpayerReceipts(int taxRegistrationNumber,
      PrintWriter outputStream);

}
