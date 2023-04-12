package graduation.project.dongyang.el.ibda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kr.project.dongyang.EL.IBDA.databinding.ActivityCreateQrBinding

class CreateQRActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCreateQrBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap =
                barcodeEncoder.encodeBitmap("https://el21.netlify.app/", BarcodeFormat.QR_CODE, 400, 400)
            val imageViewQrcode: ImageView = binding.imageQR
            imageViewQrcode.setImageBitmap(bitmap)
        } catch (e: Exception) {

        }
    }
}