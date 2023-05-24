package graduation.project.dongyang.el.ibda

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //start : onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMember.setOnClickListener(ButtonListener())
        binding.btnNotMember.setOnClickListener(ButtonListener())

        Handler().postDelayed({
            val loading = Intent(this, LoadingActivity::class.java)
            startActivity(loading)
        },300000)

    }
    //end onCreate

    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()

            when (v?.id) {
                R.id.btnMember->{
                    intent=Intent(this@MainActivity, LoginActivity::class.java)
                }
                R.id.btnNotMember-> {
                    intent = Intent(this@MainActivity, MainClothes::class.java)
                    intent.putExtra("id", "비회원")
                }
            }
            startActivity(intent)
            finish()
        }
    }


}
