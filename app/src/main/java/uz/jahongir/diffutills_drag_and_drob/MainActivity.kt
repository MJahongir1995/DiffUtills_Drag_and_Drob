package uz.jahongir.diffutills_drag_and_drob

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import org.chromium.base.Callback
import uz.jahongir.diffutills_drag_and_drob.adapter.MyUserAdapter
import uz.jahongir.diffutills_drag_and_drob.adapter.UserRvAdapter
import uz.jahongir.diffutills_drag_and_drob.databinding.ActivityMainBinding
import uz.jahongir.diffutills_drag_and_drob.models.User
import uz.jahongir.diffutills_drag_and_drob.utils.ItemTouchHelperAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var list: ArrayList<User>
    private lateinit var userRvAdapter: UserRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        loadData()

        userRvAdapter = UserRvAdapter(list)
        binding.rv.adapter = userRvAdapter

        touchHelper()
    }

    private fun touchHelper() {
        val itemTouchHelperAdapter = object :ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userRvAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userRvAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }

        val touch = ItemTouchHelper(itemTouchHelperAdapter)
        touch.attachToRecyclerView(binding.rv)
    }

    private fun loadData() {
        list = ArrayList()

        for (i in 0..100){
            list.add(User("Johnny $i","6656196"))
            list.add(User("John $i","4444444"))
            list.add(User("Mike $i","666666666"))

        }
    }
}