package com.ssafy.memo

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MemoAdapter() : RecyclerView.Adapter<MemoViewHolder>() {
    var listData = mutableListOf<MemoDto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.memo_list_item, parent, false)
        return MemoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.apply {
            bindInfo(listData[position])
            setItemClickListener(itemClickListener)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    // 클릭 처리 기능이 내장되어 있지 않아 OnItemClickListener 인터페이스 구현
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }
    lateinit var itemClickListener : ItemClickListener
}

class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener{
    fun bindInfo(memoDto: MemoDto){
        itemView.findViewById<TextView>(R.id.memoTitle).text = memoDto.title
        itemView.findViewById<TextView>(R.id.memoContent).text = memoDto.content
        itemView.findViewById<TextView>(R.id.memoDate).text = memoDto.date
    }
    fun setItemClickListener(itemClickListener: MemoAdapter.ItemClickListener){
        itemView.setOnClickListener {
            itemClickListener.onClick(it, adapterPosition)
        }
    }
    // Context 메뉴 생성
    override fun onCreateContextMenu(menu: ContextMenu?,
                                     v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        menu?.add(0, 0, adapterPosition, "삭제 하기")
    }

    // Context 메뉴 리스너 등록
    init {
        itemView.setOnCreateContextMenuListener(this)
    }
}