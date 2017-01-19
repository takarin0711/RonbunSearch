package com.example.inohizatakayuki.ronbunsearch;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.lti.jawjaw.JAWJAW;
import edu.cmu.lti.jawjaw.pobj.POS;

import static com.example.inohizatakayuki.ronbunsearch.R.id.button1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editText;
    TextView textView;
    Button button;
    Button button2;
    ListView listView;
    String[] string;
    List<String> dataList;
    int itemNum=0;
    ArrayAdapter<String> adapter, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(button1);
        button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText1);
        textView = (TextView) findViewById(R.id.textView1);

        //SimpleDemo simpleDemo = new SimpleDemo();
        //simpleDemo.run("sell", POS.v);

        dataList = new ArrayList<String>();
        string = new String[]{
                "catch","have","take","make","acquire","obtain","receive","capture"
        };
        listView = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, dataList);
        adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, string);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                String item = (String) listView.getItemAtPosition(position);
                textView.setText(item);
                Uri uri = Uri.parse("https://scholar.google.co.jp/scholar?q=" + textView.getText().toString());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);

                /*Toast.makeText(getApplicationContext(), item + " clicked",
                        Toast.LENGTH_LONG).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String str = editText.getText().toString();
        textView.setText(str);
        /*String selectedText = textView.getText().subSequence(textView.getSelectionStart(), textView.getSelectionEnd()).toString();
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, selectedText);
        startActivity(intent);*/
        if(v==button) {
            Uri uri = Uri.parse("https://scholar.google.co.jp/scholar?q=" + textView.getText().toString());
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(i);
        }else if(v==button2){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() { // 0.5秒後に実行
                @Override
                public void run() {
                    if(itemNum<string.length&&adapter2.getItem(itemNum)!=null) {
                        adapter.add(adapter2.getItem(itemNum));
                        itemNum++;
                        adapter.notifyDataSetChanged();
                    }
                }
            }, 500);
            if(itemNum>=string.length){
                Toast.makeText(getApplicationContext(), "類義語はこれ以上抽出できません",
                        Toast.LENGTH_LONG).show();
            }

        }

    }

}
