package com.ssafy.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CustomListAdapter(context: Context, var items: ArrayList<MemoItem>, val layout: Int): ArrayAdapter<MemoItem>(context, layout, items) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(layout, null)
        view.findViewById<TextView>(R.id.todoTitle).text = items[position].title + " " + items[position].date
        return view
    }


}