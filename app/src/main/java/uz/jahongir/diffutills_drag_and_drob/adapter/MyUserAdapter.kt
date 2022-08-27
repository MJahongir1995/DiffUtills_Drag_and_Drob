package uz.jahongir.diffutills_drag_and_drob.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.jahongir.diffutills_drag_and_drob.databinding.ItemRvBinding
import uz.jahongir.diffutills_drag_and_drob.models.User

class MyUserAdapter:ListAdapter<User,MyUserAdapter.VH>(MyDifUtils()) {

    class MyDifUtils : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem  == newItem
        }
    }

    inner class VH (private val itemRvBinding: ItemRvBinding): RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(user:User){
            itemRvBinding.tvName.text = user.name
            itemRvBinding.tvNumber.text = user.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        return holder.onBind(getItem(position))
    }
}