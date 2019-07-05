package incometaxcalculator.data.management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManager {

  private static HashMap<Integer, Taxpayer> taxpayerHashMap = new HashMap<Integer, Taxpayer>(0);
  private static HashMap<Integer, Integer> receiptOwnerTRN = new HashMap<Integer, Integer>(0);

  public final void createTaxpayer(final String fullname, final int taxRegistrationNumber, final String status,
      final float income) throws WrongTaxpayerStatusException {
    try {
      TaxPayerFactory.createTaxpayerFactory(fullname, taxRegistrationNumber, status, income,
          taxpayerHashMap);
    } catch (WrongTaxpayerStatusException e) {
      e.printStackTrace();
    }
  }

  public final void createReceipt(final int receiptId, final String issueDate, final float amount, final String kind,
      final String companyName, final String country, final String city, final String street, final int number,
      final int taxRegistrationNumber) throws WrongReceiptKindException, WrongReceiptDateException {

    Receipt receipt = new Receipt(receiptId, issueDate, amount, kind,
        new Company(companyName, country, city, street, number));
    taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
    receiptOwnerTRN.put(receiptId, taxRegistrationNumber);
  }

  public final void removeTaxpayer(final int taxRegistrationNumber) {
    Taxpayer taxpayer = taxpayerHashMap.get(taxRegistrationNumber);
    taxpayerHashMap.remove(taxRegistrationNumber);
    HashMap<Integer, Receipt> receiptsHashMap = taxpayer.getReceiptHashMap();
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      receiptOwnerTRN.remove(receipt.getId());
    }
  }

  public final void addReceipt(final int receiptId, final String issueDate, final float amount, final String kind,
      final String companyName, final String country, final String city, final String street, final int number,
      final int taxRegistrationNumber) throws IOException, WrongReceiptKindException,
      WrongReceiptDateException, ReceiptAlreadyExistsException {

    if (containsReceipt(receiptId)) {
      throw new ReceiptAlreadyExistsException();
    }
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street, number,
        taxRegistrationNumber);
    updateFiles(taxRegistrationNumber);
  }

  public final void removeReceipt(final int receiptId) throws IOException, WrongReceiptKindException {
    taxpayerHashMap.get(receiptOwnerTRN.get(receiptId)).removeReceipt(receiptId);
    updateFiles(receiptOwnerTRN.get(receiptId));
    receiptOwnerTRN.remove(receiptId);
  }

  private void updateFiles(final int taxRegistrationNumber) {
    try {
      WriterFactory.updateFilesFactoryMethod(taxRegistrationNumber);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public final void saveLogFile(final int taxRegistrationNumber, final String fileFormat)
      throws IOException, WrongFileFormatException {
    try {
      WriterFactory.saveLogFileFactory(taxRegistrationNumber, fileFormat);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (WrongFileFormatException e) {
      e.printStackTrace();
    }
  }

  public final boolean containsTaxpayer(final int taxRegistrationNumber) {
    if (taxpayerHashMap.containsKey(taxRegistrationNumber)) {
      return true;
    }
    return false;
  }

  public final boolean containsTaxpayer() {
    if (taxpayerHashMap.isEmpty()) {
      return false;
    }
    return true;
  }

  public final boolean containsReceipt(final int id) {
    if (receiptOwnerTRN.containsKey(id)) {
      return true;
    }
    return false;

  }

  public final Taxpayer getTaxpayer(final int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber);
  }

  public final void loadTaxpayer(final String fileName)
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    try {
      ReaderFactory.loadTaxpayerFactory(fileName);
    } catch (WrongFileEndingException e) {
      e.printStackTrace();
    }
  }

  public final String getTaxpayerName(final int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getFullname();
  }

  public static String getTaxpayerStatus(final int taxRegistrationNumber) {
    if (taxpayerHashMap.get(taxRegistrationNumber) instanceof MarriedFilingJointlyTaxpayer) {
      return "Married Filing Jointly";
    } else if (taxpayerHashMap
        .get(taxRegistrationNumber) instanceof MarriedFilingSeparatelyTaxpayer) {
      return "Married Filing Separately";
    } else if (taxpayerHashMap.get(taxRegistrationNumber) instanceof SingleTaxpayer) {
      return "Single";
    } else {
      return "Head of Household";
    }
  }

  public static String getTaxpayerIncome(final int taxRegistrationNumber) {
    return "" + taxpayerHashMap.get(taxRegistrationNumber).getIncome();
  }

  public static double getTaxpayerVariationTaxOnReceipts(final int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getVariationTaxOnReceipts();
  }

  public final int getTaxpayerTotalReceiptsGathered(final int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalReceiptsGathered();
  }

  public final float getTaxpayerAmountOfReceiptKind(final int taxRegistrationNumber, final short kind) {
    return taxpayerHashMap.get(taxRegistrationNumber).getAmountOfReceiptKind(kind);
  }

  public final double getTaxpayerTotalTax(final int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalTax();
  }

  public final double getTaxpayerBasicTax(final int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getBasicTax();
  }

  public static HashMap<Integer, Receipt> getReceiptHashMap(final int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getReceiptHashMap();
  }

}