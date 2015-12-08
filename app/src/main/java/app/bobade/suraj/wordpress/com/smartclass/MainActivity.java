package app.bobade.suraj.wordpress.com.smartclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        expListView.setGroupIndicator(null);
        expListView.setChildIndicator(null);
        expListView.setDividerHeight(2);
        expListView.setChildDivider(getResources().getDrawable(R.color.white));
        expListView.setDivider(getResources().getDrawable(R.color.veryFaintGray));

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                //Toast.makeText(getApplicationContext(),"Group Clicked " + listDataHeader.get(groupPosition), Toast.LENGTH_SHORT).show();
                switch (groupPosition)
                {
                    case 2:
                        Intent intent=new Intent(MainActivity.this,StackActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
        {
            @Override
            public void onGroupCollapse(int groupPosition)
            {
                //Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                // Auto-generated method stub
               Intent intent;
                if (groupPosition==0)
                {
                    switch (childPosition)
                    {
                        case 0:
                            intent=new Intent(getApplicationContext(),ArrayActivity.class);
                            startActivity(intent);
                            break;

                    }
                }
                if (groupPosition==1)
                {
                    switch (childPosition)
                    {
                        case 0:
                            intent=new Intent(getApplicationContext(),LinkedListActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
                if (groupPosition==3)
                {
                    switch (childPosition)
                    {
                        case 0:
                            intent=new Intent(getApplicationContext(),QueueActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
                return false;
            }
        });

    }


    private void prepareListData()
    {
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();

            // Adding child data
            listDataHeader = Arrays.asList(new String[]{"Array", "Linked List", "Stack", "Queue", "Tree", "Graph"});

            List<String> ArrayL = new ArrayList<String>();
            ArrayL = Arrays.asList(new String[]{"One Dimensional Array", "Two Dimensional Array", "Three Dimensional Array"});
            List<String> LinkedListL = new ArrayList<String>();
            LinkedListL = Arrays.asList(new String[]{"Singly Linked List", "Doubly Linked List", "Circular Linked List"});
            List<String> QueueL = new ArrayList<String>();
            QueueL = Arrays.asList(new String[]{"Simple Queue", "Circular Queue", "Priority Queue", "Doubly Ended Queue"});
            List<String> TreeL = new ArrayList<String>();
            TreeL = Arrays.asList(new String[]{"Binary Tree", "Binary Search Tree", "M-way Tree", "Expression Tree"});
            List<String> Empty = new ArrayList<String>();
            Empty=Arrays.asList(new String[]{});

            listDataChild.put(listDataHeader.get(0),ArrayL);
            listDataChild.put(listDataHeader.get(1),LinkedListL);
            listDataChild.put(listDataHeader.get(2),Empty);
            listDataChild.put(listDataHeader.get(3),QueueL);
            listDataChild.put(listDataHeader.get(4),TreeL);
            listDataChild.put(listDataHeader.get(5),Empty);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help)
        {
            return true;
        }
        if(id == R.id.action_about)
        {
            intent =new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
