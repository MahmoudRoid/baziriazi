package mahmoud.baziriazi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.alexkolpa.fabtoolbar.FabToolbar;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;


public class MainActivity extends Activity {
    Button asan,motevaset,sakht;
    TextView bazi_txt,riazi_txt;
    ImageView sound_img_view;
    Animation textclicked_animation,textmove_animation,textmove2_animation,textslide_animation,textslide2_animation;
    public RelativeLayout main_relativeLayout,contact_us_relativelayout;
    public  Handler handler;
    FabToolbar fabToolbar;
    public static boolean sound=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_relativeLayout=(RelativeLayout)findViewById(R.id.main_relativelayout);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/DANSTEVIS.OTF");
        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));
        sound_img_view=(ImageView)findViewById(R.id.sound);
        if(sound)   sound_img_view.setImageResource(R.drawable.ic_voice_on);
        if(!sound)  sound_img_view.setImageResource(R.drawable.ic_voice_off);

        // Animations
        textclicked_animation = AnimationUtils.loadAnimation(this, R.anim.clickedview);
        textmove_animation = AnimationUtils.loadAnimation(this, R.anim.moveview);
        textmove2_animation = AnimationUtils.loadAnimation(this, R.anim.moveview_barax);
        textslide_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        textslide2_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);

        asan=(Button)findViewById(R.id.asan);
        motevaset=(Button)findViewById(R.id.motevaset);
        sakht=(Button)findViewById(R.id.sakht);

        bazi_txt=(TextView)findViewById(R.id.bazi_textview);

        // set fonts
        bazi_txt.setTypeface(custom_font);
        asan.setTypeface(custom_font);
        motevaset.setTypeface(custom_font);
        sakht.setTypeface(custom_font);
        //anim textviews;
        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(2000);
        bazi_txt.startAnimation(in);

        // anim buttons
        asan.startAnimation(textslide2_animation);
        motevaset.startAnimation(textslide2_animation);
        sakht.startAnimation(textslide_animation);
        // textviews clicked listener
        asan.setOnClickListener(clicked);
        motevaset.setOnClickListener(clicked);
        sakht.setOnClickListener(clicked);
    }
    View.OnClickListener clicked =new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.asan :
                    asan.startAnimation(textclicked_animation);
                    sleep("asan");
                    asan.setTextSize(35);
                    break;
                case R.id.motevaset :
                    motevaset.startAnimation(textclicked_animation);
                    sleep("motevaset");
                    motevaset.setTextSize(45);
                    break;
                case R.id.sakht :
                    sakht.startAnimation(textclicked_animation);
                    sleep("sakht");
                    sakht.setTextSize(50);
                    break;
            }
        }
    };


    private void sleep(final String level) {
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (level) {
                    case "asan":
                        playactivityasan();
                        break;
                    case "motevaset":
                        playactivitymotevaset();
                        break;
                    case "sakht":
                        playactivitysakht();
                        break;
                }
            }
        }, 800);
    }

    public void  playactivityasan(){
        // goto second activity
        Intent myintent = new Intent(MainActivity.this,playasan.class);
        startActivity(myintent);
        overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);
    }
    public void playactivitymotevaset(){
        // goto second activity
        Intent intent = new Intent(MainActivity.this,Playmotevaset.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);

    }
    public void  playactivitysakht(){
        // goto second activity
        Intent intent = new Intent(MainActivity.this,playsakht.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);

    }
    public void close_fabtoolbar(View view){
        fabToolbar.hide();
    }
    @Override
    public void onBackPressed() {
            fabToolbar.hide();
            SnackbarManager.show(
                    Snackbar.with(getApplicationContext()) // context
                            .text("میخوای بری ؟") // text to display
                            .actionLabel("آره ، خسته شدم")
                            .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)// action button label
                            .actionListener(new ActionClickListener() {
                                @Override
                                public void onActionClicked(Snackbar snackbar) {
                                    moveTaskToBack(true);
                                    finish();
                                    System.exit(0);
                                }
                            }) // action button's ActionClickListener
                    , this); // activity where it is displayed
        }

    public void contact_us(View view){
        // TODO goto contact_us activity
        Intent myIntent=new Intent(MainActivity.this,contactus_help.class);
        myIntent.putExtra("which_layout","contact_us");
        startActivity(myIntent);
        overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);
    }
    public void help(View view){
        // TODO goto help activity
        Intent myIntent=new Intent(MainActivity.this,contactus_help.class);
        myIntent.putExtra("which_layout","help");
        startActivity(myIntent);
        overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_fade_out);
    }
    public void sound_on_off(View view){
        if(sound){
            // TODO turn sound off
            sound=false;
            sound_img_view.setImageResource(R.drawable.ic_voice_off);
        }
        else if (!sound){
            // TODO turn sound  on
            sound=true;
            sound_img_view.setImageResource(R.drawable.ic_voice_on);
        }
    }

}




