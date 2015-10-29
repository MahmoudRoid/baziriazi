package mahmoud.bazioriazi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class playasan extends Activity {
    String[] backcolors=new String[]{"#FFCDD2","#E040FB","#512DA8"
                                     ,"#673AB7","#1976D2","#2196F3","#0288D1","#03A9F4"
                                      ,"#0097A7","#00BCD4","#B2EBF2","#00796B","#009688"
                                      ,"#AFB42B","#CDDC39","#FFEB3B"
                                     ,"#FFA000","#FFC107","#F57C00","#FF9800","#FF5722"
                                     ,"#607D8B"};
// #536DFE for buttons
    TextView tvadad1;   // adade aval
    TextView tvadad2;	// adade 2vom
    TextView tvadad3;// adade 3vom
    TextView plus,equal;

    int adad;
    boolean bool;
    public int[] myrandomnumbers=new int[2];
    public int result;
    public int count=0;
    private ProgressBar customProgress;
    public int myProgress=0;
    public CountDownTimer mCountDownTimer;
    //    public int counter=1; // baraye inke az dafeye dovom b bad progress bar kar konad
    public int etmam=1; // baraye inke 2 bar methode payan() seda zade nashavad
    public LinearLayout linearlayoutforbuttons;
    public SharedPreferences pref;
    public SharedPreferences.Editor editor;
    public  MediaPlayer mp_asan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playasan);
        // start playing if sound is not playing
        mp_asan= MediaPlayer.create(this, R.raw.backgroundvoice);
        if(MainActivity.tmp_bool) {

            mp_asan.setLooping(true);
            mp_asan.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp_asan.start();
                }
            });
            mp_asan.start();
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/DANSTEVIS.OTF");

        tvadad1=(TextView) findViewById(R.id.textView1);
        tvadad2=(TextView) findViewById(R.id.textView2);
        tvadad3=(TextView) findViewById(R.id.textView3);
        plus=(TextView)findViewById(R.id.textviewplus);
        equal=(TextView)findViewById(R.id.textView5);
        // set font
        tvadad1.setTypeface(custom_font);
        tvadad2.setTypeface(custom_font);
        tvadad3.setTypeface(custom_font);
        plus.setTypeface(custom_font);
        equal.setTypeface(custom_font);

        customProgress =(ProgressBar)findViewById(R.id.customProgress);
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.relativelayout);
        linearlayoutforbuttons = (LinearLayout)findViewById(R.id.linearLayoutbuttons);
        pref=getApplicationContext().getSharedPreferences("MyPref_asan", MODE_PRIVATE);
        editor=pref.edit();
        editor.clear();

     //    taeene andazeye ertefa e button ha

//        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
//        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        int height=(int)((dpHeight*2)/5);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int)dpWidth, height);
//        linearlayoutforbuttons.setLayoutParams(lp);


        // baraye range backgroud
        int min = 0;
        int max = 21;
        Random backcolorrandom = new Random();
        int i1 = backcolorrandom.nextInt(max - min + 1) + min;
        rl.setBackgroundColor(Color.parseColor(backcolors[i1]));

        trueorfalse();
    }

    protected void trueorfalse(){

        bool=booleanrandom();
        if(bool==true) maketrue_ebarat();
        else           makefalse_ebarat();


    }


    // BUTTON TRUE
    public void btntrue(View view){


        try{
            mCountDownTimer.cancel();
            customProgress.setProgress(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        finally{

            if(result==myrandomnumbers[0]+myrandomnumbers[1]){

                count++;
                // moghayese behtarin result ba natijeye hale hazer
                int saved_best_result=pref.getInt("best_result_asan",0);
                if(count>=saved_best_result){
                    editor.putInt("best_result_asan",count);
                    editor.commit();
                }
                // continue
                trueorfalse();
                myprogressbar();
            }
            else {
                if(etmam==1){
                    payan();
                    etmam=2;
                }

            }
        }

    }

    // BUTTON FALSE
    public void btnfalse(View view){


        try{
            mCountDownTimer.cancel();
            customProgress.setProgress(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(result!=myrandomnumbers[0]+myrandomnumbers[1]){
                count++;
                int saved_best_result=pref.getInt("best_result_asan",0);
                if(count>=saved_best_result){
                    editor.putInt("best_result_asan",count);
                    editor.commit();
                }
                //continue
                trueorfalse();
                myprogressbar();
            }
            else{
                if(etmam==1){
                    payan();
                    etmam=2;
                }
            }
        }

    }

    public void payan(){

        // namayesh emtiaz va az sar giri
        try{
            if(MainActivity.sound){

                if(mp_asan.isPlaying()){
                    mp_asan.stop();
                    mp_asan.reset();
                    mp_asan.release();
                    mp_asan=null;
                }
            }
            int bestresult=pref.getInt("best_result_asan",0);
            String bestresult_string=String.valueOf(bestresult);

            String emtiaz=String.valueOf(count);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(500);
            // mire b activitie result va natijeha ra ha mibarad

            Intent result_intent = new Intent(getApplicationContext(),Result.class);
            Bundle extras = new Bundle();
            extras.putString("emtiaz",emtiaz);
            extras.putString("best_emtiaz", bestresult_string);
            extras.putString("level","asan");
            result_intent.putExtras(extras);
            startActivity(result_intent);
            overridePendingTransition(R.anim.left, R.anim.abc_fade_out);
            onDestroy();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void maketrue_ebarat(){

        for(int i=0;i<=1;i++){

            myrandomnumbers[i]=random();

        }
        result=myrandomnumbers[0]+myrandomnumbers[1];
        tvadad1.setText(String.valueOf(myrandomnumbers[0]));
        tvadad2.setText(String.valueOf(myrandomnumbers[1]));
        tvadad3.setText(String.valueOf(result));


    }

    public void makefalse_ebarat(){
        // ijade 2 adade random va zakhire dar arraye

        for(int i=0;i<=1;i++){

            myrandomnumbers[i]=random();

        }
        // set the int array to text views
        tvadad1.setText(String.valueOf(myrandomnumbers[0]));
        tvadad2.setText(String.valueOf(myrandomnumbers[1]));
        int min=myrandomnumbers[0]+myrandomnumbers[1];
        int tmp;

        Random random=new Random();
        tmp=random.nextInt(100);

        if(tmp<30){
            // adade result kamtar az jame adaad bashad
            int x=min-2;
            result=random.nextInt(min-x)+x;
        }
        else{
            // adade result bisgtar az jame adaad basha
            int x=min+3;
            result=random.nextInt(x-min)+min;
        }
        tvadad3.setText(""+result);
    }



    public int random(){

        Random random = new Random();
        adad = random.nextInt(10 - 1) + 1;
        return adad;
    }

    public boolean booleanrandom(){
        Random random = new Random();
        adad = random.nextInt(100);
        if(adad<50)
            return true;
        else
            return false;
    }

    public void myprogressbar(){
        myProgress=0;
        mCountDownTimer=new CountDownTimer(2000,90) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                myProgress++;
                customProgress.setProgress(myProgress);
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(), "finished", 1000).show();
                if(etmam==1) {
                    payan();
                    etmam=2;
                }
            }
        };
        mCountDownTimer.start();

    }

    @Override
    public void onBackPressed() {
            if(count==0){
                if(MainActivity.sound){
                    if(mp_asan.isPlaying()){
                        mp_asan.stop();
                        mp_asan.reset();
                        mp_asan.release();
                        mp_asan=null;
                    }
                }
                super.onBackPressed();
                onDestroy();
                Intent myintent=new Intent(playasan.this,MainActivity.class);
                startActivity(myintent);
                overridePendingTransition(R.anim.left, R.anim.abc_fade_out);
            }
    }
}
