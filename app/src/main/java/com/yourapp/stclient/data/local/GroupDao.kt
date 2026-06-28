package com.sillytavern.kotlin.engine

interface GroupDao {
    suspend fun allGroups(): List<GroupChat>
    suspend fun getGroup(id: String): GroupChat?
    suspend fun upsert(group: GroupChat)
    suspend fun deleteGroup(id: String)
}
