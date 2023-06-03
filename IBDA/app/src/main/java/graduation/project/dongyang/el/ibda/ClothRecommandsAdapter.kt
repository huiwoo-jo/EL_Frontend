package graduation.project.dongyang.el.ibda

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.project.dongyang.EL.IBDA.R

class ClothRecommandsAdapter(private val pantsClothList: ArrayList<ClothItemType>) : RecyclerView.Adapter<ClothRecommandsAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothRecommandsAdapter.CustomViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_cloth, parent, false)

        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {

            }
        }

    }

    override fun getItemCount(): Int {
        return pantsClothList.size
        // 데이터 세트의 아이템 수를 반환합니다.
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // onBindViewHolder 함수는 뷰 홀더에 데이터를 바인딩합니다.
        holder.image.setImageResource(pantsClothList[position].image)
        // 현재 위치의 프로필 이미지를 설정합니다.
        //holder.name.text = pantsClothList[position].name
        // 현재 위치의 이름을 설정합니다.
        holder.brand.text = pantsClothList[position].brand
        // 현재 위치의 브랜드를 설정합니다.
        holder.price.text = pantsClothList[position].price.toString()
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // CustomViewHolder는 RecyclerView.ViewHolder를 상속받는 클래스입니다.
        val image: ImageView = itemView.findViewById<ImageView>(R.id.tv_image)
        // ImageView 객체를 초기화합니다.
        //val name: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        // TextView 객체를 초기화합니다.
        val brand: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        // TextView 객체를 초기화합니다.
        val price: TextView = itemView.findViewById<TextView>(R.id.tv_price)
    }
}
