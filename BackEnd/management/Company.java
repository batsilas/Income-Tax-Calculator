package incometaxcalculator.data.management;

public class Company {

  private final String name;
  private final Address address;

  public Company(final String name, final String country, final String city, final String street, final int number) {
    this.name = name;
    this.address = new Address(country, city, street, number);
  }

  public final String getName() {
    return name;
  }

  public final String getCountry() {
    return address.getCountry();
  }

  public final String getCity() {
    return address.getCity();
  }

  public final String getStreet() {
    return address.getStreet();
  }

  public final int getNumber() {
    return address.getNumber();
  }
}
