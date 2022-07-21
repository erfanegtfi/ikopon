package com.ikopon.ikopon.core;

public class Constants {

    public static String DEVICE_TYPE = "ANDROID";

    public static long SEARCH_BOX_DEBOUNCE_TIME=800;
    public static int SEARCH_BOX_MIN_CHARACTER = 3;
    public static String QR_CODE_API_URL = "https://chart.googleapis.com/chart?chs=300x300&cht=qr&chld=L|0&chl=";
    public static String APPLICATION_DOWNLOAD = "https://play.google.com/store/apps/details?id=com.ikopon.ikopon";

    public static final String SHARE_PRODUCT = "https://ikopon.com/product/%d/";
    public static final String REMOVE_HTML_TAG_PATTERN = "\\<.*?\\>";
    public static final String PRODUCT_UID_SHARE_LINK_REGEX = "product\\/(.*?)\\/";

    public static long SELECT_BACK_TWICE_EXIT = 2000;

    public static int GET_WAY_FROM_CART = 4;
    public static int GET_WAY_FROM_CHARGE = 3;
}
