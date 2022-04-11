package com.ssafy.memo

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.memo.util.Utils

private const val TAG = "MyAdapter_싸피"
class MemoAdapter
    (context: Context, val resource: Int, val objects: MutableList<MemoDto>)
    : RecyclerView.Adapter<ViewHolder>() {

    // viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(resource, parent, false)

        Log.d(TAG, "onCreateViewHolder: ViewHolder 객체 생성 !!")
        return ViewHolder(itemView)
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템 뷰에 표시
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 한 행에 데이터를 넣어준다.
        holder.num.text = objects[position].num.toString()
        
        holder.title.text = objects[position].title
        holder.contents.text = objects[position].content
        holder.date.text = Utils.formatter().format(objects[position].date)
        Log.d(TAG, "onBindViewHolder: $position 행에 ${objects[position]} 데이터 삽입")

        if(position % 3 == 0){
            holder.title.setBackgroundResource(R.color.green)
        }else if(position % 3 == 1){
            holder.title.setBackgroundResource(R.color.pink)
        }else{
            holder.title.setBackgroundResource(R.color.skyBlue)
        }
        // OnItemClickListener 연결
        holder.bindOnItemClickListener(onItemClickListener)
    }

    // 전체 아이템 개수 리턴
    override fun getItemCount(): Int {
        return objects.size
    }

    // 클릭 처리 기능이 내장되어 있지 않아 OnItemClickListener 인터페이스 구현
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
    lateinit var onItemClickListener: OnItemClickListener
}

// ViewHolder 클래스 생성
class ViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
    var num = itemView.findViewById(R.id.num) as TextView
    var title = itemView.findViewById(R.id.title) as TextView
    var contents = itemView.findViewById(R.id.contents) as TextView
    var date = itemView.findViewById(R.id.date) as TextView

    fun bindOnItemClickListener(onItemClickListener: MemoAdapter.OnItemClickListener) {
        itemView.setOnClickListener {
            onItemClickListener.onItemClick(it, adapterPosition)
        }
    }

    // Context 메뉴 생성
    override fun onCreateContextMenu(menu: ContextMenu?,
                                     v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.add(0, R.id.menuDelete, adapterPosition, "메모삭제")
    }
    
    // Context 메뉴 리스너 등록
    init {
        itemView.setOnCreateContextMenuListener(this)
    }
}