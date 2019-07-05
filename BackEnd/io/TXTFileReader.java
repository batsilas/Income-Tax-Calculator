package incometaxcalculator.data.io;

public class TXTFileReader extends FileReader {

  protected final boolean checkReceiptTags(final String[] values) {
    return values[0].equals("Receipt") && values[1].equals("ID:");
  }

  protected final String getReceiptIDValue(final String[] values) {
    return values[2].trim();
  }

  protected final String postProcessValues(final String fieldsLine) {
    String[] values = fieldsLine.split(" ", 2);

    values[1] = values[1].trim();
    return values[1];
  }

}