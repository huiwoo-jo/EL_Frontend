package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import graduation.project.dongyang.el.ibda.adapter.ClothShopAdapter
import graduation.project.dongyang.el.ibda.recycler.ClothesHardcodingItem
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityShopBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //의상 출력
        val clothList = arrayListOf(
            ClothesHardcodingItem("상의", R.drawable.top1, "상의1", 1000),
            ClothesHardcodingItem("상의", R.drawable.top2, "상의2", 2000),
            ClothesHardcodingItem("상의", R.drawable.top3, "상의3", 3000),
        )
        val allRv = binding.rvAll
        allRv.layoutManager = GridLayoutManager(this,1)
        allRv.setHasFixedSize(true)
        allRv.adapter = ClothShopAdapter(clothList)

        //버튼
        binding.btnBack.setOnClickListener(ButtonListener())
        binding.btnSearch.setOnClickListener(ButtonListener())
        binding.btnLike.setOnClickListener(ButtonListener())
    }
    // 버튼 클릭
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()
            when (v?.id) {
                // 의상 뷰
                R.id.btnBack -> {
                    intent= Intent(this@ShopActivity, MainClothesActivity::class.java)
                }
                R.id.btnSearch -> {
                    intent=Intent(this@ShopActivity, SearchActivty::class.java)
                }
                R.id.btnLike -> {
                    intent=Intent(this@ShopActivity, LikeActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }
    }
}