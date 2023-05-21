package graduation.project.dongyang.el.ibda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.project.dongyang.EL.IBDA.R

class ClothAdapter (private val profileList: ArrayList<Cloth>) : RecyclerView.Adapter<ClothAdapter.CustomViewHolder>(){
//Profiles 의 객체를? 가져와서 리스트 형태로 만든다는 뜻 : RecyclerView.Adapter 는 RecyclerView.Adapter를 상속받는다는 뜻

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_cloth, parent, false) //(parent.context) => 연결될 Activity의 context를 가져오는 것. 즉 MainActivity의 경우 MainActivity의 모든 context가 됨
        return CustomViewHolder(view).apply {
            itemView.setOnClickListener{
                val curPos : Int = absoluteAdapterPosition
                val profile: Cloth = profileList.get(curPos)
                Toast.makeText(parent.context, "의상 : ${profile.name}\n가격 : ${profile.job}", Toast.LENGTH_SHORT).show()
            }
        } //class CustomViewHolder 에 view를 담아서 생성함.
    }

    override fun getItemCount(): Int {
        return profileList.size //출력할 총 리스트의 갯수
    }

    override fun onBindViewHolder(holder: ClothAdapter.CustomViewHolder, position: Int) {
        holder.gender.setImageResource(profileList.get(position).gender)// onCreateViewHolder 에서 만들어진 view를 연결해주는 역할. 지속적으로 호출되면서 match 시켜줌
        holder.name.text = profileList.get(position).name
        holder.job.text = profileList.get(position).job
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val gender = itemView.findViewById<ImageView>(R.id.id_profile) //성별
        val name  = itemView.findViewById<TextView>(R.id.tv_name) //이름
        val job  = itemView.findViewById<TextView>(R.id.tv_job) //직업
    }

}
