package kr.project.dongyang.el.ibda.ibda_admin.sc_clothes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityItemClothBinding

class ItemCloth : AppCompatActivity() {
    private val binding by lazy {
        ActivityItemClothBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}