package com.example.dptermproject.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dptermproject.R
import com.example.dptermproject.databinding.ItemTodoListBinding

//dataset이 Adapter에 들어간다.
class TodoAdapter(
    private val dataset:List<Todo>,
    private val onClickDeleteIcon:(todo:Todo)->Unit):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(val binding:ItemTodoListBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        //recyclerview에 각 아이템에 들어갈 layout 인플레이트
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_todo_list, viewGroup, false)
        return TodoViewHolder(ItemTodoListBinding.bind(view))
    }

    override fun onBindViewHolder(viewHolder: TodoViewHolder, position: Int) {
        val listPosition = dataset[position]
        viewHolder.binding.etTodo.setText(listPosition.text)
        //viewHolder.binding.btnCheck

        //
        viewHolder.binding.btnDelete.setOnClickListener {
            onClickDeleteIcon.invoke(listPosition)
        }
    }

    //item의 갯수는 myDataset 사이즈만큼
    override fun getItemCount(): Int {
        return dataset.size
    }
}