package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.databinding.ActivityReadQrBinding

class ReadQR : AppCompatActivity() {
    private val binding by lazy {
        ActivityReadQrBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainBtn.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}