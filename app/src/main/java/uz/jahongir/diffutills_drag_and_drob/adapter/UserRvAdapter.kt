package uz.jahongir.diffutills_drag_and_drob.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.jahongir.diffutills_drag_and_drob.databinding.ItemRvBinding
import uz.jahongir.diffutills_drag_and_drob.models.User
import uz.jahongir.diffutills_drag_and_drob.utils.ItemTouchHelperAdapter
import java.util.*
import kotlin.collections.ArrayList

class UserRvAdapter(val list: ArrayList<User>) : RecyclerView.Adapter<UserRvAdapter.VH>(),
    ItemTouchHelperAdapter {

    inner class VH(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User) {
            itemRv.tvName.text = user.name
            itemRv.tvNumber.text = user.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i + 1)
            }
        }else{
            for (i in fromPosition until toPosition) {
                Collections.swap(list, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }


}
