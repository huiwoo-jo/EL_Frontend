package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainBG.setOnClickListener{
            val intent=Intent(this, Loading::class.java)
            startActivity(intent)
        }
        binding.createQR.setOnClickListener{
            val intent=Intent(this, CreateQR::class.java)
            startActivity(intent)
        }
        binding.readQR.setOnClickListener{
            val intent=Intent(this, ReadQR::class.java)
            startActivity(intent)
        }
    }
}
