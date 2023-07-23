package kr.project.dongyang.el.ibda.ibda_admin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import kr.project.dongyang.el.ibda.ibda_admin.R
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityClothesDetailsBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class ClothesDetailsActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityClothesDetailsBinding.inflate(layoutInflater)
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
        binding.btnPrev.setOnClickListener(ButtonListener())
        binding.btnHome.setOnClickListener(ButtonListener())
        binding.btnEdit.setOnClickListener(ButtonListener())
        binding.btnNext.setOnClickListener(ButtonListener())


        //정보
        binding.clothBrand.text = "IBDA"
        binding.clothCategory.text = intent.getStringExtra("category")
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
        /*
        val thread = NetworkThread()
        thread.start()
        thread.join()
         */
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
                val back = Intent(this@ClothesDetailsActivity, MainClothesActivity::class.java)
                startActivity(back)
                finish()
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //버튼 클릭
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            // 의류 정보
            val id = intent.getStringExtra("id")
            val category = intent.getStringExtra("category")
            val image = intent.getStringExtra("image")
            val name = intent.getStringExtra("name")
            val price = intent.getStringExtra("price")

            val num = id?.toInt()

            var intent = Intent()
            when (v?.id) {
                R.id.btnPrev -> {
                    // 메인 -> 이전 의상
                    if((num?.minus(1))!! <0){
                        Toast.makeText(this@ClothesDetailsActivity, "처음 페이지입니다.", Toast.LENGTH_SHORT).show()
                    }else if (num != null) {
                        intent = Intent(this@ClothesDetailsActivity, ClothesDetailsActivity::class.java)
                        intent.putExtra("id", (num-1).toString())
                        startActivity(intent)
                        finish()
                    }
                }
                R.id.btnHome -> {
                    // 메인 -> 메인화면
                    intent = Intent(this@ClothesDetailsActivity, MainClothesActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.btnEdit -> {
                    // 메인 -> 회원 관리

                    var intent = Intent(this@ClothesDetailsActivity, ClothesEditActivity::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("category", category)
                    intent.putExtra("image", image)
                    intent.putExtra("name", name)
                    intent.putExtra("price", price)
                    startActivity(intent)
                    finish()
                }
                R.id.btnNext -> {
                    // 메인 -> 다음 의상
                    if (num != null) {
                        intent = Intent(this@ClothesDetailsActivity, ClothesDetailsActivity::class.java)
                        intent.putExtra("id", (num+1).toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

    /*
    inner class NetworkThread: Thread(){
        // 의류 번호
        val id = intent.getStringExtra("id")
        val num = id?.toInt()

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
                // 페이지 id의 데이터를 불러온다.
                val jObject = num?.let { item.getJSONObject(it) }
                val imageView = binding.clothImageSlider
                val defaultImage = R.drawable.ibda_logo_png
                val url = jObject?.let { JSON_Parse(it,"image") }

                // 데이터를 적용한다.
                if (num != null) {
                    binding.clothId.text = "No. "+(num+1).toString()
                }
                binding.clothName.text = jObject?.let { JSON_Parse(it,"name") }
                binding.clothPrice.text = jObject?.let { JSON_Parse(it,"price") }
                binding.clothCategory.text = jObject?.let { JSON_Parse(it,"category") }

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

    }
     */
}