package app.bobade.suraj.wordpress.com.smartclass;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Array1DTheoryActivity extends AppCompatActivity {

    View view_card;
    TextView textview_card_title, textview_card_text, textview_card_text_description;
    int i;
    LinearLayout linearlayout_theory;
    String[] string_card_title, string_card_text, string_card_text_dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_1d_theory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LayoutInflater layoutInflater=LayoutInflater.from(this);
        linearlayout_theory=(LinearLayout)findViewById(R.id.activity_array_1d_theory_l);
        string_card_title = getResources().getStringArray(R.array.array_1d_title);
        string_card_text = getResources().getStringArray(R.array.array_1d_text);
        string_card_text_dis = getResources().getStringArray(R.array.array_1d_text_dis);

        i=1;
        while(i<7)
        {
            view_card=layoutInflater.inflate(R.layout.theory_card, null);

            textview_card_title=(TextView)view_card.findViewById(R.id.card_title);
            textview_card_title.setText(string_card_title[i-1]);
            textview_card_text=(TextView)view_card.findViewById(R.id.card_text);
            textview_card_text.setText(string_card_text[i-1]);
            textview_card_text_description=(TextView)view_card.findViewById(R.id.card_text_description);
            textview_card_text_description.setText(string_card_text_dis[i-1]);

            linearlayout_theory.addView(view_card,i-1);
            i++;
        }
    }
}
