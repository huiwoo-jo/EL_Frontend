package graduation.project.dongyang.el.ibda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.project.dongyang.EL.IBDA.R

class ListAdapterVertical:  RecyclerView.Adapter<ListAdapterVertical.ListAdapter>(){
    var titles = arrayListOf("Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8")
    var contents = arrayListOf("Content 1", "Content 2", "Content 3", "Content 4", "Content 5", "Content 6", "Content 7", "Content 8")

    class ListAdapter(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter {
        return ListAdapter(LayoutInflater.from(parent.context).inflate(R.layout.list_item_vert, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter, position: Int) {
        holder.layout.textTitle.text = titles[position]
        holder.layout.textContent.text = contents[position]
    }


    override fun getItemCount(): Int {
        return contents.size
    }
}