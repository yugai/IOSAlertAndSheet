package com.mayi.mylibrary;


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/12.
 */

public class IOSAlert implements DialogInterface.OnDismissListener {
    private static final String TAG = "IOSAlert";
    private AlertDialog dialog;
    private Activity mContext;
    private RelativeLayout rootView;
    private String title;
    private String message;
    private int titleColor;
    private int messageColor;
    private int maxMessageLine;
    private TextView tvTitle;
    private LinearLayout contentView;
    private View customView;
    private String posButton,negButton;
    private int posButtonColor,negButtonColor;
    private AlertInterface.OnNegClickListener mOnNegClickListener;
    private AlertInterface.OnPosClickListener mOnPosClickListener;
    private AlertInterface.OnAlertDismissListener onAlertDismissListener;
    private boolean autoDismiss=true;


    private IOSAlert(Activity context){
        mContext=context;
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_ios_alert,null);
        tvTitle= (TextView) view.findViewById(R.id.title);
        contentView= (LinearLayout) view.findViewById(R.id.ly_content);
        dialog = new AlertDialog.Builder(context).setView(view).create();
        dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        dialog.setOnDismissListener(this);
        rootView=view;
    }
    private void init(){
        if (title==null||title.isEmpty()){
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setText(title);
            tvTitle.setTextColor(titleColor);
        }

        if (message!=null){
            TextView tvMessage = new TextView(mContext);
            tvMessage.setText(message);
            tvMessage.setTextColor(messageColor);
            tvMessage.setGravity(Gravity.CENTER);
            int padding=dip2px(5);
            if (tvTitle.getVisibility()==View.GONE){
                tvMessage.setPadding(padding,0,padding,padding);
                tvMessage.setMinHeight(dip2px(42));
            }else{
                tvMessage.setPadding(padding,0,padding,padding*2);
            }
            if (maxMessageLine!=0){
                tvMessage.setMaxLines(maxMessageLine);
                tvMessage.setVerticalScrollBarEnabled(true);
                tvMessage.setMovementMethod(new ScrollingMovementMethod());
            }
            contentView.addView(tvMessage);
        }

        if (customView!=null){
            contentView.addView(customView);
        }

        View bottomButton;
        if (posButton==null&&negButton==null){
            Log.i(TAG, "没有底部按钮");
//            RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) contentView.getLayoutParams();
        }else if (posButton==null){
            bottomButton=LayoutInflater.from(mContext).inflate(R.layout.bottom_single,null);
            TextView button=(TextView)bottomButton.findViewById(R.id.tv_button);
            button.setTextColor(negButtonColor);
            button.setText(negButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnNegClickListener!=null){
                        mOnNegClickListener.onClick(IOSAlert.this);
                    }
                    if (autoDismiss) dismiss();
                }
            });
            contentView.addView(bottomButton);
        }else if (negButton==null){
            bottomButton=LayoutInflater.from(mContext).inflate(R.layout.bottom_single,null);
            TextView button=(TextView)bottomButton.findViewById(R.id.tv_button);
            button.setTextColor(posButtonColor);
            button.setText(posButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnPosClickListener!=null){
                        mOnPosClickListener.onClick(IOSAlert.this);
                    }
                    if (autoDismiss) dismiss();
                }
            });
            contentView.addView(bottomButton);
        }else{
            bottomButton=LayoutInflater.from(mContext).inflate(R.layout.bottom_double,null);
            TextView posButton= (TextView) bottomButton.findViewById(R.id.tv_pos);
            TextView negButton= (TextView) bottomButton.findViewById(R.id.tv_neg);
            posButton.setText(this.posButton);
            negButton.setText(this.negButton);
            posButton.setTextColor(posButtonColor);
            negButton.setTextColor(negButtonColor);
            negButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnNegClickListener!=null){
                        mOnNegClickListener.onClick(IOSAlert.this);
                    }
                    if (autoDismiss) dismiss();
                }
            });
            posButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnPosClickListener!=null){
                        mOnPosClickListener.onClick(IOSAlert.this);
                    }
                    if (autoDismiss) dismiss();
                }
            });
            contentView.addView(bottomButton);
        }
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        if (onAlertDismissListener!=null){
            onAlertDismissListener.onDismiss(this);
        }
    }

    public static class Builder {
        IOSAlert mIOSAlert;
        IOSConfig mConfig = IOSConfig.DEFALUT;
        public Builder(Activity context){
            mIOSAlert=new IOSAlert(context);
        }
        public Builder setTitle(String title){
            setTitle(title,mConfig.getAlertTitleColor());
            return this;
        }
        public Builder setTitle(String title,int color){
            mIOSAlert.titleColor=color;
            mIOSAlert.title=title;
            return this;
        }

        public Builder autoDismiss(boolean dismiss){
            mIOSAlert.autoDismiss=dismiss;
            return this;
        }

        public Builder setMessage(String message){
            setMessage(message,mConfig.getAlertMessageColor());
            return this;
        }
        public Builder setMessage(String message,int color){
            mIOSAlert.message=message;
            mIOSAlert.messageColor=color;
            return this;
        }
        public Builder setMaxMessageLine(int maxline){
            mIOSAlert.maxMessageLine=maxline;
            return this;
        }
        public Builder setView(View view){
            mIOSAlert.customView=view;
            return this;
        }

        public Builder setPositiveButton(String text){
            setPositiveButton(text,null);
            return this;
        }
        public Builder setNegativeButton(String text){
            setNegativeButton(text,null);
            return this;
        }
        public Builder setPositiveButton(String text, AlertInterface.OnPosClickListener onPosClickListener){
            setPositiveButton(text,mConfig.getAlertButtonColor(),onPosClickListener);
            return this;
        }
        public Builder setNegativeButton(String text, AlertInterface.OnNegClickListener onNegClickListener){
            setNegativeButton(text,mConfig.getAlertButtonColor(),onNegClickListener);
            return this;
        }
        public Builder setPositiveButton(String text,int color, AlertInterface.OnPosClickListener onPosClickListener){
            mIOSAlert.posButton=text;
            mIOSAlert.mOnPosClickListener=onPosClickListener;
            mIOSAlert.posButtonColor=color;
            return this;
        }
        public Builder setNegativeButton(String text,int color, AlertInterface.OnNegClickListener onNegClickListener){
            mIOSAlert.negButton=text;
            mIOSAlert.mOnNegClickListener=onNegClickListener;
            mIOSAlert.negButtonColor=color;
            return this;
        }
        public Builder setOnDismissListener(AlertInterface.OnAlertDismissListener onAlertDismissListener){
            mIOSAlert.onAlertDismissListener=onAlertDismissListener;
            return this;
        }
        public IOSAlert create(){
            mIOSAlert.init();
            return mIOSAlert;
        }
    }
    public IOSAlert show(){
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.i(TAG, "IOSAlert: "+metrics.widthPixels);
        params.width=metrics.widthPixels*3/4;
        dialog.getWindow().setAttributes(params);
        return this;
    }

    public void dismiss(){
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }

    public  int dip2px(float dipValue){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public  int px2dip(float pxValue){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
