package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginTestBinding

class LoginTest : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginTestBinding.inflate(layoutInflater)
    }

    //start : onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val receivedMessage = intent.getStringExtra("id")
        binding.printID.text = receivedMessage

        binding.back.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    //end onCreate
}