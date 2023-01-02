package com.example.android.unscramble.ui.game


import android.text.Spannable
import android.text.SpannableString
import android.text.style.TtsSpan
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*


class GameViewModel : ViewModel(){
    private var _score = MutableStateFlow<Int>(0)
    val score: StateFlow<Int>
        get() = _score
    private var _currentWordCount = MutableStateFlow<Int>(0)
    val currentWordCount: StateFlow<Int>
        get() = _currentWordCount
    private val _currentScrambledWord = MutableStateFlow("")
    val currentScrambledWord: StateFlow<Spannable> = _currentScrambledWord
        .map {
            val scrambledWord = it.toString()
            val spannable: Spannable = SpannableString(scrambledWord)
            spannable.setSpan(
                TtsSpan.VerbatimBuilder(scrambledWord).build(),
                0,
                scrambledWord.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            spannable
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),SpannableString(""))


    /*data class PC1(val name:String)
    private  val _tmf = MutableStateFlow(PC1(""))
    val tf :StateFlow<PC1> = _tmf
        .map {
            it
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000),PC1(""))
    */

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    // 임시 생성
    val tmpCurrentWord : String
        get() = currentWord

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    private fun getNextWord() {
        currentWord = allWordsList.random()

        val tempWord = currentWord.toCharArray()
        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if (wordsList.contains(currentWord)) {
            this.getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }


}