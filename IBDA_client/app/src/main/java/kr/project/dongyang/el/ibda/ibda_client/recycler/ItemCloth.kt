package graduation.project.dongyang.el.ibda.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.project.dongyang.el.ibda.ibda_client.databinding.ActivityItemClothBinding

class ItemCloth : AppCompatActivity() {
    private val binding by lazy {
        ActivityItemClothBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}