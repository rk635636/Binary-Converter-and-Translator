package com.example.androidworld.ed;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.*;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText write,decode;
    Button de,decodebtn,copy1,copy2;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    String result ="";
    String add = "";
    int WHICH_EVENT_INT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        decode = (EditText) findViewById(R.id.decode);
        write = (EditText)findViewById(R.id.write);

        de  = (Button)findViewById(R.id.convert);
        decodebtn = (Button)findViewById(R.id.decodebtn);
        copy1 = (Button)findViewById(R.id.copy);
        copy2 = (Button)findViewById(R.id.copy1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Binary Translator");

        copy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                text = decode.getText().toString();
                myClip = ClipData.newPlainText("text",text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(MainActivity.this,"Text Copied",Toast.LENGTH_SHORT).show();
            }
        });

        copy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                text = write.getText().toString();
                myClip = ClipData.newPlainText("text",text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(MainActivity.this,"Text Copied",Toast.LENGTH_SHORT).show();
            }
        });
        //String passedArg = getIntent().getExtras().getString("arg");
        Intent received_int=getIntent();

        WHICH_EVENT_INT=received_int.getIntExtra("arg",0);
        switch (WHICH_EVENT_INT)
        {
            case 1:

                de.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String str = write.getText().toString();

                        char[] messChar = str.toCharArray();
                        String result = "";
                        String tmpStr;
                        int tmpInt;


                        for (int i = 0; i < messChar.length; i++) {
                            tmpStr = Integer.toBinaryString(messChar[i]);
                            tmpInt = tmpStr.length();
                            if(tmpInt !=8 ) {
                                tmpInt = 8 - tmpInt;
                                if (tmpInt == 8) {
                                    result += tmpStr;
                                } else if (tmpInt > 0) {
                                    for (int j = 0; j < tmpInt; j++) {
                                        result += " ";
                                    }
                                    result += tmpStr;
                                } else {
                                    System.err.println("argument 'bits' is too small");
                                }
                            } else {
                                result += tmpStr;
                            }
                            result += " "; // separator
                        }


                        decode.setText(result);


                    }
                });


                decodebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String binary = decode.getText().toString();
                        StringTokenizer st = new StringTokenizer(binary," ");
                        while(st.hasMoreTokens()){
                            int ascii = Integer.parseInt(st.nextToken(), 2);
                            char character = (char)ascii;
                            write.setText(write.getText() + "" + character);

                        }
                    }
                });


                break;

            case 2:
                de.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String str = write.getText().toString();
                        String result = "";
                        String tmpStr;
                        int tmpInt;
                        char[] messChar = str.toCharArray();

                        for (int i = 0; i < messChar.length; i++) {
                            tmpStr = Integer.toHexString(messChar[i]);
                            tmpInt = tmpStr.length();
                            if(tmpInt != 2) {
                                tmpInt = 2 - tmpInt;
                                if (tmpInt == 2) {
                                    result += tmpStr;
                                } else if (tmpInt > 0) {
                                    for (int j = 0; j < tmpInt; j++) {
                                        result += "";
                                    }
                                    result += tmpStr;
                                } else {
                                    System.err.println("argument 'bits' is too small");
                                }
                            } else {
                                result += tmpStr;
                            }
                            result += ""; // separator
                        }


                        decode.setText(result);



                    }
                });

                decodebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String hex = decode.getText().toString();
                        StringBuilder output = new StringBuilder();
                        for (int i = 0; i < hex.length(); i+=2) {
                            String str1 = hex.substring(i, i+2);
                            output.append((char)Integer.parseInt(str1, 16));
                        }

                        write.setText(output);
                    }
                });

                break;



            case 4:
                de.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String text = write.getText().toString();
// Sending side
                        byte[] data = null;
                        try {
                            data = text.getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        }
                        String base64 = Base64.encodeToString(data, Base64.DEFAULT);

                        decode.setText(base64);


                    }
                });


                decodebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Receiving side
                        String text = decode.getText().toString();
                        byte[] data1 = Base64.decode(text, Base64.DEFAULT);

                        String text1 = null;
                        try {
                            text1 = new String(data1, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        write.setText(text1);

                    }
                });
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, Welcome_Screen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            case R.id.share:
                Intent i=new Intent(android.content.Intent.ACTION_SEND);

                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "extra text that you want to put https://play.google.com/store/apps/details?id=com.example.androidworld.ed");
                startActivity(Intent.createChooser(i,"Share via"));

                return true;
            case R.id.clear:
                // check for updates action
                write.setText("");
                decode.setText("");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
