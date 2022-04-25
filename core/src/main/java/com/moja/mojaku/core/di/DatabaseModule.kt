package com.moja.mojaku.core.di

import android.content.Context
import androidx.room.Room
import com.moja.mojaku.core.data.source.local.room.MangaDao
import com.moja.mojaku.core.data.source.local.room.MangaDatabase
import com.moja.mojaku.core.utils.SecretDoor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MangaDatabase {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes(SecretDoor.getPassPhrase().toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            MangaDatabase::class.java, SecretDoor.getDatabaseName()
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMangaDao(database: MangaDatabase): MangaDao = database.mangaDao()
}