package mahmoud.bazioriazi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Result extends Activity {
    public String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/DANSTEVIS.OTF");

        TextView tv = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView tv2 = (TextView) findViewById(R.id.textView4);
        tv.setTypeface(custom_font);
        tv2.setTypeface(custom_font);
        textView2.setTypeface(custom_font);
        textView3.setTypeface(custom_font);

        Button btn_replay=(Button)findViewById(R.id.buttonreplay);
        Button btn_menu=(Button)findViewById(R.id.buttonmenu);

        btn_replay.setTypeface(custom_font);
        btn_menu.setTypeface(custom_font);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String Result = extras.getString("emtiaz");
            tv.setText(Result);

            String Best_Result = extras.getString("best_emtiaz");
            tv2.setText(Best_Result);

            level=extras.getString("level");
        }


    }

    public void replay(View view) {
        Intent replay_intent;
        switch (level){
            case "asan" :
                replay_intent  = new Intent(Result.this, playasan.class);
                startActivity(replay_intent);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);
                break;
            case "motevaset":
                replay_intent = new Intent(Result.this, Playmotevaset.class);
                startActivity(replay_intent);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);
                break;
            case  "sakht":
                replay_intent = new Intent(Result.this, playsakht.class);
                startActivity(replay_intent);
                overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);
                break;

        }

    }

    public void goto_menu(View v) {

        Intent exit_intent = new Intent(Result.this, MainActivity.class);
        startActivity(exit_intent);
        overridePendingTransition(R.anim.left, R.anim.abc_fade_out);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
//        super.onBackPressed();
        Intent intent = new Intent(Result.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left, R.anim.abc_fade_out);
        finish();
    }

}
