package graduation.project.dongyang.el.ibda

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.project.dongyang.EL.IBDA.R

class ClothAdapter(private val clothList: ArrayList<ClothesResponseItem>) : RecyclerView.Adapter<ClothAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_cloth, parent, false) //(parent.context) => 연결될 Activity의 context를 가져오는 것. 즉 MainActivity의 경우 MainActivity의 모든 context가 됨


        return CustomViewHolder(view).apply {
            itemView.setOnClickListener{
                val curPos : Int = absoluteAdapterPosition
                val cloth: ClothesResponseItem = clothList[curPos]
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
            Glide.with(itemView)
                .load(imageData.image) // 불러올 이미지 url
                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(image)
            name.text = imageData.name
            price.text = imageData.price
        }

        holder.image.setOnClickListener{
            var intent = Intent(it.context, DetailClothes::class.java)
            intent.putExtra("id", imageData.id)
            it.context.startActivity(intent)
        }
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.tv_image)
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val price  = itemView.findViewById<TextView>(R.id.tv_price)
    }
}
