package graduation.project.dongyang.el.ibda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_hori.view.*
import kr.project.dongyang.EL.IBDA.R
import kr.project.dongyang.EL.IBDA.databinding.ActivityLoadingBinding
import kr.project.dongyang.EL.IBDA.databinding.ListItemHoriBinding

class ListAdapterHorizontal(var list: ArrayList<String>):  RecyclerView.Adapter<ListAdapterHorizontal.ListAdapter>(){

    class ListAdapter(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(LayoutInflater.from(parent.context).inflate(R.layout.list_item_hori, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.textImg.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}