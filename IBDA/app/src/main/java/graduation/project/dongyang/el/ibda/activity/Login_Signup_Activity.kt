package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginSignupBinding

class Login_Signup_Activity : AppCompatActivity() {
    val binding by lazy {
        ActivityLoginSignupBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            var intent = Intent(this@Login_Signup_Activity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}