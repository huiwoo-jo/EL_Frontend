package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginBinding
import kr.project.dongyang.EL.IBDA.databinding.ActivityMainClothesBinding

class MainClothes : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainClothesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val receivedMessage = intent.getStringExtra("id")
        binding.printID.text = receivedMessage

        binding.btnBack.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            startActivity(back)
            finish()
        }

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
}