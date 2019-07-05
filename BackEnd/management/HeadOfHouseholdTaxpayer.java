package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {

  public HeadOfHouseholdTaxpayer(final String fullname, final int taxRegistrationNumber, final float income) {
    super(fullname, taxRegistrationNumber, income, 2);
  }

}
