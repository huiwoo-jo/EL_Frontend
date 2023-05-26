package kr.project.dongyang.el.ibda.ibda_admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityClothesAddBinding

class ClothesAddActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityClothesAddBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}