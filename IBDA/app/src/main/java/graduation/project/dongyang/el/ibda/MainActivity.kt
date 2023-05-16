package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //start : onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMember.setOnClickListener{
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
            finish()
        }
        binding.btnNotMember.setOnClickListener{
            val cloth = Intent(this, MainClothes::class.java)
            cloth.putExtra("id", "비회원")
            startActivity(cloth)
            finish()
        }
    }
    //end onCreate

}
