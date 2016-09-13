package com.mayi.iosalertandsheet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mayi.mylibrary.AlertInterface;
import com.mayi.mylibrary.IOSAlert;
import com.mayi.mylibrary.IOSConfig;
import com.mayi.mylibrary.IOSSheet;
import com.mayi.mylibrary.OnSheetSelectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //可以应用初始化的时候配置默认的颜色 推荐application里面  不设置则使用自带默认颜色
        /*IOSConfig.DEFALUT=new IOSConfig.Builder()
                .setAlertButtonColor(Color.parseColor("#ffffff"))
                .setAlertTitleColor(Color.CYAN)
                .setAlertMessageColor(Color.argb(0x23,0x23,0x23,0x23))
                .setSheetButtonColor(Color.YELLOW)
                .build();*/
    }

    public void onClickA(View view){
        new IOSSheet.Builder(this)
                .setTitle("这是一段提示文字")
                .addButton("选项一")
                .addButton("选项二")
                .addButton("选项三")
                .setCancel("取消")
                .setOnSheetSelectListener(new OnSheetSelectListener() {
                    @Override
                    public void onItemSelect(IOSSheet sheet, int position) {
                        Toast.makeText(MainActivity.this,"对应的"+position,Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }
    public void onClickB(View view){
        new IOSSheet.Builder(this)
                .addButton("选项一",Color.RED)
                .addButton("选项二")
                .addButton("选项三",Color.YELLOW)
                .addButton("选项四")
                .setCancel("取消",Color.RED)
                .setOnSheetSelectListener(new OnSheetSelectListener() {
                    @Override
                    public void onItemSelect(IOSSheet sheet, int position) {
                        Toast.makeText(MainActivity.this,"对应的"+position,Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }
    public void onClickC(View view){
        new IOSAlert.Builder(this)
                .setTitle("Title")
                .setMessage("这是一条提示内容！")
                .setNegativeButton("取消")
                .setPositiveButton("确定", Color.RED, new AlertInterface.OnPosClickListener() {
                    @Override
                    public void onClick(IOSAlert alert) {
                        Toast.makeText(MainActivity.this,"点击确定",Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();

    }
    public void onClickD(View view){
        new IOSAlert.Builder(this)
                .setTitle("Title",Color.parseColor("#f0f0f0"))
                .setMessage("这是一条提示内容！")
                .setPositiveButton("关闭", new AlertInterface.OnPosClickListener() {
                    @Override
                    public void onClick(IOSAlert alert) {
                        Toast.makeText(MainActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }
    public void onClickE(View view){
        View testView=getLayoutInflater().inflate(R.layout.test_view,null);
        new IOSAlert.Builder(this)
                .setView(testView)
                .create().show();
    }
}
