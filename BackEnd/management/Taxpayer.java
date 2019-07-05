package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  protected final int typeOfTaxpayer;
  private float[] amountPerReceiptsKind = new float[5];
  private String[] kindsOfReceipts = { "Entertainment", "Basic", "Travel", "Health", "Other" };
  private double[][] arrayToCalculateVariationTax = { { 0.2, 0.08 }, { 0.4, 0.04 }, { 0.6, -0.15 },
      { 1.0, -0.3 } };
  private double[] percentage = { 0, 0.0535, 0.0705, 0.0785, 0.0785, 0.0985 };
  private double[][] taxLimits = { { 0, 18040, 71680, 90000, 127120 },
      { 0, 36080, 90000, 143350, 254240 }, { 0, 30390, 90000, 122110, 203390 },
      { 0, 24680, 81080, 90000, 152540 } };

  private boolean nameErrorOfKind = true;
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);

  protected Taxpayer(final String fullname, final int taxRegistrationNumber, final float income, final int typeOfTaxpayer) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
    this.typeOfTaxpayer = typeOfTaxpayer;
  }

  public final double calculateBasicTax() {
    if (income < taxLimits[typeOfTaxpayer][1]) {
      return percentage[0] * taxLimits[typeOfTaxpayer][0]
          + percentage[1] * (income - taxLimits[typeOfTaxpayer][0]);
    } else if (income < taxLimits[typeOfTaxpayer][2]) {
      return percentage[1] * taxLimits[typeOfTaxpayer][1]
          + percentage[2] * (income - taxLimits[typeOfTaxpayer][1]);
    } else if (income < taxLimits[typeOfTaxpayer][3]) {
      return percentage[2] * taxLimits[typeOfTaxpayer][2]
          + percentage[3] * (income - taxLimits[typeOfTaxpayer][2]);
    } else if (income < taxLimits[typeOfTaxpayer][4]) {
      return percentage[3] * taxLimits[typeOfTaxpayer][3]
          + percentage[4] * (income - taxLimits[typeOfTaxpayer][3]);
    } else {
      return percentage[4] * taxLimits[typeOfTaxpayer][4]
          + percentage[5] * (income - taxLimits[typeOfTaxpayer][4]);

    }
  }

  public final void addReceipt(final Receipt receipt) throws WrongReceiptKindException {

    for (int i = 0; i < 5; i++) {

      if (receipt.getKind().equals(kindsOfReceipts[i])) {
        amountPerReceiptsKind[i] += receipt.getAmount();
        nameErrorOfKind = false;
      }
    }
    if (nameErrorOfKind) {
      throw new WrongReceiptKindException();
    }
    nameErrorOfKind = true;
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;

  }

  public final void removeReceipt(final int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    for (int i = 0; i < 5; i++) {

      if (receipt.getKind().equals(kindsOfReceipts[i])) {
        amountPerReceiptsKind[i] -= receipt.getAmount();
        nameErrorOfKind = false;
      }
    }
    if (nameErrorOfKind) {
      throw new WrongReceiptKindException();
    }
    nameErrorOfKind = true;
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }

  public final String getFullname() {
    return fullname;
  }

  public final int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public final float getIncome() {
    return income;
  }

  public final HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public final double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for (int i = 0; i < 4; i++) {
      if (totalAmountOfReceipts < arrayToCalculateVariationTax[i][0] * income) {
        return calculateBasicTax() * arrayToCalculateVariationTax[i][1];
      }
    }
    return 0.0;

  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public final int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public final float getAmountOfReceiptKind(final short kind) {
    return amountPerReceiptsKind[kind];
  }

  public final double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public final double getBasicTax() {
    return calculateBasicTax();
  }

}