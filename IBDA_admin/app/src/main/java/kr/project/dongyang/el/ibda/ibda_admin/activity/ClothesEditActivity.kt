package kr.project.dongyang.el.ibda.ibda_admin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import kr.project.dongyang.el.ibda.ibda_admin.R
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityClothesEditBinding

class ClothesEditActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityClothesEditBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24_white)  // 왼쪽 버튼 아이콘 설정


        //버튼
        binding.btnEditDone.setOnClickListener(ButtonListener())
        binding.btnEditCancle.setOnClickListener(ButtonListener())
        binding.btnGallery.setOnClickListener(ButtonListener())

        //정보
        binding.clothBrand.setText("IBDA")
        binding.clothCategory.setText(intent.getStringExtra("category"))
        binding.clothName.setText(intent.getStringExtra("name"))
        binding.clothPrice.setText(intent.getStringExtra("price"))

        val imageData =  intent.getStringExtra("image")?.toInt()
        val defaultImage = R.drawable.ibda_logo_png

        binding.clothImage.apply {
            Glide.with(this)
                .load(imageData) // 불러올 이미지 url
                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(binding.clothImage)
        }

    }
    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    //액션버튼을 눌렀을때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                val back = Intent(this@ClothesEditActivity, MainClothesActivity::class.java)
                startActivity(back)
                finish()
            }
        }
        return super.onOptionsItemSelected(item);
    }

    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            // 의류 정보
            val id = intent.getStringExtra("id")
            val category = intent.getStringExtra("category")
            val image = intent.getStringExtra("image")
            val name = intent.getStringExtra("name")
            val price = intent.getStringExtra("price")

            var intent = Intent(this@ClothesEditActivity, ClothesDetailsActivity::class.java)
            intent.putExtra("id", id)
            intent.putExtra("category", category)
            intent.putExtra("image", image)
            intent.putExtra("name", name)
            intent.putExtra("price", price)

            when (v?.id) {
                R.id.btnEditDone -> {
                    val mDialogView = LayoutInflater.from(this@ClothesEditActivity)
                        .inflate(R.layout.activity_popup, null)
                    val mBuilder = AlertDialog.Builder(this@ClothesEditActivity)
                        .setView(mDialogView)
                    val mAlertDialog = mBuilder.show()
                    mDialogView.findViewById<TextView>(R.id.popup_info).setText(R.string.popup_clothes_edit)

                    val okButton = mDialogView.findViewById<Button>(R.id.popup_btnDone)
                    okButton.setOnClickListener {
                        startActivity(intent)
                        finish()
                    }
                    val noButton = mDialogView.findViewById<Button>(R.id.popup_btnCancle)
                    noButton.setOnClickListener {
                        mAlertDialog.dismiss()
                    }
                }
                R.id.btnEditCancle -> {
                    //취소 시 메인화면으로 이동
                    startActivity(intent)
                    finish()
                }

                R.id.btnGallery -> {
                    intent = Intent(Intent.ACTION_PICK)
                    intent.type="image/*"
                    activityResult.launch(intent)
                }
            }
        }
    }
    private val  activityResult : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK && it.data != null ){
            val uri = it.data!!.data
            Glide.with(this)
                .load(uri)
                .into(binding.clothImage)
        }
    }
}