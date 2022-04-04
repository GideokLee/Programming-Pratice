package com.ssafy.memo

interface IMemoItemMgr {

    // Memo를 저장하고 있는 리스트
    var memos: ArrayList<MemoItem>

    // Memo 리스트를 받아오는 함수
    fun getList(): ArrayList<MemoItem>

    // Memo의 개수를 받아오는 함수
    fun size(): Int

    // index를 가지고 원하는 메모를 받아오는 함수
    fun search(index: Int): MemoItem

    // Memo를 추가하는 함수
    fun add(m: MemoItem)

    // Memo를 수정하는 함수
    fun update(index: Int, item: MemoItem)

    // Memo를 삭제하는 함수
    fun remove(index: Int)

    // 메모리스트 전체 삭제하는 함수
    fun clear()
}