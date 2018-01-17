package Problem8;

import java.util.ArrayList;
import java.util.List;

public class ProductOfXadjacentDigits {
    private String stringNumber;

    public ProductOfXadjacentDigits(String stringNumber) {
        this.stringNumber = stringNumber;
    }

    private List<String> splitStringNumberIntoXPart(int xPart) {
        this.stringNumber.split("");
        return null;
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
            quotient = quotient / 10;
            rest = (int) (quotient % 10);
            digits.add(rest);
        }

        return digits;
    }

    public Long getProductOfXDigits(int nbrDigits) {
        List<String> stringDigits = splitStringNumberIntoXPart(nbrDigits);
        List<Long> longDigits = convertStringDigitsToLong(stringDigits);
        List<Long> allProducts = getAllProducts(longDigits);
        return calculateMaxProduct(allProducts);
    }

}
