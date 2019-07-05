package incometaxcalculator.data.io;

public class XMLFileReader extends FileReader {

  protected final boolean checkReceiptTags(final String[] values) {
    return values[0].equals("<ReceiptID>");
  }

  protected final String getReceiptIDValue(final String[] values) {
    return values[1].trim();
  }

  protected final String postProcessValues(final String fieldsLine) {
    String[] valueWithTail = fieldsLine.split(" ", 2);
    String[] valueReversed = new StringBuilder(valueWithTail[1]).reverse().toString().trim()
        .split(" ", 2);
    return new StringBuilder(valueReversed[1]).reverse().toString();
  }

}
