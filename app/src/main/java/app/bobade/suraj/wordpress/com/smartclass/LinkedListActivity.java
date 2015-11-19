package app.bobade.suraj.wordpress.com.smartclass;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListActivity extends AppCompatActivity
{
    Spinner select_operation_spinner;
    EditText enter_element_editText;

    //LayoutInflater layoutInflater=LayoutInflater.from(this);
    //View node=layoutInflater.inflate(R.layout.llnode,null);
    LinearLayout result_box;
    TextView llindex,llelement,llnext,lladdress,llpaddress,start_address;

    View curr_node, prev_node;

    int operationChoice,input,index,address=1000;

    LinkedList linkedList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        result_box=(LinearLayout)findViewById(R.id.result_box_inner);

        start_address=(TextView)findViewById(R.id.start_address);

        enter_element_editText=(EditText)findViewById(R.id.enter_element_editText);

        linkedList=new LinkedList();

        ArrayAdapter spinnerAdapter=ArrayAdapter.createFromResource(this, R.array.linkedlistoperations, android.R.layout.simple_spinner_dropdown_item);
        select_operation_spinner=(Spinner)findViewById(R.id.select_operation_spinner);
        select_operation_spinner.setAdapter(spinnerAdapter);
        select_operation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                operationChoice = position;
                enter_element_editText.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                enter_element_editText.setEnabled(false);
            }
        });


        enter_element_editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        enter_element_editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    Toast.makeText(getBaseContext(),"onEditorAction",Toast.LENGTH_SHORT).show();
                    input=Integer.parseInt(enter_element_editText.getText().toString());
                    operate(input);
                    handled = true;
                }
                return handled;
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LinkedListActivity.class);
                finish();
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void operate(int input)
    {
        Toast.makeText(getBaseContext(),"Operate",Toast.LENGTH_SHORT).show();
        switch (operationChoice)
        {
            case 0:
                addAtEnd(input);
                break;
        }
    }

    public void addAtEnd(int input)
    {
        Toast.makeText(getBaseContext(),"AddAtEnd",Toast.LENGTH_SHORT).show();

        LayoutInflater layoutInflater=LayoutInflater.from(this);
        curr_node=layoutInflater.inflate(R.layout.llnode, null);

        while(true)
        {
            address = (int) (Math.random() * 10 + address+8);
            if(!linkedList.contains(address))
            {
                for(int i=0;i<8;i++)
                {
                    linkedList.add(address + i);
                }
                break;
            }
        }
        enter_element_editText.setText("");

        llindex=(TextView)curr_node.findViewById(R.id.llindex);
        llindex.setText(""+index);

        llelement=(TextView)curr_node.findViewById(R.id.llelement);
        llelement.setText(""+input);

        lladdress=(TextView)curr_node.findViewById(R.id.lladdress);
        lladdress.setText(""+address);


        if(index++==0)
        {
            start_address.setText(""+address);
            address+=4;
        }
        else
        {
            llpaddress=(TextView)prev_node.findViewById(R.id.llnext);
            llpaddress.setText(""+address);
        }

        result_box.addView(curr_node);
        prev_node=curr_node;
    }
}
