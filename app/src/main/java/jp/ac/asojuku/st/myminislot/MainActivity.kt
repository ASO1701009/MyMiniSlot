
package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    var betMoney:Int=10;
    var haveMoney:Int = 0;

//AAAAAAAAA
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener{gamenseni(it)};
        btn_up.setOnClickListener { betUp() }
        btn_down.setOnClickListener{betDown()}
        btn_reset.setOnClickListener{coinreset()}
    }

    override fun onResume() {
        super.onResume()
        // 共有プリファレンス　読み込み
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        haveMoney = pref.getInt("HAVE_MONEY", 1000);
        txvhaveMoney.setText(haveMoney.toString());



    }

    private fun gamenseni(it: View?) {
        val intent = Intent(this,GameActivity::class.java)
        intent.putExtra("BET_MONEY",it?.id)

        startActivity<GameActivity>("BET_MONEY" to betMoney);
    }

    // 掛け金up
    fun betUp(){
        if(betMoney+10 <= haveMoney){
            betMoney+=10;

            txvBetMoney.setText(betMoney.toString());

        }

    }


    // 掛け金down
    fun betDown(){
        if(betMoney-10 > 0){
            betMoney-=10;

            txvBetMoney.setText(betMoney.toString());

        }

    }

    // 手持ちリセット
    fun coinreset(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor.clear().apply()
        memReset();
    }

    fun memReset(){
        haveMoney=1000;
        txvhaveMoney.setText(haveMoney.toString());
        betMoney=10;
        txvBetMoney.setText(betMoney.toString());

    }


}

