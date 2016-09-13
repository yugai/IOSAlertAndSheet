package com.mayi.mylibrary;

import android.graphics.Color;

/**
 * 作者 by yugai 时间 16/9/12.
 * ＊ 邮箱 784787081@qq.com
 */
public class  IOSConfig {
    //默认配置
    public static IOSConfig DEFALUT = new Builder().build();
    private int sheetTitleColor;
    private int sheetCancelColor;
    private int sheetButtonColor;

    private int alertTitleColor;
    private int alertMessageColor;
    private int alertButtonColor;



    private IOSConfig(Builder builder){
        this.sheetTitleColor=builder.sheetTitleColor;
        this.sheetCancelColor=builder.sheetCancelColor;
        this.sheetButtonColor=builder.sheetButtonColor;

        this.alertButtonColor=builder.alertButtonColor;
        this.alertTitleColor=builder.alertTitleColor;
        this.alertMessageColor=builder.alertMessageColor;
    }
    public static class Builder{
        private int sheetTitleColor= Color.parseColor("#efc3c4c6");
        private int sheetCancelColor=Color.parseColor("#FF3680F9");
        private int sheetButtonColor=Color.parseColor("#FF3680F9");

        private int alertTitleColor=Color.parseColor("#FF252525");
        private int alertMessageColor=Color.parseColor("#FF565656");
        private int alertButtonColor=Color.parseColor("#FF3680F9");

        public Builder setSheetTitleColor(int sheetTitleColor) {
            this.sheetTitleColor = sheetTitleColor;
            return this;
        }

        public Builder setSheetCancelColor(int sheetCancelColor) {
            this.sheetCancelColor = sheetCancelColor;
            return this;
        }

        public Builder setSheetButtonColor(int sheetButtonColor) {
            this.sheetButtonColor = sheetButtonColor;
            return this;
        }

        public Builder setAlertTitleColor(int alertTitleColor) {
            this.alertTitleColor = alertTitleColor;
            return this;
        }

        public Builder setAlertMessageColor(int alertMessageColor) {
            this.alertMessageColor = alertMessageColor;
            return this;
        }

        public Builder setAlertButtonColor(int alertButtonColor) {
            this.alertButtonColor = alertButtonColor;
            return this;
        }

        public IOSConfig build(){
            IOSConfig config = new IOSConfig(this);
            DEFALUT = config;
            return config;
        }
    }

    public int getSheetTitleColor() {
        return sheetTitleColor;
    }

    public int getSheetCancelColor() {
        return sheetCancelColor;
    }

    public int getSheetButtonColor() {
        return sheetButtonColor;
    }

    public int getAlertTitleColor() {
        return alertTitleColor;
    }

    public int getAlertMessageColor() {
        return alertMessageColor;
    }

    public int getAlertButtonColor() {
        return alertButtonColor;
    }
}
