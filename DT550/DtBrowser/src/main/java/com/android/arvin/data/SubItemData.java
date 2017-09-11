package com.android.arvin.data;

/**
 * Created by arvin on 2017/9/8 0008.
 */

public class SubItemData {

    private String itemCode;
    private int measure_item_liquid_statc_bg = -1;
    private String measure_item_liquid_state_text;
    private String measure_item_name_text;
    private String measure_item_value_text;
    private String measure_item_time_text;

    public SubItemData(String code,
                       int measure_item_liquid_statc_boolean,
                       String measure_item_liquid_state_text,
                       String measure_item_name_text,
                       String measure_item_value_text,
                       String measure_item_time_text) {
        this.itemCode = code;
        this.measure_item_liquid_statc_bg = measure_item_liquid_statc_boolean;
        this.measure_item_liquid_state_text = measure_item_liquid_state_text;
        this.measure_item_name_text = measure_item_name_text;
        this.measure_item_value_text = measure_item_value_text;
        this.measure_item_time_text = measure_item_time_text;
    }

    public int isMeasure_item_liquid_statc_bg() {
        return measure_item_liquid_statc_bg;
    }

    public void setMeasure_item_liquid_statc_bg(int measure_item_liquid_statc_boolean) {
        this.measure_item_liquid_statc_bg = measure_item_liquid_statc_boolean;
    }

    public String getMeasure_item_liquid_state_text() {
        return measure_item_liquid_state_text;
    }

    public void setMeasure_item_liquid_state_text(String measure_item_liquid_state_text) {
        this.measure_item_liquid_state_text = measure_item_liquid_state_text;
    }

    public String getMeasure_item_name_text() {
        return measure_item_name_text;
    }

    public void setMeasure_item_name_text(String measure_item_name_text) {
        this.measure_item_name_text = measure_item_name_text;
    }

    public String getMeasure_item_value_text() {
        return measure_item_value_text;
    }

    public void setMeasure_item_value_text(String measure_item_value_text) {
        this.measure_item_value_text = measure_item_value_text;
    }

    public String getMeasure_item_time_text() {
        return measure_item_time_text;
    }

    public void setMeasure_item_time_text(String measure_item_time_text) {
        this.measure_item_time_text = measure_item_time_text;
    }
}
