package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongReceiptDateException;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Receipt {

  private final int id;
  private final Date issueDate;
  private final float amount;
  private final String kind;
  private final Company company;
  private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

  public Receipt(final int id, final String issueDate, final float amount, final String kind, final Company company)
      throws WrongReceiptDateException {
    this.id = id;
    this.issueDate = createDate(issueDate);
    this.amount = amount;
    this.kind = kind;
    this.company = company;
  }

  private Date createDate(final String issueDate) throws WrongReceiptDateException {
    String[] token = issueDate.split("/");
    if (token.length != 3) {
      throw new WrongReceiptDateException();
    }

    Date date = new Date();
    try {
      date = DATE_FORMAT.parse(issueDate);
    } catch (ParseException e) {

      e.printStackTrace();
    }

    return date;
  }

  public final int getId() {
    return id;
  }

  public final String getIssueDate() {
    return DATE_FORMAT.format(issueDate);
  }

  public final float getAmount() {
    return amount;
  }

  public final String getKind() {
    return kind;
  }

  public final Company getCompany() {
    return company;
  }
}