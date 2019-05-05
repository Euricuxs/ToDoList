package com.example.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private EditText Item;
    private Button btn;
    private ListView itemList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Item = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.btnadd);
        itemList = findViewById(R.id.item_list);

        items = FileHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);

        btn.setOnClickListener(this);
        itemList.setOnItemClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnadd:
            {
                String itemEntered = Item.getText().toString();
                adapter.add(itemEntered);
                Item.setText("");

                FileHelper.writeData(items, this);

                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        items.remove(position);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(items,this);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
