/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.*
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.launch

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality

    val nights = database.getAllNights()

    private var tonight = MutableLiveData<SleepNight?>()

    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    var startButtonVisible = Transformations.map(tonight) {
        it == null
    }

    var stopButtonVisible = Transformations.map(tonight) {
        it != null
    }

    var clearButtonVisible = Transformations.map(nights) {
        it?.isNotEmpty()
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    init {
        initTonight()
    }

    private val _navigateToSleepDetail = MutableLiveData<Long>()
    val navigateToSleepDetail: LiveData<Long>
        get() = _navigateToSleepDetail

    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }

    fun doneShowingSnakebar() {
        _showSnackbarEvent.value = false
    }

    private fun initTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): SleepNight? {
        var night = database.getTonight()
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }

    private suspend fun insert(night: SleepNight) {
        database.insert(night)
    }

    fun onStartTracking() {
        viewModelScope.launch {
            var newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    fun onStopTracking() {
        viewModelScope.launch {
            val startedNight =  tonight.value ?: return@launch
            startedNight.endTimeMilli = System.currentTimeMillis()
            update(startedNight)
            _navigateToSleepQuality.value = startedNight
        }
    }

    private suspend fun update(night: SleepNight) {
        database.update(night)
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            tonight.value = null
            _showSnackbarEvent.value = true
        }
    }

    private suspend fun clear() {
        database.clear()
    }

    fun onSleepNightClicked(id: Long) {
        _navigateToSleepDetail.value = id
    }
    fun onSleepDetailNavigated() {
        _navigateToSleepDetail.value = null
    }
}

