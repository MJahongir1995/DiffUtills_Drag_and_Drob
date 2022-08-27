package uz.jahongir.diffutills_drag_and_drob.utils

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition:Int)

    fun onItemDismiss(position:Int)
}