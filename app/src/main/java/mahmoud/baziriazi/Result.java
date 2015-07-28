package mahmoud.baziriazi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class Result extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView tv = (TextView) findViewById(R.id.textView1);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String Result = extras.getString("emtiaz");
            tv.setText(Result);
        }


    }

    public void replay(View view) {

        Intent replay_intent = new Intent(Result.this, playasan.class);
        startActivity(replay_intent);
    }

    public void goto_menu(View v) {

        Intent exit_intent = new Intent(Result.this, MainActivity.class);
        startActivity(exit_intent);

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent myintent = new Intent(Result.this, MainActivity.class);
        startActivity(myintent);
    }
}
