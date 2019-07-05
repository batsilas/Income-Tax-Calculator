package incometaxcalculator.data.management;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.data.io.XMLInfoWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxPayerFactory {

  public static void createTaxpayerFactory(final String fullname, final int taxRegistrationNumber,
      final String status, final float income, final HashMap<Integer, Taxpayer> taxpayerHashMap)
      throws WrongTaxpayerStatusException {

    if (status.equals("Married Filing Jointly")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new MarriedFilingJointlyTaxpayer(fullname, taxRegistrationNumber, income));
    } else if (status.equals("Married Filing Separately")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new MarriedFilingSeparatelyTaxpayer(fullname, taxRegistrationNumber, income));
    } else if (status.equals("Single")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new SingleTaxpayer(fullname, taxRegistrationNumber, income));
    } else if (status.equals("Head of Household")) {
      taxpayerHashMap.put(taxRegistrationNumber,
          new HeadOfHouseholdTaxpayer(fullname, taxRegistrationNumber, income));
    } else {
      throw new WrongTaxpayerStatusException();
    }
  }



}
