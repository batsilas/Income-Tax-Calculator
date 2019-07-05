package incometaxcalculator.data.management;

public class Address {

  private final String country;
  private final String city;
  private final String street;
  private final int number;

  public Address(final String country, final String city, final String street, final int number) {
    this.country = country;
    this.city = city;
    this.street = street;
    this.number = number;
  }

  public final String getCountry() {
    return country;
  }

  public final String getCity() {
    return city;
  }

  public final String getStreet() {
    return street;
  }

  public final int getNumber() {
    return number;
  }

  public final String toString() {
    return (country + " " + city + " " + street + " " + number);
  }
}