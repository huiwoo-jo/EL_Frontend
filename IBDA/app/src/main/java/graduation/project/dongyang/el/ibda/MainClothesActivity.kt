package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainClothesBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainClothesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainClothesBinding.inflate(layoutInflater)
    }

    // val clothList :ArrayList<ClothesResponseItem> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //회원정보
        val userID = intent.getStringExtra("id")

        //툴바
        setSupportActionBar(binding.toolbar) //커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.
        binding.toolbar.title = "의상 : " + userID.toString()


        // 뷰 초기 상태 설정
        binding.rvTop.visibility = View.VISIBLE
        binding.rvPant.visibility = View.GONE
        binding.rvRecommand.visibility = View.GONE

        //버튼 클릭
        binding.btnTops.setOnClickListener{ButtonListener()}
        binding.btnPants.setOnClickListener{ButtonListener()}
        binding.btnRecommands.setOnClickListener{ButtonListener()}
        binding.btnPayment.setOnClickListener{ButtonListener()}
        binding.btnAR.setOnClickListener{ButtonListener()}
        binding.btnExperience.setOnClickListener{ButtonListener()}

        // ! 수정 필요 - 의상 출력 : 하단 하드코딩
        //의상 출력
        val footerProfileList = arrayListOf(
            ClothFooter(R.drawable.pants2, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.top2, "브랜드2","상의1", 20000),
            ClothFooter(R.drawable.top1, "브랜드3","상의2",30000)
        )
        val footerRv = binding.footerRv
        footerRv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        footerRv.setHasFixedSize(true)
        footerRv.adapter = ClothFooterAdapter(footerProfileList)

        val clothPantsList = arrayListOf(
            ClothFooter(R.drawable.pants1, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.pants2, "브랜드2","하의2", 20000),
            ClothFooter(R.drawable.pants3, "브랜드3","하의3",30000),
            ClothFooter(R.drawable.pants1, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.pants2, "브랜드2","하의2", 20000),
            ClothFooter(R.drawable.pants3, "브랜드3","하의3",30000),
            ClothFooter(R.drawable.pants1, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.pants2, "브랜드2","하의2", 20000),
            ClothFooter(R.drawable.pants3, "브랜드3","하의3",30000),
            ClothFooter(R.drawable.pants1, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.pants2, "브랜드2","하의2", 20000),
            ClothFooter(R.drawable.pants3, "브랜드3","하의3",30000),
            ClothFooter(R.drawable.pants1, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.pants2, "브랜드2","하의2", 20000),
            ClothFooter(R.drawable.pants3, "브랜드3","하의3",30000),
            ClothFooter(R.drawable.pants1, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.pants2, "브랜드2","하의2", 20000),
            ClothFooter(R.drawable.pants3, "브랜드3","하의3",30000),
            ClothFooter(R.drawable.pants1, "브랜드1","하의1",10000),
            ClothFooter(R.drawable.pants2, "브랜드2","하의2", 20000),
            ClothFooter(R.drawable.pants3, "브랜드3","하의3",30000),


            )
        val pantsRv = binding.rvPant
        pantsRv.layoutManager = GridLayoutManager(this@MainClothesActivity,3)
        pantsRv.setHasFixedSize(true)
        pantsRv.adapter = ClothTopsAdapter(clothPantsList)

        val clothRecommandsList = arrayListOf(
            ClothFooter(R.drawable.top1, "브랜드1","추천1",50000),
            ClothFooter(R.drawable.pants1, "브랜드2","추천2", 20000),
            ClothFooter(R.drawable.top2, "브랜드3","추천3",30000),
            ClothFooter(R.drawable.pants2, "브랜드1","추천1",10000),
            ClothFooter(R.drawable.top3, "브랜드2","추천2",20000),
            ClothFooter(R.drawable.pants3, "브랜드3","추천3",30000),
            ClothFooter(R.drawable.top1, "브랜드1","추천1",50000),
            ClothFooter(R.drawable.pants1, "브랜드2","추천2", 20000),
            ClothFooter(R.drawable.top2, "브랜드3","추천3",30000),
            ClothFooter(R.drawable.pants2, "브랜드1","추천1",10000),
            ClothFooter(R.drawable.top3, "브랜드2","추천2",20000),
            ClothFooter(R.drawable.pants3, "브랜드3","추천3",30000),
            ClothFooter(R.drawable.top1, "브랜드1","추천1",50000),
            ClothFooter(R.drawable.pants1, "브랜드2","추천2", 20000),
            ClothFooter(R.drawable.top2, "브랜드3","추천3",30000),
            ClothFooter(R.drawable.pants2, "브랜드1","추천1",10000),
            ClothFooter(R.drawable.top3, "브랜드2","추천2",20000),
            ClothFooter(R.drawable.pants3, "브랜드3","추천3",30000),
            ClothFooter(R.drawable.top1, "브랜드1","추천1",50000),
            ClothFooter(R.drawable.pants1, "브랜드2","추천2", 20000),
            ClothFooter(R.drawable.top2, "브랜드3","추천3",30000),
            ClothFooter(R.drawable.pants2, "브랜드1","추천1",10000),
            ClothFooter(R.drawable.top3, "브랜드2","추천2",20000),
            ClothFooter(R.drawable.pants3, "브랜드3","추천3",30000),

            )
        val recommendRv = binding.rvRecommand
        recommendRv.layoutManager = GridLayoutManager(this@MainClothesActivity,3)
        recommendRv.setHasFixedSize(true)
        recommendRv.adapter = ClothRecommandsAdapter(clothRecommandsList)

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


    // 버튼 클릭
    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()
            when (v?.id) {
                // 의상 뷰
                R.id.btnTops -> {
                    // 상의 출력
                    binding.rvTop.visibility=View.VISIBLE
                    binding.rvPant.visibility=View.GONE
                    binding.rvRecommand.visibility=View.GONE
                }
                R.id.btnPants -> {
                    // 하의 출력
                    binding.rvTop.visibility=View.GONE
                    binding.rvPant.visibility=View.VISIBLE
                    binding.rvRecommand.visibility=View.GONE
                }
                R.id.btnRecommands -> {
                    // 추천 의상 출력
                    val userID = intent.getStringExtra("id")
                    if(userID == "비회원"){
                        // 팝업 페이지로 변경
                        Toast.makeText(this@MainClothesActivity, "회원 전용 페이지 입니다.", Toast.LENGTH_SHORT).show()
                    }else {
                        binding.rvTop.visibility = View.GONE
                        binding.rvPant.visibility = View.GONE
                        binding.rvRecommand.visibility = View.VISIBLE
                    }
                }
                //페이지 이동
                R.id.btnPayment -> {
                    // 결제 페이지
                }
                R.id.btnAR -> {
                    // AR 페이지
                }
                R.id.btnExperience -> {
                    // 사이즈 측정 페이지
                }
            }
            startActivity(intent)
            finish()
        }
    }

    /*
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

                val rvCloth = binding.rvTop
                rvCloth.layoutManager = GridLayoutManager(this@MainClothesActivity,3)
                rvCloth.setHasFixedSize(true)
                rvCloth.adapter = ClothTopsAdapter(clothList)
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
