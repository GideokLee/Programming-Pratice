package com.ssafy.jetpack_03.jetpack_all

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.jetpack_03.jetpack_all.database.NotesDto
import com.ssafy.jetpack_03.jetpack_all.databinding.NotesRowBinding
import com.ssafy.jetpack_03.jetpack_all.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteListActivity : AppCompatActivity() {

    private lateinit var listAdapter: NoteListAdapter
    private val noteListViewModel: NoteListViewModel by viewModels()

    // DB 연결하고 Adapter 초기화
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        initAdapter()
    }

    private fun initAdapter() {
        listAdapter = NoteListAdapter()

        noteListViewModel.noteList.observe(this) { noteList ->
            listAdapter.listData = noteList
            listAdapter.notifyDataSetChanged()
        }

        findViewById<RecyclerView>(R.id.rv_list).apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(this@NoteListActivity)
        }
    }

    // options 메뉴(생성)
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menu.add(0, INSERT_ID, 0, R.string.menu_insert)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            INSERT_ID -> {
                createNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // note 생성
    private fun createNote() {
        val intent = Intent(this, NoteEditActivity::class.java)
        startActivity(intent)
    }

    // note 삭제
    private fun deleteNote(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            NoteRepository.get().deleteNote(NoteRepository.get().getNote(id))
        }
    }

    inner class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {
        var listData : List<NotesDto> = emptyList()

        inner class NoteViewHolder(private val binding: NotesRowBinding) : RecyclerView.ViewHolder(binding.root),
            View.OnCreateContextMenuListener {
            // context Menu 등록.필수.
            init {
                binding.viewModel = NoteViewModel()
                itemView.setOnCreateContextMenuListener(this)
            }

            fun bind(noteDto: NotesDto) {
                binding.apply {
                    viewModel?.note = noteDto
                    // 일일이 찾아서 UI에 넣어줄 필요가 없다. 즉시 binding 실행할 경우 호출.
                    executePendingBindings()
                }
            }

            // context Menu 생성
            override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
                val menuItem = menu?.add(0, DELETE_ID, 0, "Delete Memo")

                // context menu event 처리
                menuItem?.setOnMenuItemClickListener {
                    this@NoteListActivity.deleteNote(listData[adapterPosition].ID)
                    true
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListAdapter.NoteViewHolder {
            val binding = DataBindingUtil.inflate<NotesRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.notes_row,
                parent,
                false
            )

            return NoteViewHolder(binding).apply {
                // 목록 선택 event 처리 -> 수정으로 보냄.
                itemView.setOnClickListener {
                    val intent = Intent(parent.context, NoteEditActivity::class.java)
                    intent.putExtra(KEY_ROWID, listData[adapterPosition].ID)
                    this@NoteListActivity.startActivity(intent)
                }
            }
        }

        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            val noteDto = listData[position]
            holder.bind(noteDto)
        }

        override fun getItemCount(): Int {
            return listData.size
        }
    }

    companion object {
        private const val INSERT_ID = Menu.FIRST
        private const val DELETE_ID = Menu.FIRST + 1
    }
}