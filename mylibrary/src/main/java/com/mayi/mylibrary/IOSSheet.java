package com.mayi.mylibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */

public class IOSSheet implements DialogInterface.OnDismissListener, AdapterView.OnItemClickListener {
    private static final String TAG = "IOSSheet";
    private Context context;
    private List<String> buttonTitles;
    private List<Integer> buttonColors;
    private String title;
    private int titleColor;
    private String cancel;
    private int cancelColor;
    private AppCompatDialog dialog;
    private SheetAdapter adapter;
    private View rootView;
    private boolean hasTitle;
    private ListView buttons;
    private TextView tvTitle;
    private TextView tvCancel;
    private OnSheetDismissListener mOnSheetDismissListener;
    private OnSheetSelectListener mOnSheetSelectListener;
    private boolean autoDismiss=true;


    private IOSSheet(Context context){
        this.context=context;
        dialog = new AppCompatDialog(context, R.style.SheetStyle);
        dialog.setOnDismissListener(this);
        //根视图
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.layout_ios_sheet, null);
        //提示文字
        tvTitle = (TextView) layout.findViewById(R.id.title);
        //选项按钮
        buttons = (ListView) layout.findViewById(R.id.sheetList);
        buttons.setOnItemClickListener(this);
        //取消按钮
        tvCancel = (TextView) layout.findViewById(R.id.cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rootView=layout;
    }

    private void setTitle(){
        if (title==null||title.isEmpty()){
            tvTitle.setVisibility(View.GONE);
            hasTitle=false;
        }else{
            tvTitle.setText(title);
            tvTitle.setTextColor(titleColor);
            hasTitle=true;
        }
    }

    private void setCancel(){
        tvCancel.setText(cancel);
        tvCancel.setTextColor(cancelColor);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (mOnSheetDismissListener!=null){
            mOnSheetDismissListener.onDismiss(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnSheetSelectListener!=null){
            mOnSheetSelectListener.onItemSelect(this,position);
        }
        if (autoDismiss) dismiss();
    }


    public static class Builder {
        private IOSSheet sheet;
        IOSConfig mConfig=IOSConfig.DEFALUT;
        private List<String> buttonTitles;
        private List<Integer> buttonColors;
        public Builder(Context context){
            sheet = new IOSSheet(context);
            buttonTitles = new ArrayList<>();
            buttonColors = new ArrayList<>();
        }
        public Builder autoDismiss(boolean dismiss){
            sheet.autoDismiss=dismiss;
            return this;
        }
        public Builder addButton(String text){
            addButton(text,mConfig.getSheetButtonColor());
            return this;
        }
        public Builder addButton(String text,int color){
            buttonTitles.add(text);
            buttonColors.add(color);
            return this;
        }
        public Builder setTitle(String text){
            setTitle(text,mConfig.getSheetTitleColor());
            return this;
        }
        public Builder setTitle(String text,int color){
            sheet.title=text;
            sheet.titleColor=color;
            return this;
        }
        public Builder setCancel(String text){
            setCancel(text,mConfig.getSheetCancelColor());
            return this;
        }
        public Builder setCancel(String text,int color){
            sheet.cancel=text;
            sheet.cancelColor=color;
            sheet.setCancel();
            return this;
        }
        public Builder setOnSheetSelectListener(OnSheetSelectListener onSheetSelectListener){
            sheet.mOnSheetSelectListener=onSheetSelectListener;
            return this;
        }
        public Builder setOnDismissListener(OnSheetDismissListener onSheetDismissListener){
            sheet.mOnSheetDismissListener=onSheetDismissListener;
            return this;
        }
        public IOSSheet create() {
            sheet.buttonTitles=this.buttonTitles;
            sheet.buttonColors=this.buttonColors;
            sheet.setTitle();
            return sheet;
        }
    }
    public IOSSheet show() {
        if (adapter==null){
            adapter=new SheetAdapter(buttonTitles,buttonColors,hasTitle,context);
            buttons.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
        // set window param
        rootView.setMinimumWidth(context.getResources().getDisplayMetrics().widthPixels);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.gravity = Gravity.BOTTOM;
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(rootView);
        dialog.show();
        return this;
    }
    public void dismiss(){
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }

}
