package ru.dekabrsky.consecutivepractice2025.listWithDetails.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidpractice.domain.model.Genre

@Entity
class MovieDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "movieName") val name: String?,
    @ColumnInfo(name = "movieType") val type: String?,
    @ColumnInfo(name = "movieGenres") val genre: String?,
    @ColumnInfo(name = "movieUrl") val url: String?,
)