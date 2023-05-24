package graduation.project.dongyang.el.ibda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.project.dongyang.EL.IBDA.R

class ClothFooterAdapter(private val footerProfileList: ArrayList<ClothFooter>) : RecyclerView.Adapter<ClothFooterAdapter.CustomViewHolder>() {
    // ClothFooterAdapter는 RecyclerView.Adapter를 상속받습니다.

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothFooterAdapter.CustomViewHolder {
        // onCreateViewHolder 함수는 뷰 홀더를 생성합니다.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_cloth_footer, parent, false)
        // R.layout.activity_item_cloth_footer를 인플레이션하여 뷰를 생성합니다.
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener {

            }
        }
        // 새로운 CustomViewHolder를 생성하여 반환합니다.
    }

    override fun getItemCount(): Int {
        return footerProfileList.size
        // 데이터 세트의 아이템 수를 반환합니다.
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // onBindViewHolder 함수는 뷰 홀더에 데이터를 바인딩합니다.
        holder.image.setImageResource(footerProfileList[position].image)
        // 현재 위치의 프로필 이미지를 설정합니다.
        holder.name.text = footerProfileList[position].name
        // 현재 위치의 이름을 설정합니다.
        holder.brand.text = footerProfileList[position].brand
        // 현재 위치의 브랜드를 설정합니다.
        holder.price.text = footerProfileList[position].price.toString()
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // CustomViewHolder는 RecyclerView.ViewHolder를 상속받는 클래스입니다.
        val image: ImageView = itemView.findViewById<ImageView>(R.id.footer_profile)
        // ImageView 객체를 초기화합니다.
        val name: TextView = itemView.findViewById<TextView>(R.id.footer_name)
        // TextView 객체를 초기화합니다.
        val brand: TextView = itemView.findViewById<TextView>(R.id.footer_brand)
        // TextView 객체를 초기화합니다.
        val price: TextView = itemView.findViewById<TextView>(R.id.footer_price)
    }
}
