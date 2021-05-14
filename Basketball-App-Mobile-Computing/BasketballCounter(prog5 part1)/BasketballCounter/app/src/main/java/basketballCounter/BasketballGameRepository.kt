package basketballCounter

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import database.BasketballGameDatabase
import java.util.*
import java.util.concurrent.Executors
import java.io.File as File

private const val TAG = "BasketballGameRepositry"
private const val DATABASE_NAME = "game-database"

/**
 * Repository class for BasketballGame that builds the database and holds
 * Writing and Reading functionality. This class is a Singleton
 */
class BasketballGameRepository private constructor(context: Context) {

    // build the database to store game data
    private val database : BasketballGameDatabase = Room.databaseBuilder(
        context.applicationContext,
        BasketballGameDatabase::class.java,
        DATABASE_NAME

    ).build()


    private val basketballGameDao = database.basketballGameDao()
    private val executor = Executors.newSingleThreadExecutor()
    private val filesDir = context.applicationContext.filesDir


    /**
     * Gets a LiveData list of basketball games from the DB
     */
    fun getGames(): LiveData<List<BasketballGame>> = basketballGameDao.getGames()

    /**
     * Gets a LiveData basketballgame from the DB based on the UUID
     */
    fun getGame(id: UUID): LiveData<BasketballGame?> = basketballGameDao.getGame(id)

    /**
     * Takes a basketball game and updates it in the database wherever the UUID exists
     */
    fun updateGame(game: BasketballGame) {
        Log.d(TAG, "updateGame() called")
        executor.execute {
            basketballGameDao.updateGame(game)
        }
    }

    fun getPhotoFile(game: BasketballGame): File = File(filesDir, game.photoFileName)
    fun getTeamBPhotoFile(game: BasketballGame): File = File(filesDir, game.teamBPhotoFileName)

    /**
     * Takes a basketball game and adds it to the database
     */
    fun addGame(game: BasketballGame) {
        Log.d(TAG, "addGame() called")
        executor.execute {
            basketballGameDao.addGame(game)
        }
    }

    /**
     * Companion object that creates the Singleton Instance and holds its getter function
     */
    companion object {
        private var INSTANCE: BasketballGameRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = BasketballGameRepository(context)
            }
        }

        fun get(): BasketballGameRepository {
            return INSTANCE ?:
            throw IllegalStateException("BasketballGameRepository must be initialized")
        }
    }
}