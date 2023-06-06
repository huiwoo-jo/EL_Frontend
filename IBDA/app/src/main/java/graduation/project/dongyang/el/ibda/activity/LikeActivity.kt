package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import graduation.project.dongyang.el.ibda.adapter.ClothLikeAdapter
import graduation.project.dongyang.el.ibda.recycler.ClothesHardcodingItem
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLikeBinding

class LikeActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLikeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // ! 수정 필요 - 의상 출력 : 하드코딩
        //의상 출력
        val clothTopsList = arrayListOf(
            ClothesHardcodingItem("상의", R.drawable.top1, "상의1", 1000),
            ClothesHardcodingItem("상의", R.drawable.top2, "상의2", 2000),
            ClothesHardcodingItem("상의", R.drawable.top3, "상의3", 3000),
        )
        val allRv = binding.rvAll
        allRv.layoutManager = GridLayoutManager(this,3)
        allRv.setHasFixedSize(true)
        allRv.adapter = ClothLikeAdapter(clothTopsList)

        //버튼
        binding.btnBack.setOnClickListener(ButtonListener())
        binding.btnSearch.setOnClickListener(ButtonListener())
        binding.btnShop.setOnClickListener(ButtonListener())
        binding.btnAR.setOnClickListener(ButtonListener())
    }

    // 버튼 클릭
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            val userID = intent.getStringExtra("id")
            var intent = Intent()
            when (v?.id) {
                // 의상 뷰
                R.id.btnBack -> {
                    intent= Intent(this@LikeActivity, MainClothesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnSearch -> {
                    intent=Intent(this@LikeActivity, SearchActivty::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnShop -> {
                    intent=Intent(this@LikeActivity, ShopActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnAR -> {
                    if(userID=="비회원") {
                        val mDialogView = LayoutInflater.from(this@LikeActivity)
                            .inflate(R.layout.activity_popup_login, null)
                        val mBuilder = AlertDialog.Builder(this@LikeActivity)
                            .setView(mDialogView)
                        val mAlertDialog = mBuilder.show()
                        val okButton = mDialogView.findViewById<Button>(R.id.btnLogin)
                        okButton.setOnClickListener {
                            intent = Intent(this@LikeActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        val noButton = mDialogView.findViewById<Button>(R.id.btnClose)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }else{
                        val mDialogView = LayoutInflater.from(this@LikeActivity)
                            .inflate(R.layout.activity_popup_ar, null)
                        val mBuilder = AlertDialog.Builder(this@LikeActivity)
                            .setView(mDialogView)
                        val mAlertDialog = mBuilder.show()
                        val okButton = mDialogView.findViewById<Button>(R.id.btnData)
                        okButton.setOnClickListener {
                            intent = Intent(this@LikeActivity, AR_fittingActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        val noButton = mDialogView.findViewById<Button>(R.id.btnClose)
                        noButton.setOnClickListener {
                            intent = Intent(this@LikeActivity, AR_SizeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
        }
    }
}