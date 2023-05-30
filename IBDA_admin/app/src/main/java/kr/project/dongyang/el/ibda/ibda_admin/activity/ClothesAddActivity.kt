package kr.project.dongyang.el.ibda.ibda_admin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import kr.project.dongyang.el.ibda.ibda_admin.R
import kr.project.dongyang.el.ibda.ibda_admin.data.ClothesPostItem
import kr.project.dongyang.el.ibda.ibda_admin.data.PostResult
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityAddClothesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClothesAddActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityAddClothesBinding.inflate(layoutInflater)
    }

    val api = APIS.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24_white)  // 왼쪽 버튼 아이콘 설정

        //버튼
        binding.btnClothesPost.setOnClickListener(ButtonListener())
        binding.btnClothesCancle.setOnClickListener(ButtonListener())

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
                val back = Intent(this@ClothesAddActivity, MainClothesActivity::class.java)
                startActivity(back)
                finish()
            }
        }
        return super.onOptionsItemSelected(item);
    }


    //버튼
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent(this@ClothesAddActivity, MainClothesActivity::class.java)
            when (v?.id) {
                R.id.btnClothesPost ->{
                    //Clothes등록
                    val category = binding.addClothCategory.text.toString()
                    val name = binding.addClothName.text.toString()
                    val image = binding.addClothImg.text.toString()
                    val price = Integer.parseInt(binding.addClothPrice.text.toString())
                    val data = ClothesPostItem(category,name,image, price)

                    api.post_clothes(data).enqueue(object : Callback<PostResult> {
                        override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                            if(!response.body().toString().isEmpty())
                                Toast.makeText(this@ClothesAddActivity, "의류 $name 을 등록하였습니다.", Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(call: Call<PostResult>, t: Throwable) {
                            // 실패
                            Toast.makeText(this@ClothesAddActivity, "의류 $name 을 등록을 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        }
                    })

                    startActivity(intent)
                    finish()
                }
                R.id.btnClothesCancle ->{
                    //취소 시 메인화면으로 이동
                    startActivity(intent)
                    finish()
                }
                R.id.btnGallery ->{
                    intent=Intent(Intent.ACTION_PICK)
                    intent.type = "image/*"
                    activityResult.launch(intent)
                }

            }
        }

        private val activityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK && it.data != null){
                val uri = it.data!!.data
                val imageView = binding.imgClothes
                var defaultImage = R.drawable.ibda_logo_png

                Glide.with(this@ClothesAddActivity)
                    .load(uri) // 불러올 이미지 uri
                    .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
                    .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                    .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                    .into(imageView) // 이미지를 넣을 뷰
            }
        }
    }
}
