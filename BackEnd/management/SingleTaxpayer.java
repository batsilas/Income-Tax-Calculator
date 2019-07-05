package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {

  public SingleTaxpayer(final String fullname, final int taxRegistrationNumber, final float income) {
    super(fullname, taxRegistrationNumber, income, 3);
  }

}