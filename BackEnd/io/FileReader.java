package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public abstract class FileReader {

  public final void readFile(final String fileName)
      throws NumberFormatException, IOException, WrongTaxpayerStatusException,
      WrongFileFormatException, WrongReceiptKindException, WrongReceiptDateException {

    BufferedReader inputStream = new BufferedReader(new java.io.FileReader(fileName));
    String fullname = getValueOfField(inputStream.readLine());
    int taxRegistrationNumber = Integer.parseInt(getValueOfField(inputStream.readLine()));
    String status = getValueOfField(inputStream.readLine());
    float income = Float.parseFloat(getValueOfField(inputStream.readLine()));
    createTaxpayer(fullname, taxRegistrationNumber, income, status);
    while (readReceipt(inputStream, taxRegistrationNumber)) {
      ;
    }
  }

  protected final boolean readReceipt(final BufferedReader inputStream,
      final int taxRegistrationNumber) throws WrongFileFormatException, IOException,
      WrongReceiptKindException, WrongReceiptDateException {

    int receiptId;
    if ((receiptId = checkForReceipt(inputStream)) < 0) {
      return false;
    }
    String issueDate = getValueOfField(inputStream.readLine());
    String kind = getValueOfField(inputStream.readLine());
    float amount = Float.parseFloat(getValueOfField(inputStream.readLine()));
    String companyName = getValueOfField(inputStream.readLine());
    String country = getValueOfField(inputStream.readLine());
    String city = getValueOfField(inputStream.readLine());
    String street = getValueOfField(inputStream.readLine());
    int number = Integer.parseInt(getValueOfField(inputStream.readLine()));
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street, number,
        taxRegistrationNumber);
    return true;
  }

  protected final void createTaxpayer(final String fullname, final int taxRegistrationNumber,
      final float income, final String status) throws WrongTaxpayerStatusException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer(fullname, taxRegistrationNumber, status, income);
  }

  protected final void createReceipt(final int receiptId, final String issueDate,
      final float amount, final String kind, final String companyName, final String country,
      final String city, final String street, final int number, final int taxRegistrationNumber)
      throws WrongReceiptKindException, WrongReceiptDateException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street,
        number, taxRegistrationNumber);
  }

  protected final boolean isEmpty(final String line) {
    return line == null;
  }

  protected final int checkForReceipt(final BufferedReader inputStream)
      throws NumberFormatException, IOException {
    String line;
    while (!isEmpty(line = inputStream.readLine())) {
      String[] values = line.split(" ", 3);
      if (checkReceiptTags(values) == true) {
        int receiptId = Integer.parseInt(getReceiptIDValue(values));
        return receiptId;
      }
    }
    return -1;
  }

  protected abstract boolean checkReceiptTags(String[] values);

  protected abstract String getReceiptIDValue(String[] values);

  protected final String getValueOfField(final String fieldsLine) throws WrongFileFormatException {
    if (isEmpty(fieldsLine)) {
      throw new WrongFileFormatException();
    }
    try {
      return postProcessValues(fieldsLine);
    } catch (NullPointerException e) {
      throw new WrongFileFormatException();
    }
  }

  protected abstract String postProcessValues(String fieldsLine);

}