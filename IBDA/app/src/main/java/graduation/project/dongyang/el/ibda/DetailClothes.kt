package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityDetailClothesBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class DetailClothes : AppCompatActivity() {

    private lateinit var binding: ActivityDetailClothesBinding
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityDetailClothesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        super.onCreate(savedInstanceState)

        //툴바
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //즐겨찾기 버튼
        val favoriteBtn = binding.btnFavorite

        favoriteBtn.setOnClickListener {
            isFavorite = !isFavorite
            updateFavoriteButtonState()

        }
        
        //thread
        val thread = NetworkThread()
        thread.start()
        thread.join()

    }

    private fun updateFavoriteButtonState() {
        //즐겨찾기 추가된 상태
        if (isFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.selected_favorite)
        } else { //즐겨찾기 해제된 상태
            binding.btnFavorite.setImageResource(R.drawable.unselected_favorite)
        }
    }


    //액션바에 액션 버튼 삽입
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.action_home -> {
                //안드로이드 홈화면 눌렀을 때
                val back = Intent(this, MainActivity::class.java)
                startActivity(back)
                finish()
            }

            R.id.action_info -> {
                val popup = PopupFragment()
                popup.show(supportFragmentManager, "popup")
            }

        }
        return super.onOptionsItemSelected(item)
    }

    inner class NetworkThread: Thread(){
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
                binding.clothName.text = jObject?.let { JSON_Parse(it,"name") }
                binding.clothPrice.text = jObject?.let { JSON_Parse(it,"price") }
                binding.clothCate.text = jObject?.let { JSON_Parse(it,"category") }

                Glide.with(this@DetailClothes)
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
}
