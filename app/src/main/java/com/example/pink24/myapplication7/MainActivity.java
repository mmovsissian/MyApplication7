package com.example.pink24.myapplication7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonfile= null;
                try {
                    jsonfile = new JSONObject(getfile());
                    JSONArray jsonarray=jsonfile.getJSONArray("myarray");
                    StringBuilder builder= new StringBuilder();
                    for (int i = 0; i <jsonarray.length() ; i++) {
                        JSONObject jsonObject = jsonarray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        int age = jsonObject.getInt("age");
                        builder.append(name + " " + age + "\n");
                    } textView.setText(builder);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public String getfile() throws IOException {
        InputStream inputStream=getAssets().open("data.json");
        int length=inputStream.available();
        byte buffer[]=new byte[length];
        inputStream.read(buffer);
        inputStream.close();
        String file=new String(buffer,"UTF-8");
        return  file;
    }





}
