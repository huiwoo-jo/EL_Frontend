package kr.project.dongyang.el.ibda.ibda_admin.sc_clothes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.project.dongyang.el.ibda.ibda_admin.R
import kr.project.dongyang.el.ibda.ibda_admin.activity.ClothesDetailsActivity
import kr.project.dongyang.el.ibda.ibda_admin.activity.ClothesEditActivity
import kr.project.dongyang.el.ibda.ibda_admin.activity.MainClothesActivity
import kr.project.dongyang.el.ibda.ibda_admin.button.SquareImageButton
import kr.project.dongyang.el.ibda.ibda_admin.data.ClothesResponseItem

class ClothAdapter(private val clothList: ArrayList<ClothesResponseItem>) : RecyclerView.Adapter<ClothAdapter.CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_cloth, parent, false)

        return CustomViewHolder(view).apply {
            itemView.setOnClickListener{
                absoluteAdapterPosition
            }
        } //class CustomViewHolder 에 view를 담아서 생성함.
    }

    override fun getItemCount(): Int {
        return clothList.size //출력할 총 리스트의 갯수
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // onCreateViewHolder 에서 만들어진 view를 연결해주는 역할. 지속적으로 호출되면서 match 시켜줌
        val imageData = clothList[position]
        val defaultImage = R.drawable.ibda_logo_png

        holder.apply {
            id.text="No. " + (imageData.id.toInt()+1).toString()
            Glide.with(itemView)
                .load(imageData.image) // 불러올 이미지 url
                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(image)
            name.text = imageData.name
            price.text = imageData.price
        }


        holder.background.setOnClickListener{
            var intent=Intent(it.context, ClothesDetailsActivity::class.java)
            intent.putExtra("id", imageData.id)
            it.context.startActivity(intent)
        }

        holder.btnEdit.setOnClickListener{
            var intent=Intent(it.context, ClothesEditActivity::class.java)
            intent.putExtra("id", imageData.id)
            it.context.startActivity(intent)
        }
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val background :View = itemView.findViewById(R.id.tv_background)
        val id: TextView = itemView.findViewById(R.id.tv_id)
        val image: ImageView = itemView.findViewById<ImageView>(R.id.tv_image)
        val name: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        val price: TextView = itemView.findViewById<TextView>(R.id.tv_price)

        val btnEdit: SquareImageButton = itemView.findViewById(R.id.tv_btnClothEdit)
    }
}
