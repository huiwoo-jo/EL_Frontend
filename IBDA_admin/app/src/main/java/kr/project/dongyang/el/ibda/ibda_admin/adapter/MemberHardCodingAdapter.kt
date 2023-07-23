package graduation.project.dongyang.el.ibda.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import graduation.project.dongyang.el.ibda.recycler.MemberHardcodingItem
import kr.project.dongyang.el.ibda.ibda_admin.R
import kr.project.dongyang.el.ibda.ibda_admin.activity.ClothesDetailsActivity
import kr.project.dongyang.el.ibda.ibda_admin.activity.MemberDetailsActivity
import kr.project.dongyang.el.ibda.ibda_admin.button.SquareImageButton

class MemberHardCodingAdapter(private val memberList: ArrayList<MemberHardcodingItem>) : RecyclerView.Adapter<MemberHardCodingAdapter.CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_member, parent, false)

        return CustomViewHolder(view).apply {
            itemView.setOnClickListener{
                absoluteAdapterPosition
            }
        } //class CustomViewHolder 에 view를 담아서 생성함.
    }

    override fun getItemCount(): Int {
        return memberList.size //출력할 총 리스트의 갯수
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // onCreateViewHolder 에서 만들어진 view를 연결해주는 역할. 지속적으로 호출되면서 match 시켜줌
        val imageData = memberList[position]
        val defaultImage = R.drawable.ibda_logo_png

        holder.apply {
            id.text = imageData.id.toString()
            name.text = imageData.name
            level.text = imageData.level
            age.text = imageData.age.toString()
        }

        /*
        holder.background.setOnClickListener{
            var intent = Intent(it.context, MemberDetailsActivity::class.java)
            intent.putExtra("id", holder.itemId)
            intent.putExtra("level", holder.level.text)
            intent.putExtra("name", holder.name.text)
            intent.putExtra("age", holder.age.text)
            it.context.startActivity(intent)
        }
         */

        holder.delete.setOnClickListener{
            val mDialogView = LayoutInflater.from(it.context)
                .inflate(R.layout.activity_popup, null)
            val mBuilder = AlertDialog.Builder(it.context)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            mDialogView.findViewById<TextView>(R.id.popup_info).setText(R.string.popup_member_del)

            val okButton = mDialogView.findViewById<Button>(R.id.popup_btnDone)
            okButton.setOnClickListener {
                Toast.makeText(it.context, "멤버가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                mAlertDialog.dismiss()
            }
            val noButton = mDialogView.findViewById<Button>(R.id.popup_btnCancle)
            noButton.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val background :View = itemView.findViewById(R.id.tv_bg)
        val id: TextView = itemView.findViewById<TextView>(R.id.tv_id)
        val name: TextView = itemView.findViewById<TextView>(R.id.tv_name)
        val level: TextView = itemView.findViewById<TextView>(R.id.tv_level)
        val age: TextView = itemView.findViewById<TextView>(R.id.tv_age)
        val delete : SquareImageButton = itemView.findViewById(R.id.btnMemberDelete)
        val edit : SquareImageButton = itemView.findViewById(R.id.btnMemberEdit)
    }
}