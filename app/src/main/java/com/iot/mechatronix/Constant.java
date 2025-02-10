package com.iot.mechatronix;

public class Constant {
    public static final String PRODUCT_NAME = "PRODUCT_NAME";
    public static final String PRODUCT_ID = "PRODUCT_ID";

    public static final String BLUETOOTH_PREF = "BLUETOOTH_PREF";
    public static final String PREVIOUS_DEVICE_ADDRESS = "PREVIOUS_DEVICE_ADDRESS";
    public static final String PREVIOUS_DEVICE_NAME = "PREVIOUS_DEVICE_NAME";

    public static final String PRODUCT_CAT_NAME = "PRODUCT_CAT_NAME";
    public static final String PRODUCT_CAT_ID = "PRODUCT_CAT_ID";


    public static final String PRODUCT_SHADE_CARD_NAME = "PRODUCT_SHADE_CARD_NAME";
    public static final String PRODUCT_SHADE_CARD_ID = "PRODUCT_SHADE_CARD_ID";


    public static final String PRODUCT_SIZE = "PRODUCT_SIZE";
    public static final String PRODUCT_COLOR_CODE = "PRODUCT_COLOR_CODE";
    public static final String DISPENSING_COMMAND1 = "DISPENSING_COMMAND1";
    public static final String DISPENSING_COMMAND3 = "DISPENSING_COMMAND3";
    public static final String BILL_TOTAL_PRICE = "BILL_TOTAL_PRICE";
    public static final String BILL_GST = "BILL_GST";
    public static final String BILL_COLOR_NAME = "BILL_COLOR_NAME";

    public static final String PRODUCT_COLOR = "PRODUCT_COLOR";
    public static final String USER_PREF = "USER_PREF";
    public static final String PRODUCT_COLOR_ID = "PRODUCT_COLOR_ID";


    public static final String PRODUCT_BASE_NAME = "PRODUCT_BASE_NAME";
    public static final String PRODUCT_BASE_ID = "PRODUCT_BASE_ID";

    public static final String PACK_SIZE = "PACK_SIZE";
    public static final String PACK_SIZE_ID = "PACK_SIZE_ID";


    //RandomNumber create
    public static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghjiklmnopqrstuvwxyz";
    public static String NUMERIC_STRING = "123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static String randomNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * NUMERIC_STRING.length());
            builder.append(NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}