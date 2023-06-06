package graduation.project.dongyang.el.ibda.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import graduation.project.dongyang.el.ibda.adapter.ClothHardCodingAdapter
import graduation.project.dongyang.el.ibda.recycler.ClothesHardcodingItem
import graduation.project.dongyang.el.ibda.recycler.ClothesResponseItem
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainClothesBinding

class MainClothesActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainClothesBinding.inflate(layoutInflater)
    }

    val clothList :ArrayList<ClothesResponseItem> = arrayListOf()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //회원정보
        val userID = intent.getStringExtra("id")

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

        binding.btnMainAI.setOnClickListener(ButtonListener())


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


        //thread
/*        val thread = NetworkThread()
        thread.start()
        thread.join()*/

    }
    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val userID = intent.getStringExtra("id")
        var intent = Intent()

        when (item?.itemId) {
            R.id.action_search -> {
                //검색
                intent=Intent(this@MainClothesActivity, SearchActivty::class.java)
                startActivity(intent)
                finish()
            }
            R.id.action_like -> {
                //관심
                intent=Intent(this@MainClothesActivity, LikeActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.action_basket -> {
                // 장바구니
                intent=Intent(this@MainClothesActivity, ShopActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.action_power -> {
                // 계정

                val mDialogView = LayoutInflater.from(this@MainClothesActivity)
                    .inflate(R.layout.activity_popup_logout, null)
                val mBuilder = AlertDialog.Builder(this@MainClothesActivity)
                    .setView(mDialogView)
                val mAlertDialog = mBuilder.show()
                val okButton = mDialogView.findViewById<Button>(R.id.btnLogout)
                val noButton = mDialogView.findViewById<Button>(R.id.btnClose)

                if(userID=="비회원" || userID=="null") {
                    okButton.setText("메인화면")
                    mDialogView.findViewById<TextView>(R.id.text).setText("IBDA 이용을\n종료하시겠습니까?")
                }else{
                    okButton.setText("로그아웃")
                    mDialogView.findViewById<TextView>(R.id.text).setText("$userID 님\nIBDA 이용을\n종료하시겠습니까?")
                }

                okButton.setOnClickListener {
                    var intent = Intent(this@MainClothesActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                noButton.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


    // 버튼 클릭
    inner class ButtonListener(): View.OnClickListener {
        override fun onClick(v: View?) {
            val userID = intent.getStringExtra("id")

            binding.rvAll.visibility=View.GONE
            binding.rvTop.visibility=View.GONE
            binding.rvPant.visibility=View.GONE
            binding.rvDress.visibility=View.GONE
            binding.rvEtc.visibility=View.GONE

            when (v?.id) {
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
                        val mDialogView = LayoutInflater.from(this@MainClothesActivity)
                            .inflate(R.layout.activity_popup_login, null)
                        val mBuilder = AlertDialog.Builder(this@MainClothesActivity)
                            .setView(mDialogView)
                        val mAlertDialog = mBuilder.show()
                        val okButton = mDialogView.findViewById<Button>(R.id.btnLogin)
                        okButton.setOnClickListener {
                            var intent = Intent(this@MainClothesActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        val noButton = mDialogView.findViewById<Button>(R.id.btnClose)
                        noButton.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }else{
                        var intent = Intent(this@MainClothesActivity, AI_RecommendActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }

    /*// 의상 출력
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

                binding.rvAll.layoutManager = GridLayoutManager(this@MainClothesActivity,3)
                binding.rvAll.setHasFixedSize(true)
                binding.rvAll.adapter = ClothAdapter(clothList)
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
    }*/
}
