package app.bobade.suraj.wordpress.com.smartclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class LinkedListActivity extends AppCompatActivity
{
    Spinner select_operation_spinner;
    EditText enter_element_editText,enter_pos_editText;

    //LayoutInflater layoutInflater=LayoutInflater.from(this);
    //View llnode=layoutInflater.inflate(R.layout.llnode,null);
    LinearLayout result_box;
    TextView llindex,llelement,llnext,lladdress,llpaddress,start_address, readTheory;

    View curr_node, prev_node;

    int operationChoice,input,index=1,address=1000,posindex;

    LinkedList linkedList;

    LinearLayout result_box_inner=null;

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

        enter_pos_editText=(EditText)findViewById(R.id.enter_pos_editText);
        //enter_pos_editText.setEnabled(false);

        linkedList=new LinkedList();

        ArrayAdapter spinnerAdapter=ArrayAdapter.createFromResource(this, R.array.linkedlistoperations, android.R.layout.simple_spinner_dropdown_item);
        select_operation_spinner=(Spinner)findViewById(R.id.select_operation_spinner);
        select_operation_spinner.setAdapter(spinnerAdapter);
        select_operation_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                operationChoice = position;
                if(position!=4)
                    enter_element_editText.setEnabled(true);
                else {
                    enter_pos_editText.setEnabled(true);
                    enter_element_editText.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                enter_element_editText.setEnabled(false);
            }
        });


        enter_element_editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });
        enter_element_editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Toast.makeText(getBaseContext(), "onEditorAction", Toast.LENGTH_SHORT).show();
                    //operate(input,posindex);
                    handled = true;
                    if (select_operation_spinner.getSelectedItemPosition() == 2 || select_operation_spinner.getSelectedItemPosition() == 4) {
                        if (select_operation_spinner.getSelectedItemPosition() == 2) {

                            try {
                                input = Integer.parseInt(enter_element_editText.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getBaseContext(), "Enter Element!!!", Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        }
                        enter_element_editText.clearFocus();
                        enter_element_editText.setEnabled(false);
                        enter_pos_editText.setEnabled(true);
                        enter_pos_editText.setFocusable(true);
                        enter_pos_editText.setFocusableInTouchMode(true);
                        //enter_element_editText.setNextFocusDownId(enter_element_editText.getId());
                        //enter_pos_editText.requestFocus();
                        if (enter_pos_editText.requestFocus()) {
                            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                        }
                        //Toast.makeText(getBaseContext(), "" + select_operation_spinner.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            input = Integer.parseInt(enter_element_editText.getText().toString());
                            operate(input, 0);
                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), "Enter Element!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                return handled;
            }
        });

        enter_pos_editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        enter_pos_editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                boolean handled = false;
                enter_pos_editText.requestFocus();
                if (actionId == EditorInfo.IME_ACTION_DONE)
                {
                    posindex=Integer.parseInt(enter_pos_editText.getText().toString());
                    if (posindex<1||posindex>index)
                    {
                        //Toast.makeText(getBaseContext(),"Enter valid position",Toast.LENGTH_SHORT).show();
                        //onEditorAction(v, actionId, event);
                        handled=false;
                    }
                    else
                    {
                        enter_pos_editText.clearFocus();
                        enter_pos_editText.setEnabled(false);
                        enter_element_editText.setEnabled(true);
                        enter_element_editText.requestFocus();
                        if(operationChoice!=4)
                            operate(input, posindex);
                        else
                            operate(0,posindex);
                        handled = true;
                    }
                }
                return handled;
            }
        });

        readTheory=(TextView)findViewById(R.id.read_theory_text);
        readTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LinkedListTheoryActivity.class);
                startActivity(intent);
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

    public void operate(int input,int pos)
    {
        //Toast.makeText(getBaseContext(),"Operate",Toast.LENGTH_SHORT).show();
        switch (operationChoice)
        {
            case 0:
                addAtEnd(input);
                break;
            case 1:
                addAtBegin(input);
                break;
            case 2:
                addAtPos(input,pos);
                break;
            case 3:
                deleteItem(input);
                break;
            case 4:
                deleteItemAt(pos);
                break;
            case 5:
                searchItem(input);
        }
    }

    public void addAtEnd(int input)
    {
       // Toast.makeText(getBaseContext(),"AddAtEnd",Toast.LENGTH_SHORT).show();
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


        if(index++==1)
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

    public void addAtBegin(int input)
    {
        //Toast.makeText(getBaseContext(),"AddAtBegin",Toast.LENGTH_SHORT).show();

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

        llnext=(TextView)curr_node.findViewById(R.id.llnext);
        if(index>1)
            llnext.setText(start_address.getText());

        llindex=(TextView)curr_node.findViewById(R.id.llindex);
        llindex.setText("0");

        llelement=(TextView)curr_node.findViewById(R.id.llelement);
        llelement.setText(""+input);

        lladdress=(TextView)curr_node.findViewById(R.id.lladdress);
        lladdress.setText("" + address);

        //if(index++==0)
       // {
            start_address.setText(""+address);
            address+=4;
        //}
        /*else
        {
            llpaddress=(TextView)prev_node.findViewById(R.id.llnext);
            llpaddress.setText(""+address);
        }*/

        result_box.addView(curr_node, 1);
        //prev_node=curr_node;

        result_box_inner=(LinearLayout)findViewById(R.id.result_box_inner);
        for(int i=1;i<=index;i++)
        {
            curr_node=result_box_inner.getChildAt(i);
            llindex=(TextView)curr_node.findViewById(R.id.llindex);
            llindex.setText(""+i);
        }
        index++;
    }

    public void addAtPos(int input,int pos)
    {
        //Toast.makeText(getBaseContext(),"AddAtPos",Toast.LENGTH_SHORT).show();
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        curr_node=layoutInflater.inflate(R.layout.llnode, null);

        result_box_inner=(LinearLayout)findViewById(R.id.result_box_inner);
        if(pos!=1)
            prev_node=result_box_inner.getChildAt(pos-1);
        else if (pos==index)
        {
           // Toast.makeText(getBaseContext(),"ADD_AT_END_CALLED",Toast.LENGTH_SHORT).show();
            addAtEnd(input);
            return;
        }
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
        enter_element_editText.requestFocus();

        llelement=(TextView)curr_node.findViewById(R.id.llelement);
        llelement.setText(""+input);

        lladdress=(TextView)curr_node.findViewById(R.id.lladdress);
        lladdress.setText(""+address);

        llnext=(TextView)curr_node.findViewById(R.id.llnext);
        if(pos==1)
            llnext.setText(start_address.getText().toString());
        else
            llnext.setText(((TextView) prev_node.findViewById(R.id.llnext)).getText().toString());

        if(pos!=1)
        {
            llpaddress = (TextView) prev_node.findViewById(R.id.llnext);
            llpaddress.setText("" + address);
        }
        else if(pos==1)
        {
            start_address.setText(""+address);
        }

        result_box_inner.addView(curr_node,pos);

        for(int i=pos;i<index+1;i++)
        {
            curr_node=result_box_inner.getChildAt(i);
            llindex=(TextView)curr_node.findViewById(R.id.llindex);
            llindex.setText(""+i);
        }
        index++;
    }

    public void searchItem(int key)
    {

        int foundIndex=search(key);
        if(foundIndex==0)
            Toast.makeText(getBaseContext(),"Not Found!!",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getBaseContext(),"Found At "+foundIndex,Toast.LENGTH_SHORT).show();
    }

    public void deleteItem(int input)
    {
        int foundIndex=search(input);
        result_box_inner=(LinearLayout)findViewById(R.id.result_box_inner);
        if(foundIndex==0)
        {
            Toast.makeText(getBaseContext(),"Element Not Found!!",Toast.LENGTH_SHORT).show();
            return;
        }
        curr_node=result_box_inner.getChildAt(foundIndex);
        if(foundIndex!=1)
        {
            prev_node = result_box_inner.getChildAt(foundIndex-1);
            llpaddress = (TextView) prev_node.findViewById(R.id.llnext);
            llpaddress.setText(((TextView) curr_node.findViewById(R.id.llnext)).getText().toString());
        }
        else
            start_address.setText(((TextView)curr_node.findViewById(R.id.llnext)).getText().toString());
        result_box_inner.removeViewAt(foundIndex);
        index--;
        for(int i=foundIndex;i<index;i++)
        {
            curr_node=result_box_inner.getChildAt(i);
            try{
                llindex=(TextView)curr_node.findViewById(R.id.llindex);
            }
            catch (Exception e)
            {
            }
            llindex.setText(""+i);
        }
    }
    public void deleteItemAt(int pos)
    {
        int foundIndex=pos;
        if(foundIndex>=index)
        {
            Toast.makeText(getBaseContext(),"Element Not Found!!",Toast.LENGTH_SHORT).show();
            return;
        }
        result_box_inner=(LinearLayout)findViewById(R.id.result_box_inner);
        curr_node=result_box_inner.getChildAt(foundIndex);
        if(foundIndex!=1)
        {
            prev_node = result_box_inner.getChildAt(foundIndex-1);
            llpaddress = (TextView) prev_node.findViewById(R.id.llnext);
            llpaddress.setText(((TextView)curr_node.findViewById(R.id.llnext)).getText().toString());
        }
        else
            start_address.setText(((TextView) curr_node.findViewById(R.id.llnext)).getText().toString());
        result_box_inner.removeViewAt(foundIndex);
        index--;
        for(int i=1;i<index;i++)
        {
            curr_node=result_box_inner.getChildAt(i);
            llindex=(TextView)curr_node.findViewById(R.id.llindex);
            llindex.setText(""+i);
        }
    }

    public int search(int key)
    {
        int position=0;
        result_box_inner=(LinearLayout)findViewById(R.id.result_box_inner);
        for(int i=1;i<index;i++)
        {
            //curr_node=null;
            try{
                curr_node=result_box_inner.getChildAt(i);
            }
            catch (Exception e)
            {
                Toast.makeText(getBaseContext(),""+e+" at "+i,Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            if(((TextView)curr_node.findViewById(R.id.llelement)).getText().toString().equals(""+key))
            {
                position=i;
                break;
            }
        }
        return  position;
    }
}