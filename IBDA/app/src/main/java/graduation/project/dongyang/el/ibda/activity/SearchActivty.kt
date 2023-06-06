package graduation.project.dongyang.el.ibda.activity

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import graduation.project.dongyang.el.ibda.adapter.ClothHardCodingAdapter
import graduation.project.dongyang.el.ibda.recycler.ClothesHardcodingItem
import kotlinx.coroutines.NonCancellable.start
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivitySearchActivtyBinding

class SearchActivty : AppCompatActivity() {
    private val binding by lazy {
        ActivitySearchActivtyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        //버튼
        binding.btnBack.setOnClickListener(ButtonListener())
        // 하드 코딩 출력
        val clothPantsList = arrayListOf(
            ClothesHardcodingItem("하의", R.drawable.pants1, "하의1", 1000),
            ClothesHardcodingItem("하의", R.drawable.pants2, "하의2", 2000),
            ClothesHardcodingItem("하의", R.drawable.pants3, "하의3", 3000),
        )
        val recentRv = binding.rvRecent
        recentRv.layoutManager = GridLayoutManager(this,3)
        recentRv.setHasFixedSize(true)
        recentRv.adapter = ClothHardCodingAdapter(clothPantsList)
    }

    // 버튼 클릭
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()
            when (v?.id) {
                // 의상 뷰
                R.id.btnBack -> {
                    intent = Intent(this@SearchActivty, MainClothesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}