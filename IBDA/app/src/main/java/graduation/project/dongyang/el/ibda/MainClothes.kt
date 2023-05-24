package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
=======
import androidx.recyclerview.widget.GridLayoutManager

import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainClothesBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainClothes : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainClothesBinding.inflate(layoutInflater)
    }

    val clothList :ArrayList<ClothesResponseItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //회원정보
        val userID = intent.getStringExtra("id")

        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        binding.toolbar.title = "의상 : " + userID.toString()

        //의상 출력
        val footerProfileList = arrayListOf(
            ClothFooter(R.drawable.el_logo_png, "브랜드1","상의1",10000),
            ClothFooter(R.drawable.ibda_logo_png, "브랜드2","상의2", 20000),
            ClothFooter(R.drawable.dongyang_logo_png, "브랜드3","상의3",30000),
            ClothFooter(R.drawable.el_logo_png, "브랜드1","상의1",10000),
            ClothFooter(R.drawable.ibda_logo_png, "브랜드2","상의2",20000),
            ClothFooter(R.drawable.dongyang_logo_png, "브랜드3","상의3",30000),
            ClothFooter(R.drawable.el_logo_png, "브랜드1","상의1",10000),
            ClothFooter(R.drawable.ibda_logo_png, "브랜드2","상의2",20000),
            ClothFooter(R.drawable.dongyang_logo_png, "브랜드3","상의3",30000),
            ClothFooter(R.drawable.el_logo_png, "브랜드1","상의1",10000),
            ClothFooter(R.drawable.ibda_logo_png, "브랜드2","상의2",20000),
            ClothFooter(R.drawable.dongyang_logo_png, "브랜드3","상의3",30000),
            ClothFooter(R.drawable.el_logo_png, "브랜드1","상의1",10000),
            ClothFooter(R.drawable.ibda_logo_png, "브랜드2","상의2",20000),
            ClothFooter(R.drawable.dongyang_logo_png, "브랜드3","상의3",30000),
            ClothFooter(R.drawable.el_logo_png, "브랜드1","상의1",10000),
            ClothFooter(R.drawable.ibda_logo_png, "브랜드2","상의2",20000),
            ClothFooter(R.drawable.dongyang_logo_png, "브랜드3","상의3",30000),
        )
        val footerRv = binding.footerRv
        footerRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        footerRv.setHasFixedSize(true)
        footerRv.adapter = ClothFooterAdapter(footerProfileList)
        
        //thread
        val thread = NetworkThread()
        thread.start()
        thread.join()

    }
    //액션버튼 메뉴 액션바에 집어 넣기
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

}



=======
    // 의상 출력
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
                // 출력할 리스트

                // 페이지 수만큼 반복하여 데이터를 불러온다.
                for(i in 0 until item.length()){
                    // 쪽수 별로 데이터를 읽는다.
                    val jObject = item.getJSONObject(i)

                    clothList.add(ClothesResponseItem(JSON_Parse(jObject,"category"),
                        JSON_Parse(jObject,"id"),
                        JSON_Parse(jObject,"image"),
                        JSON_Parse(jObject,"name"),
                        JSON_Parse(jObject,"price")))
                }

                val rvCloth = binding.rvCloth
                rvCloth.layoutManager = GridLayoutManager(this@MainClothes,3)
                rvCloth.setHasFixedSize(true)
                rvCloth.adapter = ClothAdapter(clothList)
            }
        }

        // 함수를 통해 데이터를 불러온다.
        fun JSON_Parse(obj:JSONObject, data : String): String {

            // 원하는 정보를 불러와 리턴받고 없는 정보는 캐치하여 "없습니다."로 리턴받는다.
            return try {

                obj.getString(data)

            } catch (e: Exception) {
                "없습니다."
            }
        }
    }

}
