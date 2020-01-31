package es.upm.miw.betca_tpv_spring.business_services;

public class Barcode {

    public String ean13ControlCodeCalculator(String code) {
        char[] charDigits = code.toCharArray();
        int[] ean13 = {1, 3};
        int sum = 0;
        for (int i = 0; i < charDigits.length; i++) {
            sum += Character.getNumericValue(charDigits[i]) * ean13[i % 2];
        }
        return String.valueOf((10 - sum % 10) % 10);
    }

    public String generateEan13code(long value) {
        String fill = "000000000000";
        String code = String.valueOf(value);
        code = fill.substring(code.length()) + code;
        return code + this.ean13ControlCodeCalculator(code);
    }

}
