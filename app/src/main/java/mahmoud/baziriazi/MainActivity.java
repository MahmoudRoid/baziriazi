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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.alexkolpa.fabtoolbar.FabToolbar;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;


public class MainActivity extends Activity {
    RelativeLayout mainRelativeLayout;
    Button asan,motevaset,sakht;
    Animation textclicked_animation,textmove_animation,textmove2_animation,textslide_animation,textslide2_animation;
    public  Handler handler;
    FabToolbar fabToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/DANSTEVIS.OTF");
        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));
        mainRelativeLayout =(RelativeLayout)findViewById(R.id.main_relativelayout);
        // Animations
        textclicked_animation = AnimationUtils.loadAnimation(this, R.anim.clickedview);
        textmove_animation = AnimationUtils.loadAnimation(this, R.anim.moveview);
        textmove2_animation = AnimationUtils.loadAnimation(this, R.anim.moveview_barax);
        textslide_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        textslide2_animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_bottom);

        asan=(Button)findViewById(R.id.asan);
        motevaset=(Button)findViewById(R.id.motevaset);
        sakht=(Button)findViewById(R.id.sakht);
        // set fonts
        asan.setTypeface(custom_font);
        motevaset.setTypeface(custom_font);
        sakht.setTypeface(custom_font);
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
                    asan.setTextColor(Color.parseColor("#4CAF50"));
                    asan.startAnimation(textclicked_animation);
                    sleep("asan");
                    asan.setTextSize(50);
                    break;
                case R.id.motevaset :
                    motevaset.startAnimation(textclicked_animation);
                    motevaset.setTextColor(Color.parseColor("#4CAF50"));
                    sleep("motevaset");
                    motevaset.setTextSize(50);
                    break;
                case R.id.sakht :
                    sakht.startAnimation(textclicked_animation);
                    sakht.setTextColor(Color.parseColor("#4CAF50"));
                    sleep("sakht");
                    sakht.setTextSize(50);
                    break;
                case R.id.main_relativelayout :
                    fabToolbar.hide();
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
    }}




