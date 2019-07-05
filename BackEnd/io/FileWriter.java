package incometaxcalculator.data.io;

import java.io.IOException;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class FileWriter {

  public abstract void generateFile(int taxRegistrationNumber) throws IOException;

  public final String getTaxpayerIncome(final int taxRegistrationNumber) {
    return TaxpayerManager.getTaxpayerIncome(taxRegistrationNumber);
  }

  public final String getTaxpayerStatus(final int taxRegistrationNumber) {
    return TaxpayerManager.getTaxpayerStatus(taxRegistrationNumber);
  }

  public final double getTaxpayerVariationTaxOnReceipts(final int taxRegistrationNumber) {
    return TaxpayerManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber);
  }

  public final int getReceiptId(final Receipt receipt) {
    return receipt.getId();
  }

  public final String getReceiptIssueDate(final Receipt receipt) {
    return receipt.getIssueDate();
  }

  public final String getReceiptKind(final Receipt receipt) {
    return receipt.getKind();
  }

  public final float getReceiptAmount(final Receipt receipt) {
    return receipt.getAmount();
  }

  public final String getCompanyName(final Receipt receipt) {
    return receipt.getCompany().getName();
  }

  public final String getCompanyCountry(final Receipt receipt) {
    return receipt.getCompany().getCountry();
  }

  public final String getCompanyCity(final Receipt receipt) {
    return receipt.getCompany().getCity();
  }

  public final String getCompanyStreet(final Receipt receipt) {
    return receipt.getCompany().getStreet();
  }

  public final int getCompanyNumber(final Receipt receipt) {
    return receipt.getCompany().getNumber();
  }

}