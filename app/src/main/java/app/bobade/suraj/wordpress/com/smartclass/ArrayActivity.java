package app.bobade.suraj.wordpress.com.smartclass;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ArrayActivity extends AppCompatActivity
{

    EditText size, element;
    int sizeA, i , rando;
    int []arr;
    Button sb, eb;
    Editable str;
    String idstring;
    TextView nodetext, readTheory;
    Resources res;
    Random rand;
    LinearLayout nodeL, Linear;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        size= (EditText)findViewById(R.id.editText);
        element= (EditText)findViewById(R.id.editText2);
        sb= (Button)findViewById(R.id.sizeb);
        eb= (Button)findViewById(R.id.button);
        res= getResources();
        rand= new Random();

        element.setEnabled(false);
        eb.setEnabled(false);
        sb.setEnabled(false);

        size.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                sb.setEnabled(true);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        i=0;
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size.getText().toString().equals(""))
                {
                    Toast.makeText(getBaseContext(),"Enter Size!",Toast.LENGTH_SHORT).show();
                }
                sizeA = Integer.parseInt(size.getText().toString());
                if (sizeA > 10 || sizeA<0)
                {
                    Toast.makeText(getBaseContext(), "Enter size between 1  to 10 !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    arr = new int[sizeA];
                    size.setEnabled(false);
                    sb.setEnabled(false);
                    element.setEnabled(true);
                    eb.setEnabled(true);
                    nodetext=(TextView)findViewById(R.id.size);
                    nodetext.setVisibility(TextView.INVISIBLE);
                    size=(EditText)findViewById(R.id.editText);
                    size.setVisibility(EditText.INVISIBLE);
                    sb.setVisibility(Button.INVISIBLE);
                }
            }
        });

        rando= rand.nextInt(50)+1000;

        eb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < sizeA) {
                    arr[i] = Integer.parseInt(element.getText().toString());

                    idstring = "ai" + i;
                    int id = res.getIdentifier(idstring, "id", getBaseContext().getPackageName());
                    nodetext = (TextView) findViewById(id);
                    nodetext.setText("" + i);

                    idstring = "ae" + i;
                    id = res.getIdentifier(idstring, "id", getBaseContext().getPackageName());
                    nodetext = (TextView) findViewById(id);
                    nodetext.setText("" + arr[i]);

                    idstring = "aa" + i;
                    id = res.getIdentifier(idstring, "id", getBaseContext().getPackageName());
                    nodetext = (TextView) findViewById(id);
                    nodetext.setText("" + (rando));
                    rando += 4;
                    element.getText().clear();

                    idstring = "node" + i;
                    id = res.getIdentifier(idstring, "id", getBaseContext().getPackageName());
                    nodeL = (LinearLayout) findViewById(id);
                    nodeL.setBackgroundColor(Color.WHITE);
                    i++;
                } else {
                    Toast.makeText(getBaseContext(), "Array Fetched", Toast.LENGTH_SHORT).show();
                    eb.setEnabled(false);
                    element.setEnabled(false);
                }
            }
        });


        img= (ImageView)findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Array Theory image Clicked", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(getBaseContext(),ScrollingActivity.class);
                startActivity(in);
            }
        });

        readTheory= (TextView)findViewById(R.id.read_theory);
        readTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Array Theory text Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ArrayActivity.this, ArrayActivity.class);
                finish();
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
