package incometaxcalculator.data.management;

public class MarriedFilingSeparatelyTaxpayer extends Taxpayer {

  public MarriedFilingSeparatelyTaxpayer(final String fullname, final int taxRegistrationNumber, final float income) {
    super(fullname, taxRegistrationNumber, income, 0);
  }

}