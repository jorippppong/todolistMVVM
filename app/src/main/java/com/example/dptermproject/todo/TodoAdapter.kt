package com.example.dptermproject.todo

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.dptermproject.R
import com.example.dptermproject.databinding.ItemTodoListBinding

//dataset이 Adapter에 들어간다.
class TodoAdapter(
    private var dataset:List<Todo>,
    private val onClickDeleteIcon:(todo:Todo)->Unit,
    private val onClickModifyIcon:(todo:Todo, newText:String)->Unit,
    private val onClickCheckIcon:(todo:Todo)->Unit):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(val binding:ItemTodoListBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        //recyclerview에 각 아이템에 들어갈 layout 인플레이트
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo_list, viewGroup, false)
        return TodoViewHolder(ItemTodoListBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: TodoViewHolder, position: Int) {
        val listPosition = dataset[position]

        //text
        viewHolder.binding.etTodo.setText(listPosition.text)

        //check image
        if(!listPosition.isDone){
            viewHolder.binding.btnCheck.setImageResource(R.drawable.iv_todo_disabled)
        }else{
            viewHolder.binding.btnCheck.setImageResource(R.drawable.iv_todo_checked)
        }

        //edittext
        if(listPosition.modify){
            modifyEdittext(viewHolder, listPosition)
        }

        //check button 구현
        viewHolder.binding.btnCheck.setOnClickListener {
            onClickCheckIcon.invoke(listPosition)
        }

        //투두 수정 구현
        viewHolder.binding.btnModify.setOnClickListener {
            modifyEdittext(viewHolder, listPosition)
        }

        //삭제 구현
        viewHolder.binding.btnDelete.setOnClickListener {
            onClickDeleteIcon.invoke(listPosition)
        }
    }

    //item의 갯수는 myDataset 사이즈만큼
    override fun getItemCount(): Int {
        return dataset.size
    }

    fun setData(newData:List<Todo>){
        dataset = newData
        notifyDataSetChanged()
    }

    private fun modifyEdittext(viewHolder: TodoViewHolder, listPosition:Todo){
        viewHolder.binding.btnDelete.visibility = View.INVISIBLE
        viewHolder.binding.btnModify.visibility = View.INVISIBLE
        viewHolder.binding.btnCheck.isEnabled = false
        viewHolder.binding.etTodo.isEnabled = true
        viewHolder.binding.etTodo.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#6B9AC4"))
        viewHolder.binding.etTodo.setSelection(listPosition.text.length)
        viewHolder.binding.etTodo.requestFocus()

        viewHolder.binding.etTodo.setOnEditorActionListener { _, actionId, event ->
            if ((actionId == EditorInfo.IME_ACTION_DONE) || (event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                listPosition.modify = false
                viewHolder.binding.etTodo.isEnabled = false
                viewHolder.binding.btnDelete.visibility = View.VISIBLE
                viewHolder.binding.btnModify.visibility = View.VISIBLE
                viewHolder.binding.btnCheck.isEnabled = true
                viewHolder.binding.etTodo.backgroundTintList =
                    ColorStateList.valueOf(Color.parseColor("#00000000"))
                if (viewHolder.binding.etTodo.text.toString().isEmpty()) {
                    //투두에 값이 없는 경우 ; 삭제
                    onClickDeleteIcon.invoke(listPosition)
                } else {
                    val newText = viewHolder.binding.etTodo.text.toString()
                    onClickModifyIcon.invoke(listPosition, newText)
                }
                true
            } else {
                false
            }
        }
    }
}