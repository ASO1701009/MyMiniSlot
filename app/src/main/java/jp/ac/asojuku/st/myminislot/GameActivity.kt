package jp.ac.asojuku.st.myminislot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class GameActivity : AppCompatActivity() {


    var slotArray:Array<Int> = arrayOf(-1,-1,-1);
    val SLOT_BANANA:Int = 0;
    val SLOT_BAR:Int = 1;
    val SLOT_BIGWIN:Int = 2;
    val SLOT_CHERRY:Int = 3;
    val SLOT_GRAPE:Int = 4;
    val SLOT_LEMON:Int = 5;
    val SLOT_ORANGE:Int = 6;
    val SLOT_SEVEN:Int = 7;
    val SLOT_WATERMEON:Int = 8;
    var betmoney =-1;
    var havemoney =-1;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val imageArray:Array<Int> = arrayOf(
                R.drawable.banana,
                R.drawable.bar,
                R.drawable.bigwin,
                R.drawable.cherry,
                R.drawable.grape,
                R.drawable.lemon,
                R.drawable.orange,
                R.drawable.seven,
                R.drawable.waltermelon
        );

        val a = (Math.random()*9).toInt();
        slotArray[0] = a;
        val b = (Math.random()*9).toInt();
        slotArray[1] = b;
        val c = (Math.random()*9).toInt();
        slotArray[2] = c;

        Go_btn.setOnClickListener{
            hatena1.setImageResource(imageArray[a]);
            hatena2.setImageResource(imageArray[b]);
            hatena3.setImageResource(imageArray[c]);
            hantei();
        }
    }

    override fun onResume() {
        super.onResume()

        btn_return.setOnClickListener{finish()}
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        havemoney = pref.getInt("HAVE_MONEY", 1000);
        temoti.setText(havemoney.toString());
        betmoney = intent.getIntExtra("BET_MONEY",-1);
        kakekin.setText(betmoney.toString());


    }
    fun hantei(){
        // 3つ揃い
        if( (slotArray[0]==slotArray[1])
        && (slotArray[1]==slotArray[2]  )){
            if(slotArray[0] == SLOT_SEVEN){
                havemoney+=(betmoney*20);
            }
            else if(slotArray[0] == SLOT_BIGWIN){
                havemoney+=(betmoney*10);
            }
            else if(slotArray[0] == SLOT_BAR){
                havemoney+=(betmoney*5);
            }
            else{
                havemoney+=(betmoney*2);
            }
            temoti.setText(havemoney.toString());
        }
        // スイカ1つで一倍
        else if((slotArray[0] == SLOT_WATERMEON)
                || (slotArray[1] == SLOT_WATERMEON)
                || (slotArray[2] == SLOT_WATERMEON))
                {
                havemoney+=(betmoney*1);
        }
        else{
            havemoney-=(betmoney);
            temoti.setText(havemoney.toString());
        }

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.putInt("HAVE_MONEY",havemoney)
                .apply()





    }









}
