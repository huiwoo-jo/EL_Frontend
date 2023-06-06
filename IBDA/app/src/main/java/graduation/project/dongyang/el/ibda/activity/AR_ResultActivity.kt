package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.databinding.ActivityArResultBinding

class AR_ResultActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityArResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAR.setOnClickListener {
            var intent = Intent(this@AR_ResultActivity, AR_fittingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}