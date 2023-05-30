package kr.project.dongyang.el.ibda.ibda_admin.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kr.project.dongyang.el.ibda.ibda_admin.sc_clothes.ClothAdapter
import kr.project.dongyang.el.ibda.ibda_admin.data.ClothesResponseItem
import kr.project.dongyang.el.ibda.ibda_admin.R
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityMainClothesBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class MainClothesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainClothesBinding.inflate(layoutInflater)
    }

    val clothList :ArrayList<ClothesResponseItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)  // 왼쪽 버튼 사용 여부 true
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_home_24_white)  // 왼쪽 버튼 아이콘 설정

        //버튼
        binding.btnAddClothes.setOnClickListener(ButtonListener())
        binding.btnUpPage.setOnClickListener(ButtonListener())
        binding.btnDownPage.setOnClickListener(ButtonListener())

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

    //액션버튼을 눌렀을때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.drawable.ic_baseline_home_24_white -> {
                //안드로이드 홈화면 눌렀을 때
                val back = Intent(this, MainActivity::class.java)
                startActivity(back)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()

            when (v?.id) {
                R.id.btnAddClothes ->{
                    intent = Intent(this@MainClothesActivity, ClothesAddActivity::class.java)
                }
                R.id.btnUpPage -> {

                }
                R.id.btnDownPage ->{
                }
            }
            startActivity(intent)
            finish()
        }
    }

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

                    clothList.add(
                        ClothesResponseItem(JSON_Parse(jObject,"category"),
                        JSON_Parse(jObject,"id"),
                        JSON_Parse(jObject,"image"),
                        JSON_Parse(jObject,"name"),
                        JSON_Parse(jObject,"price"))
                    )
                }

                val rvCloth = binding.rvCloth
                rvCloth.layoutManager = GridLayoutManager(this@MainClothesActivity,1)
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