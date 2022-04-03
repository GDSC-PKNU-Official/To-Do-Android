package com.gdsc.todo.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gdsc.todo.data.entity.ToDoEntity
import com.gdsc.todo.data.local.ToDoDao

@Database(entities = [ToDoEntity::class], version = 2)
abstract class Database : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // FIXME: set not null
                database.execSQL(
                    """ 
                        CREATE TABLE todoentity (
                            `id` LONG,
                            `title` TEXT,
                            `contents` TEXT,
                            `date` LONG,
                            PRIMARY KEY(`id`)
                    )
                    """.trimMargin()
                )

//                database.execSQL(
//                    """
//                        INSERT INTO todoentity(
//                            id,
//                            title,
//                            contents,
//                            NULL
//                        ) SELECT id, title, contents
//                        FROM todo
//                    """.trimMargin()
//                )

                database.execSQL(
                    """
                        DROP TABLE todo
                    """.trimIndent()
                )
            }
        }
    }
}