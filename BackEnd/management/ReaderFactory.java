package incometaxcalculator.data.management;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

import java.io.IOException;

public class ReaderFactory {
  
  public static void loadTaxpayerFactory(final String fileName)
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
  
      String[] ending = fileName.split("\\.");
      if (ending[1].equals("txt")) {
        FileReader reader = new TXTFileReader();
        reader.readFile(fileName);
      } else if (ending[1].equals("xml")) {
        FileReader reader = new XMLFileReader();
        reader.readFile(fileName);
      } else {
        throw new WrongFileEndingException();
      }
  }

}
