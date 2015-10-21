package mahmoud.baziriazi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class contactus_help extends Activity{

    TextView matn_tamas,email,matn_help,return1,return2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myintet=getIntent();
        Bundle myBundle=myintet.getExtras();
        if(myBundle!=null){
            String layout= (String) myBundle.get("which_layout");
            switch (layout){
                case "contact_us":
                    setContentView(R.layout.contact_us_layout);
                    break;
                case "help":
                    setContentView(R.layout.help_layout);
                    break;
            }
        }
        //
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/DANSTEVIS.OTF");
        try {
            matn_tamas=(TextView)findViewById(R.id.matn_tamas);
            email=(TextView)findViewById(R.id.email);
            return1=(TextView)findViewById(R.id.return1);
            // set fonts
            matn_tamas.setTypeface(custom_font);
            email.setTypeface(custom_font);
            return1.setTypeface(custom_font);

            return1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            matn_help=(TextView)findViewById(R.id.matn_help);
            return2=(TextView)findViewById(R.id.return2);
            //set fonts
            matn_help.setTypeface(custom_font);
            return2.setTypeface(custom_font);

            return2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onDestroy();
        Intent myintent=new Intent(contactus_help.this,MainActivity.class);
        startActivity(myintent);
        overridePendingTransition(R.anim.left, R.anim.abc_fade_out);
    }
}
