package basketballCounter

import android.app.Application
import android.util.Log

private const val TAG = "BasketballCounterApp"

// creating a singleton
class BasketballCounterApplication : Application() {



    override fun onCreate() {
        super.onCreate()
        BasketballGameRepository.initialize(this)
        Log.d(TAG, "onCreate() called")
        Log.d(TAG, "BasketballGameRepository initialized")
    }
}