package kr.project.dongyang.el.ibda.ibda_admin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import graduation.project.dongyang.el.ibda.adapter.ClothHardCodingAdapter
import graduation.project.dongyang.el.ibda.adapter.MemberHardCodingAdapter
import graduation.project.dongyang.el.ibda.recycler.ClothesHardcodingItem
import graduation.project.dongyang.el.ibda.recycler.MemberHardcodingItem
import kr.project.dongyang.el.ibda.ibda_admin.R
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityMainClothesBinding
import kr.project.dongyang.el.ibda.ibda_admin.databinding.ActivityMainMemberBinding

class MainMemberActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainMemberBinding.inflate(layoutInflater)
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
        binding.btnUpPage.setOnClickListener(ButtonListener())
        binding.btnDownPage.setOnClickListener(ButtonListener())

        // ! 수정 필요 - 의상 출력 : 하드코딩
        //의상 출력

        val memberAllList = arrayListOf(
            MemberHardcodingItem(1,"huiwoo-jo", "김동양", 25,"admin"),
            MemberHardcodingItem(2,"huiwoo-jo", "최미래", 30,"admin"),
            MemberHardcodingItem(3,"huiwoo-jo", "한대학", 24,"admin"),
            MemberHardcodingItem(4,"huiwoo-jo", "이고척", 32,"mem"),
            MemberHardcodingItem(5,"huiwoo-jo", "강하늘", 45,"mem"),
            MemberHardcodingItem(6,"huiwoo-jo", "최한나", 22,"mem"),
            MemberHardcodingItem(7,"huiwoo-jo", "장소라", 27,"mem"),
            MemberHardcodingItem(8,"huiwoo-jo", "박슬기", 36,"mem"),
        )
        val allRv = binding.rvMember
        allRv.layoutManager = GridLayoutManager(this,1)
        allRv.setHasFixedSize(true)
        allRv.adapter = MemberHardCodingAdapter(memberAllList)
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
                val back = Intent(this, MainActivity::class.java)
                startActivity(back)
                finish()
            }
        }
        return super.onOptionsItemSelected(item);
    }

    inner class ButtonListener: View.OnClickListener {
        override fun onClick(v: View?) {
            var intent = Intent()

            when (v?.id) {
                R.id.btnUpPage -> {

                }

                R.id.btnDownPage -> {
                }
            }
            startActivity(intent)
            finish()
        }
    }
}