package kr.project.dongyang.el.ibda.ibda_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityLoginBinding
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}