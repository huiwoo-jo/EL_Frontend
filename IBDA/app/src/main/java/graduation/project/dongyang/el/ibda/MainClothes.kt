package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginBinding
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainClothesBinding
import android.widget.Toolbar as Toolbar1

class MainClothes : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainClothesBinding.inflate(layoutInflater)
    }

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
        val profileList = arrayListOf(
            Cloth(R.drawable.el_logo_png, "상의1","10,000"),
            Cloth(R.drawable.ibda_logo_png, "상의2","10,000"),
            Cloth(R.drawable.dongyang_logo_png, "상의3","10,000"),
            Cloth(R.drawable.el_logo_png, "상의1","10,000"),
            Cloth(R.drawable.ibda_logo_png, "상의2","10,000"),
            Cloth(R.drawable.dongyang_logo_png, "상의3","10,000"),
            Cloth(R.drawable.el_logo_png, "상의1","10,000"),
            Cloth(R.drawable.ibda_logo_png, "상의2","10,000"),
            Cloth(R.drawable.dongyang_logo_png, "상의3","10,000"),
            Cloth(R.drawable.el_logo_png, "상의1","10,000"),
            Cloth(R.drawable.ibda_logo_png, "상의2","10,000"),
            Cloth(R.drawable.dongyang_logo_png, "상의3","10,000"),
            Cloth(R.drawable.el_logo_png, "상의1","10,000"),
            Cloth(R.drawable.ibda_logo_png, "상의2","10,000"),
            Cloth(R.drawable.dongyang_logo_png, "상의3","10,000"),
            Cloth(R.drawable.el_logo_png, "상의1","10,000"),
            Cloth(R.drawable.ibda_logo_png, "상의2","10,000"),
            Cloth(R.drawable.dongyang_logo_png, "상의3","10,000"),
            )
        val rvCloth = binding.rvCloth
        rvCloth.layoutManager = GridLayoutManager(this,3)
        rvCloth.setHasFixedSize(true)
        rvCloth.adapter = ClothAdapter(profileList)

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