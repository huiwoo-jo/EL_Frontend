package graduation.project.dongyang.el.ibda.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoginQrBinding

class Login_QR_Activity : AppCompatActivity() {
    val binding by lazy{
        ActivityLoginQrBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap =
                barcodeEncoder.encodeBitmap("https://el21.netlify.app/", BarcodeFormat.QR_CODE, 400, 400)
            val imageViewQrcode = binding.loginQR
            imageViewQrcode.setImageBitmap(bitmap)
        } catch (e: Exception) {

        }

        binding.btnBack.setOnClickListener {
            var intent = Intent(this@Login_QR_Activity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}