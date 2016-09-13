package com.mayi.mylibrary;

/**
 * 作者 by yugai 时间 16/9/13.
 * ＊ 邮箱 784787081@qq.com
 */
public interface AlertInterface {

    interface OnPosClickListener {
        void onClick(IOSAlert alert);
    }
    interface OnNegClickListener {
        void onClick(IOSAlert alert);
    }
    interface OnAlertDismissListener{
        void onDismiss(IOSAlert alert);
    }
}
