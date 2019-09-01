package com.example.wangjingyang.ipc_aidl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.view.View.SYSTEM_UI_FLAG_LOW_PROFILE;
import static android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF;
/*
* Bundle 进程间通信
*
* */
public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                //序列化 Parcelable
//                Person person=new Person();
//                person.setSex("man");
//                person.setName("zhangsan");
//                person.setAge(20);
//                intent.putExtra("person",person);

                Bundle bundle=new Bundle();
                bundle.putFloat("bundlefloat",3.14f);
                bundle.putInt("bundleint",6);
                bundle.putBoolean("bundleboolean",true);
                bundle.putString("bundleString","bundleString");

                Custom custom=new Custom();
                custom.setAge(30);
                custom.setName("lisi");
                bundle.putSerializable("custom",custom);

                Person person=new Person();
                person.setSex("man");
                person.setName("lisi");
                person.setAge(40);
                bundle.putParcelable("person",person);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //sp 进程间传递数据
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("spData", "spdata main");
        editor.commit();


        //进程间  文件传输 数据
//        FileOutputStream outputStream=null;
//        try {
//            outputStream=openFileOutput("android.txt",Context.MODE_PRIVATE);
//            outputStream.write("main data".getBytes());
//        }
//        catch (FileNotFoundException e){
//            e.printStackTrace();
//        }
//        catch ( IOException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }





//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("ThreadActivity",""+Thread.currentThread().getName());
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i("ThreadActivity",""+Thread.currentThread().getName());
//            }
//        }).start();

//        Process process=new Process();
//        Thread thread=new Thread(process);
//        thread.start();
//        Thread thread1=new Thread(process);
//        thread1.start();


        //序列化　
//        serializable();
    }



    //序列化 对象 serializable
    private  void serializable(){

        Custom custom=new Custom();
        custom.setAge(30);
        custom.setName("lisi");
        String path=getExternalCacheDir().getAbsolutePath();
        File file=new File(path+"/custom.txt");
        try {
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(custom);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static WindowManager.LayoutParams getWindowManagerParams(Context context) {
//        int max = Math.max(width, height);

//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(max + ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION,
//                max + ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, 2006, 66072, -2);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.width = max + ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;
//        lp.height = max + ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;
        lp.width = 1000;
        lp.height = 1000;
        lp.type = (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <= 24) ? WindowManager.LayoutParams.TYPE_TOAST : WindowManager.LayoutParams.TYPE_PHONE;
        lp.format = PixelFormat.TRANSLUCENT;

        lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR;
        lp.flags = 17368856;
        lp.dimAmount = -1f;
        lp.gravity = 8388659;
        lp.buttonBrightness = BRIGHTNESS_OVERRIDE_OFF;
        lp.systemUiVisibility = SYSTEM_UI_FLAG_LOW_PROFILE;

        return lp;
    }
}
