package com.example.androidworld.ed;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class More extends AppCompatActivity {


    String[] SPINNER_LIST = {"Binary", "Decimal", "Heaxdecimal", "Octal"};
    private int flag,flag1;
    EditText input,convert;
    private AdView adView;
    Button copy;
    String value = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        getSupportActionBar().setTitle("Binary Converter");


        input = (EditText)findViewById(R.id.Input);
        copy = (Button)findViewById(R.id.copy);
        convert = (EditText)findViewById(R.id.Output);


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(convert.getText().toString());
                } else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Result Copied",convert.getText().toString());
                    clipboard.setPrimaryClip(clip);
                }
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,SPINNER_LIST);
        final MaterialBetterSpinner betterSpinner = (MaterialBetterSpinner)findViewById(R.id.spinner2);
        betterSpinner.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,SPINNER_LIST);
        final MaterialBetterSpinner betterSpinner1 = (MaterialBetterSpinner)findViewById(R.id.spinner);
        betterSpinner1.setAdapter(arrayAdapter1);


        betterSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(getApplicationContext(), "selected Item Name is " + betterSpinner.getText() +i , Toast.LENGTH_LONG).show();
                flag=i;
                process();
            }
        });

        betterSpinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "selected Item Name is " + betterSpinner1.getText() + i, Toast.LENGTH_LONG).show();
                flag1 = i;
                process();
            }
        });


    }



    public void process()
    {
        value = input.getText().toString();
        value.replaceAll(" ","");


        if(flag==0 && flag1 == 1)
        {
            try
            {

                StringBuilder output = new StringBuilder();

                output.append((int) Integer.parseInt(value, 2));


                convert.setText(output);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }

        }
        else if(flag==0 && flag1 == 2)
        {
            try
            {
                // value = input.getText().toString();
                int decimal = Integer.parseInt(value,2);
                String hex = Integer.toString(decimal,16);
                convert.setText(hex);

            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }
        else if(flag==0 && flag1 == 3)
        {
            try
            {
                //value  = input.getText().toString();
                int decimal = Integer.parseInt(value,2);
                String oct = Integer.toOctalString(decimal);
                convert.setText(oct);
            }catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }

        }
        else if(flag==1 && flag1 == 0)
        {
            try
            {
                //  value  = input.getText().toString();
                int decimal = Integer.parseInt(value);
                String bin = Integer.toBinaryString(decimal);
                convert.setText(bin);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }


        }
        else if(flag==1 && flag1 == 2)
        {

            try
            {
                // value  = input.getText().toString();
                int decimal = Integer.parseInt(value);
                String hex = Integer.toHexString(decimal);
                convert.setText(hex);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }
        else if(flag==1 && flag1 == 3)
        {
            try
            {
                // value  = input.getText().toString();
                int decimal = Integer.parseInt(value);
                String oct = Integer.toOctalString(decimal);
                convert.setText(oct);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }else if(flag==2 && flag1 == 0)
        {
            try
            {
                //  value  = input.getText().toString();
                int hex = Integer.parseInt(value,16);
                String bin = Integer.toBinaryString(hex);
                convert.setText(bin);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();

            }
        }
        else if(flag==2 && flag1 == 1)
        {
            try
            {
                //  value  = input.getText().toString();
                int hex = Integer.parseInt(value,16);
                String dec = Integer.toString(hex);
                convert.setText(dec);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }else if(flag==2 && flag1 == 3)
        {
            try
            {
                //  value  = input.getText().toString();
                int hex = Integer.parseInt(value,16);
                String oct = Integer.toOctalString(hex);
                convert.setText(oct);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }
        else if(flag==3 && flag1 == 0)
        {
            try
            {
                // value  = input.getText().toString();
                int oct = Integer.parseInt(value,8);
                String bin = Integer.toBinaryString(oct);
                convert.setText(bin);
            }
            catch(Exception e)
            {
                if(value.equals(null))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }
        else if(flag==3 && flag1 == 1)
        {
            try
            {
                // value  = input.getText().toString();
                int oct = Integer.parseInt(value,8);
                String bin = Integer.toString(oct);
                convert.setText(bin);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }else if(flag==3 && flag1 == 2)
        {
            try
            {
                //   value  = input.getText().toString();
                int oct = Integer.parseInt(value,8);
                String bin = Integer.toHexString(oct);
                convert.setText(bin);
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();
            }
        }else
        {
            try
            {
                if(flag==0 && flag1==0)
                {
                    int decimal = Integer.parseInt(value,2);
                    convert.setText(value);
                }
                else if(flag1==1 && flag==1)
                {
                    int decimal = Integer.parseInt(value);
                    convert.setText(value);
                }
                else if(flag1==2 && flag==2)
                {
                    int decimal = Integer.parseInt(value,16);
                    convert.setText(value);
                }
                else if(flag1==3 && flag==3)
                {
                    int decimal = Integer.parseInt(value);
                    convert.setText(value);
                }
            }
            catch(Exception e)
            {
                if(value.equals(""))
                {

                }
                else
                {
                    Toast.makeText(this,"Enter A Valid Input Number",Toast.LENGTH_LONG).show();

                }

            }
        }

    }
}
