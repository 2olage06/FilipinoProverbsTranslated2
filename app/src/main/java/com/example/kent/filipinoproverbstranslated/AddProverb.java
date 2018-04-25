package com.example.kent.filipinoproverbstranslated;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Kent on 24/04/2018.
 */

public class AddProverb extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_proverb);
    }

    public void onClick(View view)
    {
        EditText et1 = (EditText)findViewById(R.id.addet1),
                et2 = (EditText)findViewById(R.id.addet2),
                et3 = (EditText)findViewById(R.id.addet3);
    }

}

