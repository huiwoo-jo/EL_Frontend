package kr.project.dongyang.el.ibda.ibda_client.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kr.project.dongyang.el.ibda.ibda_client.R
import kr.project.dongyang.el.ibda.ibda_client.databinding.ActivityMainLoginActiviyBinding

class MainLoginActiviy : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainLoginActiviyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(ButtonListener())
        binding.btnKakao.setOnClickListener(ButtonListener())
        binding.btnEMail.setOnClickListener(ButtonListener())
    }

    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent(this@MainLoginActiviy, LoginActivity::class.java)

            when (v?.id) {
                R.id.btnLogin ->{
                        intent= Intent(this@MainLoginActiviy, LoginActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }
    }

}