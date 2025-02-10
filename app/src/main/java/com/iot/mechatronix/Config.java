package com.iot.mechatronix;

public class Config {

    public static final String DATABASE_NAME = "iot-db";

    //column names of productType table
    public static final String TABLE_PRODUCT_CATEGORY = "product_category";
    public static final String COLUMN_PRODUCT_CATEGORY_ID = "cat_id";
    public static final String COLUMN_PRODUCT_CATEGORY_NAME = "cat_name";

    //column names of product table
    public static final String TABLE_PRODUCT = "table_product";
    public static final String COLUMN_PRODUCT_CATEGORY_ID_REL = "product_cat_id_rel";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";

    //column names of SHADE table
    public static final String TABLE_SHADE = "table_shade";
    public static final String COLUMN_PRODUCT_ID_REL = "product_id_rel";
    public static final String COLUMN_SHADE_ID = "shade_id";
    public static final String COLUMN_SHADE_NAME = "shade_name";

    //column names of Color table
    public static final String TABLE_COLOR = "table_color";
    public static final String COLUMN_SHADE_ID_REL = "shade_id_rel";
    public static final String COLUMN_COLOR_ID = "color_id";
    public static final String COLUMN_COLOR_NAME = "color_name";
    public static final String COLUMN_COLOR_CODE = "color_code";


    public static final String COLUMN_COLOR_TINT_TYPE_1 = "tint_type_1";
    public static final String COLUMN_COLOR_TINT_TYPE_1_VALUE = "tint_type_1_value";
    public static final String COLUMN_COLOR_TINT_TYPE_2 = "tint_type_2";
    public static final String COLUMN_COLOR_TINT_TYPE_2_VALUE = "tint_type_2_value";
    public static final String COLUMN_COLOR_TINT_TYPE_3 = "tint_type_3";
    public static final String COLUMN_COLOR_TINT_TYPE_3_VALUE = "tint_type_3_value";
    public static final String COLUMN_COLOR_TINT_TYPE_4 = "tint_type_4";
    public static final String COLUMN_COLOR_TINT_TYPE_4_VALUE = "tint_type_4_value";
    public static final String COLUMN_COLOR_TINT_TYPE_5 = "tint_type_5";
    public static final String COLUMN_COLOR_TINT_TYPE_5_VALUE = "tint_type_5_value";


    //column names of ColorTint table
    public static final String TABLE_TINT = "table_color_tint";
    public static final String COLUMN_TINT_ID = "tint_id";
    public static final String COLUMN_TINT_NAME = "tint_name";
    public static final String COLUMN_TINT_COST_LTR = "tint_cost_ltr";

    public static final String COLUMN_TINT_STATUS = "tint_status";

    //column names of Product Base table
    public static final String TABLE_BASE = "table_base";
    public static final String COLUMN_BASE_ID = "base_id";
    public static final String COLUMN_BASE_NAME = "base_name";
    public static final String COLUMN_BASE_CODE = "base_code";

    //column names of Product Base table
    public static final String TABLE_PACK = "table_pack";
    public static final String COLUMN_BASE_ID_REL = "base_id_rel";
    public static final String COLUMN_PACK_ID = "pack_id";
    public static final String COLUMN_PACK_NAME = "pack_name";
    public static final String COLUMN_PACK_SIZE = "pack_size";
    public static final String COLUMN_PACK_COST = "pack_cost";

}
