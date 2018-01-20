package Problem8;

import java.util.ArrayList;
import java.util.List;

public class ProductOfXadjacentDigits {
    private String stringNumber;
    private int nbrDigits;

    public ProductOfXadjacentDigits(String stringNumber, int nbrDigits) {
        this.stringNumber = removeSpaces(stringNumber);
        this.nbrDigits = nbrDigits;
    }

    private String removeSpaces(String stringNumber) {
        stringNumber = stringNumber.replace("\n", "");
        return stringNumber;
    }

    private List<String> getAllPossiblePartsList() {
        int startFrom;
        int stopIn = (int) Math.floor(stringNumber.length() / 2);
        List<String> allPossiblePartsList = new ArrayList<String>();

        for (startFrom = 0; startFrom < stopIn; startFrom++) {
            allPossiblePartsList.addAll(splitStringNumberIntoXPart(startFrom));
        }

        return allPossiblePartsList;
    }

    private List<String> splitStringNumberIntoXPart(int startFrom) {
        int i;
        String digitPart;
        String digitPartsContainer = "";
        List<String> digitsPartsList = new ArrayList<String>();
        char digit;

        for (i = startFrom; i < stringNumber.length(); i++) {
            digit = stringNumber.charAt(i);
            digitPart = Character.toString(digit);

            if (digitPart.matches("[0-9]+")) {
                digitPartsContainer = digitPartsContainer.concat(digitPart);
            } else {
                continue;
            }

            if((digitPartsContainer.length() == nbrDigits || i == stringNumber.length() - 1)) {
                digitsPartsList.add(digitPartsContainer);
                digitPartsContainer = "";
            }
        }

        return digitsPartsList;
    }

    private List<Long> convertStringDigitsToLong(List<String> stringDigits) {
        List<Long> longDigits = new ArrayList<Long>();

        for (String digit : stringDigits) {
            longDigits.add(stringNumberToLong(digit));
        }

        return longDigits;
    }

    private Long calculateMaxProduct(List<Long> productsList) {
        Long maxProduct = 0L;

        for (Long product : productsList) {
            if (maxProduct < product) {
                maxProduct = product;
            }
        }

        return maxProduct;
    }

    private Long stringNumberToLong(String stringNumber) {
        return Long.valueOf(stringNumber);
    }

    private Long calculateDigitsProduct(Long number) {
        List<Integer> numberDigits = new ArrayList<Integer>();
        numberDigits.addAll(getListOfDigits(number));
        Long product = 1L;

        for (Integer numberDigit : numberDigits) {
            product *= numberDigit;
        }

        return product;
    }

    private List<Long> getAllProducts(List<Long> longDigitsList) {
        List<Long> productsList = new ArrayList<Long>();

        for (Long digits : longDigitsList) {
            productsList.add(calculateDigitsProduct(digits));
        }

        return productsList;
    }

    private List<Integer> getListOfDigits(Long number) {
        Long quotient = number;
        int rest;
        List<Integer> digits = new ArrayList<Integer>();

        while (quotient != 0) {
            rest = (int) (quotient % 10);
            quotient = quotient / 10;
            digits.add(rest);
        }

        return digits;
    }

    public Long getProductOfXDigits() {
        List<String> stringDigits = getAllPossiblePartsList();
        List<Long> longDigits = convertStringDigitsToLong(stringDigits);
        List<Long> allProducts = getAllProducts(longDigits);
        for(String part : stringDigits) {
            System.out.println(part + " possible part");
        }
        return calculateMaxProduct(allProducts);
    }

}
