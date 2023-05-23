package graduation.project.dongyang.el.ibda

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityItemClothBinding

class ItemCloth : AppCompatActivity() {
    private val binding by lazy {
        ActivityItemClothBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}