package com.example.wangjingyang.ipc_aidl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SecondActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (getIntent() != null) {
            //反序列化  Parcelable
//            Person person=getIntent().getParcelableExtra("person");
//            Log.i("SecondActivity", "person name " + person.getName()+" sex "+person.getSex()+" age "+person.getAge());

            //bu
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                float float1 = bundle.getFloat("bundlefloat");
                int int1 = bundle.getInt("bundleint");
                boolean boolean1 = bundle.getBoolean("bundleboolean");
                String string = bundle.getString("bundleString");
                Log.i("SecondActivity", " float1 "+float1+" "+int1+" "+boolean1+" "+string);

                Custom custom= (Custom) bundle.getSerializable("custom");
                Person person=bundle.getParcelable("person");

                Log.i("SecondActivity","custom "+custom.getName()+ "person "+person.getName());

            }
        }
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThridActivity.class);
                startActivity(intent);
            }
        });
        //sp 进程间传递数据不好用
        SharedPreferences sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
        String str = sharedPreferences.getString("spData", "");
        Log.i("SecondActivity", "str " + str);


        //        文件进程间传递数据

//        FileInputStream fileInputStream = null;
//        ByteArrayOutputStream byteArrayOutputStream = null;

//        try {
//            fileInputStream = openFileInput("android.txt");
//            int lenth = fileInputStream.available();
//            byte[] bytes = new byte[lenth];
//            while (fileInputStream.read(bytes) != -1) {
//                byteArrayOutputStream.write(bytes, 0, bytes.length);
//
//            }
//            Log.i("SecondActivity", "" + new String(byteArrayOutputStream.toByteArray()));
//        } catch (FileNotFoundException f) {
//            f.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        } finally {
//            try {
//                fileInputStream.close();
//                byteArrayOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        //反序列化 Serializable
//        deSerializable();
    }


    private void deSerializable() {


        String path = getExternalCacheDir().getAbsolutePath();
        File file = new File(path + "/custom.txt");
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Custom custom = (Custom) objectInputStream.readObject();
            Log.i("SecondActivity", "age " + custom.getAge() + " name " + custom.getName());
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
