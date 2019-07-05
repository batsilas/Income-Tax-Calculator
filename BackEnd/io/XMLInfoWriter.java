package incometaxcalculator.data.io;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public class XMLInfoWriter extends InfoWriter {

  protected final String getFileExtension() {
    return "_INFO.xml";
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

  protected final String getStatusBeginTag() {
    return "<Status> ";
  }

  protected final String getStatusEndTag() {
    return " </Status>";
  }

  protected final String geIncomeBeginTag() {
    return "<Income> ";
  }

  protected final String getIncomeEndTag() {
    return " </Income>";
  }

  protected final String getReceiptsTag() {
    return "<Receipts>";
  }

  protected final void generateTaxpayerReceipts(final int taxRegistrationNumber, final PrintWriter outputStream) {

    HashMap<Integer, Receipt> receiptsHashMap = TaxpayerManager
        .getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println("<ReceiptID> " + getReceiptId(receipt) + "</ReceiptID>");
      outputStream.println("<Date> " + getReceiptIssueDate(receipt) + " </Date>");
      outputStream.println("<Kind> " + getReceiptKind(receipt) + " </Kind>");
      outputStream.println("<Amount> " + receipt.getAmount() + " </Amount>");
      outputStream.println("<Company> " + getCompanyName(receipt) + " </Company>");
      outputStream.println("<Country> " + getCompanyCountry(receipt) + "</Country>");
      outputStream.println("<City> " + getCompanyCity(receipt) + " </City>");
      outputStream.println("<Street> " + getCompanyStreet(receipt) + " </Street>");
      outputStream.println("<Number> " + getCompanyNumber(receipt) + " </Number>");
      outputStream.println();
    }
  }

}
