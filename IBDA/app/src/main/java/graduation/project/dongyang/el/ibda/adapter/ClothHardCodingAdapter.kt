package graduation.project.dongyang.el.ibda.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import graduation.project.dongyang.el.ibda.activity.ClothesDetailsActivity
import graduation.project.dongyang.el.ibda.recycler.ClothesHardcodingItem
import kr.project.dongyang.EL.IBDA.R

class ClothHardCodingAdapter(private val clothList: ArrayList<ClothesHardcodingItem>) : RecyclerView.Adapter<ClothHardCodingAdapter.CustomViewHolder>(){


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
            Glide.with(itemView)
                .load(imageData.image) // 불러올 이미지 url
                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(image)
            name.text = imageData.name
            price.text = imageData.price.toString()
        }

        holder.bg.setOnClickListener{
            var intent = Intent(it.context, ClothesDetailsActivity::class.java)
            intent.putExtra("image", imageData.image.toString())
            intent.putExtra("name", holder.name.text)
            intent.putExtra("price", holder.price.text)
            it.context.startActivity(intent)
        }

        holder.checkbox.setButtonDrawable(R.drawable.selector_btnlike_24)
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val bg = itemView.findViewById<View>(R.id.tv_bg)
        val image: ImageView = itemView.findViewById<ImageView>(R.id.tv_image)
        val name: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        val price: TextView = itemView.findViewById<TextView>(R.id.tv_price)
        val checkbox : CheckBox = itemView.findViewById<CheckBox>(R.id.checkbox)
    }
}
