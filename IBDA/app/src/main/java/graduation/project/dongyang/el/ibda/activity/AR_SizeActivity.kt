package graduation.project.dongyang.el.ibda.activity


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import kr.project.dongyang.EL.IBDA.databinding.ActivityArSizeBinding
import kotlin.math.roundToInt

class AR_SizeActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityArSizeBinding.inflate(layoutInflater)
    }
    // ViewBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler().postDelayed({
            mCountDown.start()
            binding.info.setTextSize(40F)
            binding.imgAR.visibility=View.VISIBLE
        },4000)
    }

    private val mCountDown: CountDownTimer = object : CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.info.setText("${(millisUntilFinished.toFloat() / 1000.0f).roundToInt()}")
        }

        override fun onFinish() {
            binding.info.setText("측정중...")
            Handler().postDelayed({
                var intent = Intent(this@AR_SizeActivity, AR_ResultActivity::class.java)
                startActivity(intent)
                finish()
            },2000)
        }
    }

}