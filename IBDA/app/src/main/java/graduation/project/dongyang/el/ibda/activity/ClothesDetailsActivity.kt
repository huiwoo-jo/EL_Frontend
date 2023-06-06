package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.bumptech.glide.Glide
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityDetailClothesBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class ClothesDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailClothesBinding
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailClothesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        super.onCreate(savedInstanceState)

        //툴바
        binding.btnBack.setOnClickListener(ButtonListener())
        binding.btnSearch.setOnClickListener(ButtonListener())
        binding.btnLike.setOnClickListener(ButtonListener())
        binding.btnShop.setOnClickListener(ButtonListener())

        //즐겨찾기 버튼
        binding.checkbox.setButtonDrawable(R.drawable.selector_btnlike_50)

        //정보
        binding.clothBrand.text = "IBDA"

        binding.clothName.text = intent.getStringExtra("name")
        binding.clothPrice.text = intent.getStringExtra("price")

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
        //thread
/*      val thread = NetworkThread()
        thread.start()
        thread.join()*/

        //정보 버튼
        binding.radioInfo.setButtonDrawable(R.drawable.selector_btn_underline)
        binding.radioSize.setButtonDrawable(R.drawable.selector_btn_underline)
        binding.radioReview.setButtonDrawable(R.drawable.selector_btn_underline)
        binding.radioRecommand.setButtonDrawable(R.drawable.selector_btn_underline)
        binding.radioQnA.setButtonDrawable(R.drawable.selector_btn_underline)

        binding.radioInfo.setOnClickListener(RadioButton())
        binding.radioSize.setOnClickListener(RadioButton())
        binding.radioReview.setOnClickListener(RadioButton())
        binding.radioRecommand.setOnClickListener(RadioButton())
        binding.radioQnA.setOnClickListener(RadioButton())

        binding.btnAddShop.setOnClickListener(ButtonListener())

    }

    // 버튼 클릭
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()
            when (v?.id) {
                // 의상 뷰
                R.id.btnBack -> {
                    // 메인 페이지
                    intent=Intent(this@ClothesDetailsActivity, MainClothesActivity::class.java)
                }
                R.id.btnSearch -> {
                    intent = Intent(this@ClothesDetailsActivity, SearchActivty::class.java)
                }
                R.id.btnLike -> {
                    intent = Intent(this@ClothesDetailsActivity, LikeActivity::class.java)
                }
                R.id.btnShop -> {
                    intent = Intent(this@ClothesDetailsActivity, ShopActivity::class.java)
                }
                R.id.btnAddShop -> {
                    Toast.makeText(this@ClothesDetailsActivity, "${binding.clothName.text}이(가) 장바구니에 추가되었습니다.",LENGTH_SHORT).show()
                    intent=Intent(this@ClothesDetailsActivity, MainClothesActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }
    }

    inner class RadioButton : View.OnClickListener {
        override fun onClick(v: View?) {
            binding.rvInfo.visibility = View.GONE
            binding.rvSize.visibility = View. GONE
            binding.rvReview.visibility = View.GONE
            binding.rvRecommand.visibility = View.GONE
            binding.rvQnA.visibility = View.GONE
            when (v?.id) {
                R.id.radio_info -> { binding.rvInfo.visibility = View.VISIBLE }
                R.id.radio_size -> {binding.rvSize.visibility = View.VISIBLE }
                R.id.radio_review -> {binding.rvReview.visibility = View.VISIBLE}
                R.id.radio_recommand -> {binding.rvRecommand.visibility = View.VISIBLE}
                R.id.radio_QnA -> {binding.rvQnA.visibility = View.VISIBLE}
            }
        }
    }
    //액션바에 액션 버튼 삽입
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    /*inner class NetworkThread: Thread(){
        override fun run() {
            // API 정보를 가지고 있는 주소
            val site = "http://ibdabackend.iptime.org:5001/clothes"

            val url = URL(site)
            val conn = url.openConnection()
            val input = conn.getInputStream()
            val isr = InputStreamReader(input)
            // br: 라인 단위로 데이터를 읽어오기 위해서 만듦
            val br = BufferedReader(isr)

            // Json 문서는 일단 문자열로 데이터를 모두 읽어온 후, Json에 관련된 객체를 만들어서 데이터를 가져옴
            var str: String? = null
            val buf = StringBuffer()
            do{
                str = br.readLine()
                if(str!=null){
                    buf.append(str)
                }
            }while (str!=null)

            val item = JSONArray(buf.toString())

            // 화면에 출력
            runOnUiThread {
                // 의류 번호
                val id = intent.getStringExtra("id")
                val num = id?.toInt()

                // 페이지 id의 데이터를 불러온다.
                val jObject = num?.let { item.getJSONObject(it) }
                val imageView = binding.clothImageSlider
                val defaultImage = R.drawable.ibda_logo_png
                val url = jObject?.let { JSON_Parse(it,"image") }

                // 데이터를 적용한다.
                binding.clothBrand.text = "EL - IBDA"
                binding.clothName.text = jObject?.let { JSON_Parse(it,"name") }
                binding.clothPrice.text = jObject?.let { JSON_Parse(it,"price") }

                Glide.with(this@ClothesDetailsActivity)
                    .load(url) // 불러올 이미지 url
                    .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
                    .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                    .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                    .into(imageView) // 이미지를 넣을 뷰
            }
        }

        // 함수를 통해 데이터를 불러온다.
        fun JSON_Parse(obj: JSONObject, data : String): String {

            // 원하는 정보를 불러와 리턴받고 없는 정보는 캐치하여 "없습니다."로 리턴받는다.
            return try {

                obj.getString(data)

            } catch (e: Exception) {
                "없습니다."
            }
        }
    }*/
}
