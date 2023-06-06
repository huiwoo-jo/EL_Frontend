package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import graduation.project.dongyang.el.ibda.adapter.ClothHardCodingAdapter
import graduation.project.dongyang.el.ibda.recycler.ClothesHardcodingItem
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityAiRecommendBinding
import kr.project.dongyang.EL.IBDA.databinding.ActivityArFittingactivityBinding

class AI_RecommendActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityAiRecommendBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.


        //버튼 클릭
        binding.btnAll.setButtonDrawable(R.drawable.selector_round_blue_button)
        binding.btnTops.setButtonDrawable(R.drawable.selector_round_blue_button)
        binding.btnPants.setButtonDrawable(R.drawable.selector_round_blue_button)
        binding.btnDress.setButtonDrawable(R.drawable.selector_round_blue_button)
        binding.btnEtc.setButtonDrawable(R.drawable.selector_round_blue_button)

        binding.btnAll.setOnClickListener(ButtonListener())
        binding.btnTops.setOnClickListener(ButtonListener())
        binding.btnPants.setOnClickListener(ButtonListener())
        binding.btnDress.setOnClickListener(ButtonListener())
        binding.btnEtc.setOnClickListener(ButtonListener())

        binding.btnBack.setOnClickListener(ButtonListener())
        binding.btnSearch.setOnClickListener(ButtonListener())
        binding.btnLike.setOnClickListener(ButtonListener())
        binding.btnShop.setOnClickListener(ButtonListener())


        //정렬 버튼
        binding.btnSort.setButtonDrawable(R.drawable.ic_baseline_swap_vert_24_blue)
        binding.textSort.setOnClickListener { binding.btnSort.toggle() }
        binding.btnSort.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {binding.textSort.setText("최신순")}
            else { binding.textSort.setText("인기순")}
        }

        // ! 수정 필요 - 의상 출력 : 하드코딩
        //의상 출력

        val clothAllList = arrayListOf(
            ClothesHardcodingItem("상의", R.drawable.top1, "상의1", 1000),
            ClothesHardcodingItem("상의", R.drawable.top2, "상의2", 2000),
            ClothesHardcodingItem("상의", R.drawable.top3, "상의3", 3000),
            ClothesHardcodingItem("하의", R.drawable.pants1, "하의1", 1000),
            ClothesHardcodingItem("하의", R.drawable.pants2, "하의2", 2000),
            ClothesHardcodingItem("하의", R.drawable.pants3, "하의3", 3000),
            ClothesHardcodingItem("드레스", R.drawable.dress1, "드레스1", 1000),
        )
        val allRv = binding.rvAll
        allRv.layoutManager = GridLayoutManager(this,3)
        allRv.setHasFixedSize(true)
        allRv.adapter = ClothHardCodingAdapter(clothAllList)

        val clothTopsList = arrayListOf(
            ClothesHardcodingItem("상의", R.drawable.top1, "상의1", 1000),
            ClothesHardcodingItem("상의", R.drawable.top2, "상의2", 2000),
            ClothesHardcodingItem("상의", R.drawable.top3, "상의3", 3000),
        )
        val topRv = binding.rvTop
        topRv.layoutManager = GridLayoutManager(this,3)
        topRv.setHasFixedSize(true)
        topRv.adapter = ClothHardCodingAdapter(clothTopsList)

        val clothPantsList = arrayListOf(
            ClothesHardcodingItem("하의", R.drawable.pants1, "하의1", 1000),
            ClothesHardcodingItem("하의", R.drawable.pants2, "하의2", 2000),
            ClothesHardcodingItem("하의", R.drawable.pants3, "하의3", 3000),
        )
        val pantsRv = binding.rvPant
        pantsRv.layoutManager = GridLayoutManager(this,3)
        pantsRv.setHasFixedSize(true)
        pantsRv.adapter = ClothHardCodingAdapter(clothPantsList)

        val clothDressList = arrayListOf(
            ClothesHardcodingItem("드레스", R.drawable.dress1, "드레스1", 1000),
        )
        val dressRv = binding.rvDress
        dressRv.layoutManager = GridLayoutManager(this,3)
        dressRv.setHasFixedSize(true)
        dressRv.adapter = ClothHardCodingAdapter(clothDressList)

    }


    // 버튼 클릭
    inner class ButtonListener(): View.OnClickListener {
        override fun onClick(v: View?) {
            val userID = intent.getStringExtra("id")
            var intent = Intent()
            binding.rvAll.visibility=View.GONE
            binding.rvTop.visibility=View.GONE
            binding.rvPant.visibility=View.GONE
            binding.rvDress.visibility=View.GONE
            binding.rvEtc.visibility=View.GONE

            when (v?.id) {
                R.id.btnBack -> {
                    //검색
                    intent= Intent(this@AI_RecommendActivity, MainClothesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnSearch -> {
                    //검색
                    intent= Intent(this@AI_RecommendActivity, SearchActivty::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnLike -> {
                    //관심
                    intent= Intent(this@AI_RecommendActivity, LikeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnShop -> {
                    // 장바구니
                    intent= Intent(this@AI_RecommendActivity, ShopActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                // 의상 뷰
                R.id.btnAll -> {
                    // 상의 출력
                    binding.rvAll.visibility=View.VISIBLE
                }
                R.id.btnTops -> {
                    // 상의 출력
                    binding.rvTop.visibility=View.VISIBLE
                }
                R.id.btnPants -> {
                    // 하의 출력
                    binding.rvPant.visibility=View.VISIBLE
                }
                R.id.btnDress -> {
                    // 드레스 출력
                    binding.rvDress.visibility=View.VISIBLE
                }
                R.id.btnEtc -> {
                    // 잡화 출력
                    binding.rvEtc.visibility=View.VISIBLE
                }
                R.id.btnMainAI -> {
                    if(userID=="비회원") {
                        val mDialogView = LayoutInflater.from(this@AI_RecommendActivity)
                            .inflate(R.layout.activity_popup_login, null)
                        val mBuilder = AlertDialog.Builder(this@AI_RecommendActivity)
                            .setView(mDialogView)
                        val mAlertDialog = mBuilder.show()
                        val okButton = mDialogView.findViewById<Button>(R.id.btnLogin)
                        okButton.setOnClickListener {
                            var intent = Intent(this@AI_RecommendActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        val noButton = mDialogView.findViewById<Button>(R.id.btnClose)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }else{
                        var intent = Intent(this@AI_RecommendActivity, AI_RecommendActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}