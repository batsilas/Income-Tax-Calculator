package incometaxcalculator.data.io;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class TXTInfoWriter extends InfoWriter {

  protected final String getFileExtension() {
    return "_INFO.txt";
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

  protected final String getStatusBeginTag() {
    return "Status: ";
  }

  protected final String getStatusEndTag() {
    return "";
  }

  protected final String geIncomeBeginTag() {
    return "Income: ";
  }

  protected final String getIncomeEndTag() {
    return "";
  }

  protected final String getReceiptsTag() {
    return "Receipts:";
  }

  protected final void generateTaxpayerReceipts(final int taxRegistrationNumber, final PrintWriter outputStream) {
    HashMap<Integer, Receipt> receiptsHashMap = TaxpayerManager
        .getReceiptHashMap(taxRegistrationNumber); // Apo pou na pairnw to getReceiptHashMap
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println("Receipt ID: " + getReceiptId(receipt));
      outputStream.println("Date: " + getReceiptIssueDate(receipt));
      outputStream.println("Kind: " + getReceiptKind(receipt));
      outputStream.println("Amount: " + getReceiptAmount(receipt));
      outputStream.println("Company: " + getCompanyName(receipt));
      outputStream.println("Country: " + getCompanyCountry(receipt));
      outputStream.println("City: " + getCompanyCity(receipt));
      outputStream.println("Street: " + getCompanyStreet(receipt));
      outputStream.println("Number: " + getCompanyNumber(receipt));
      outputStream.println();
    }
  }

}