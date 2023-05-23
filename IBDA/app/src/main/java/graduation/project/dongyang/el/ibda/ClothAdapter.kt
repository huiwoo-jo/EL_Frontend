package graduation.project.dongyang.el.ibda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.project.dongyang.EL.IBDA.R

class ClothAdapter(private val clothList: ArrayList<ClothesResponseItem>) : RecyclerView.Adapter<ClothAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_cloth, parent, false) //(parent.context) => 연결될 Activity의 context를 가져오는 것. 즉 MainActivity의 경우 MainActivity의 모든 context가 됨
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener{
                val curPos : Int = absoluteAdapterPosition
                val cloth: ClothesResponseItem = clothList.get(curPos)
                Toast.makeText(parent.context, "의상 : ${cloth.name}\n가격 : ${cloth.price}", Toast.LENGTH_SHORT).show()
            }
        } //class CustomViewHolder 에 view를 담아서 생성함.
    }

    override fun getItemCount(): Int {
        return clothList.size //출력할 총 리스트의 갯수
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
       // holder.image.setImageResource(profileList.get(position).image)// onCreateViewHolder 에서 만들어진 view를 연결해주는 역할. 지속적으로 호출되면서 match 시켜줌
        holder.name.text = clothList[position].name
        holder.price.text = clothList[position].price
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        //val image  = itemView.findViewById<TextView>(R.id.tv_image)
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val price  = itemView.findViewById<TextView>(R.id.tv_price)

    }

}
