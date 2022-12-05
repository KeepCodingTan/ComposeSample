import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.common.composesample.TodoItem

/**
 * @Author: Sun
 * @CreateDate: 2022/12/5
 * @Description: java类作用描述
 */
class TodoViewModel: ViewModel() {
    var _items = mutableStateListOf<TodoItem>()
       private set

    private var currentEditPos by mutableStateOf<Int>(-1)

    val currentEdit : TodoItem?
        get() = _items.getOrNull(currentEditPos)


    fun addItem(item:TodoItem){
        _items.add(item)
    }

    fun removeItem(item:TodoItem){
        _items.remove(item)
        onEditDown()
    }

    fun startEdit(item: TodoItem){
        currentEditPos = _items.indexOf(item)
    }

    fun onEdit(item: TodoItem){
        val currentEditItem = requireNotNull(currentEdit)
        require(currentEditItem.id == item.id){
            "only edit item can be changed!"
        }
        Log.d("sun","editing=${currentEditItem.id}")
        _items[currentEditPos] = item
    }

    fun onEditDown(){
        currentEditPos = -1
    }
}