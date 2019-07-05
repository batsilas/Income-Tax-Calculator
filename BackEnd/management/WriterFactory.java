package incometaxcalculator.data.management;

import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLInfoWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileFormatException;

import java.io.File;
import java.io.IOException;

public class WriterFactory {
  
  public static void updateFilesFactoryMethod(final int taxRegistrationNumber) throws IOException {
  
      if (new File(taxRegistrationNumber + "_INFO.xml").exists()) {
        new XMLInfoWriter().generateFile(taxRegistrationNumber);
      } else {
        new TXTInfoWriter().generateFile(taxRegistrationNumber);
        return;
      }
      if (new File(taxRegistrationNumber + "_INFO.txt").exists()) {
        new TXTInfoWriter().generateFile(taxRegistrationNumber);
      }
  }
  public static void saveLogFileFactory(final int taxRegistrationNumber, final String fileFormat)
      throws IOException, WrongFileFormatException {

      if (fileFormat.equals("txt")) {
        TXTLogWriter writer = new TXTLogWriter();
        writer.generateFile(taxRegistrationNumber);
      } else if (fileFormat.equals("xml")) {
        XMLLogWriter writer = new XMLLogWriter();
        writer.generateFile(taxRegistrationNumber);
      } else {
        throw new WrongFileFormatException();
      }
  }

}
