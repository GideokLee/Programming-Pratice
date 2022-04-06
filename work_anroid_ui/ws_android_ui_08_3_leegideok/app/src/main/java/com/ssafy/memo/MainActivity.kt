package com.ssafy.memo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // ListView에 들어갈 String List
    private lateinit var listViewItems: MutableList<MemoDto>

    // ListView
    private lateinit var listView: RecyclerView

    // DB 선언부
    private lateinit var memoDao: MemoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DB 초기화
        initDatabase()

        // 앱 최초 실행 시, 권한 설정
        requirePermission()

        listView = findViewById(R.id.listView)
        listView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)

        updateListView()

        // 새 Activity를 실행할 때는 onCreate만 실행되기 때문에
        // 앱 실행 전 메시지 수신 시 따로 실행해 줘야한다.
        createSMSMemo(this.intent)
    }

    // ListView와 Adapter 연결하고 ListView 내용 갱신하기
    private fun updateListView() {

        // 새 리스트 만들기
        listViewItems = memoDao.selectAllMemos()

        // Adapter 생성
        val adapter = MemoAdapter(this, R.layout.item_article_memo, listViewItems)

        // ListView와 Adapter 연결
        listView.adapter = adapter

        // ListView 변경 적용
        adapter.notifyDataSetChanged()

        // 4. 행을 터치했을 때 실행하는 콜백 함수
        val intent = Intent(this, MemoEditActivity::class.java)
        val itemClickListener =  object : MemoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                intent.putExtra("dto", listViewItems[position])
                memoEditActivityLauncher.launch(intent)
            }
        }
        adapter.onItemClickListener = itemClickListener
    }

    // MemoEditActivity에서 MainActivity로 돌아올 때 수행할 작업
    private val memoEditActivityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val intent: Intent? = it.data

            val dto = intent!!.getSerializableExtra("dto") as MemoDto

            //state 0 : 등록 1 : 수정
            val state = intent.getIntExtra("OState",0)

            val msg = when (state) {
                0 -> {
                    memoDao.insertMemo(dto)
                    "등록되었습니다."
                }
                1 -> {
                    memoDao.updateMemo(dto)
                    "수정되었습니다."
                }
                else -> "잘못된 상태 값을 전달 받았습니다."
            }

            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            updateListView()
        }
    }

    // 길게 누를 시 실행
    override fun onContextItemSelected(item: MenuItem): Boolean {
        memoDao.deleteMemo(listViewItems[item.order].num)
        Toast.makeText(applicationContext,"삭제되었습니다.", Toast.LENGTH_SHORT).show()
        updateListView()

        return super.onContextItemSelected(item)
    }

    // 2-3. 옵션 메뉴 구현
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuCreate -> {
                // 등록
                val intent = Intent(this, MemoEditActivity::class.java)

                // 값의 초기화
                intent.putExtra("dto", MemoDto(-1, "", "", 0))
                memoEditActivityLauncher.launch(intent)
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // SMS 수신 Permission 함수
    private fun requirePermission() {
        val permissions = arrayOf(Manifest.permission.RECEIVE_SMS)
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, permissions, 1)
        }
    }

    // 메세지 MEMO 추가
    private fun createSMSMemo(intent: Intent?) {
        if (intent != null) {
            // MemoReceiver에서 온 Data
            val smsSender = intent.getStringExtra("SMSSender").toString()
            val smsContents = intent.getStringExtra("SMSContents").toString()
            val smsDate = intent.getLongExtra("SMSDate", 0)
            val smsState = intent.getBooleanExtra("SMSstate",false)

            // 메시지 수신으로 앱이 실행되는 것과 아닐 때를 구분해 주기 위해
            if (smsState) {
                memoDao.insertMemo(MemoDto(smsSender, smsContents, smsDate))
            }

            updateListView()
        }
    }

    // Activity를 계속 새로 실행하지 않고 재사용할 경우 onCreate가 아닌 onNewIntent를 불러온다.
    // Activity를 새로 시작하지 않고 재사용하기 위해 onNewIntent를 만들어준다.
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        createSMSMemo(intent)
    }

    // DB 초기화
    private fun initDatabase() {
        //helper 인스턴스 생성 -> helper() 생성자가 실행되어 memos 파일이 생성된다.
        memoDao = MemoDao()
        memoDao.dbOpenHelper(this)
        memoDao.open()
    }
}