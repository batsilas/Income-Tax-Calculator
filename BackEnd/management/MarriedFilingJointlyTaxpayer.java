package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {

  public MarriedFilingJointlyTaxpayer(final String fullname, final int taxRegistrationNumber, final float income) {
    super(fullname, taxRegistrationNumber, income, 1);
  }

}