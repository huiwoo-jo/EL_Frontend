package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoadingBinding

class LoadingActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityLoadingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.flip.flipInterval=1000
        binding.flip.startFlipping()

        binding.loadingBG.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}