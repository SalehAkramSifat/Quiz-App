package com.sifat.quizapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sifat.quizapp.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {
    private lateinit var binding: FragmentQuestionBinding
    private var questions = arrayOf(
        "পৃথিবীর সবচেয়ে বড় মহাসাগরের নাম কী?",
        "বাংলাদেশের জাতীয় প্রাণী কোনটি?",
        "সবচেয়ে বড় স্থল প্রাণী কোনটি?",
        "বিশ্বের সবচেয়ে উঁচু পর্বতের নাম কী?",
        "বাংলা ভাষার সবচেয়ে জনপ্রিয় সাহিত্যিক কে?",
        "সূর্যের চারপাশে ঘূর্ণায়মান গ্রহগুলোর সংখ্যা কত?",
        "ডাইনোসরের সময়কাল কত লক্ষ বছর আগে ছিল?",
        "পৃথিবীতে সবচেয়ে বেশি জনসংখ্যার দেশ কোনটি?",
        "কোন মহাদেশে সবচেয়ে বেশি দেশ অবস্থিত?",
        "সৌরজগতের সবচেয়ে বড় গ্রহ কোনটি?"
    )

    private val options = arrayOf(
        arrayOf("আটলান্টিক মহাসাগর", "ভারতীয় মহাসাগর", "প্রশান্ত মহাসাগর", "আর্টিক মহাসাগর"),
        arrayOf("বাঘ", "হাঙ্গর", "ঈগল", "শেয়াল"),
        arrayOf("আফ্রিকান হাতি", "সাদা গন্ডার", "মেসো জিরাফ", "ভয়ঙ্কর গন্ডার"),
        arrayOf("K2", "এভারেস্ট", "কাংচেনজঙ্ঘা", "লোতসেই"),
        arrayOf("শরৎচন্দ্র চট্টোপাধ্যায়", "নজরুল ইসলাম", "রবীন্দ্রনাথ ঠাকুর", "সেলিনা হোসেন"),
        arrayOf("৭টি", "৮টি", "৯টি", "১০টি"),
        arrayOf("৫০ লক্ষ বছর", "১০০ লক্ষ বছর", "১৫০ লক্ষ বছর", "২০০ লক্ষ বছর"),
        arrayOf("ভারত", "চীন", "যুক্তরাষ্ট্র", "ব্রাজিল"),
        arrayOf("আফ্রিকা", "এশিয়া", "ইউরোপ", "উত্তর আমেরিকা"),
        arrayOf("পৃথিবী", "বৃহস্পতী", "শনির গ্রহ", "নেপচুন")
    )

    private val correctAnswers = arrayOf(2, 0, 0, 1, 2, 1, 2, 1, 0, 1)

    private var currentQuestionIndex = 0
    private var score = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)

        displayQuestion()

        binding.option1.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3.setOnClickListener {
            checkAnswer(2)
        }
        binding.option4.setOnClickListener {
            checkAnswer(3)
        }
        binding.next.setOnClickListener {
            restartQuiz()
        }

        return binding.root
    }

    private fun showResult() {
        Toast.makeText(requireContext(), "Your Score is: $score out of ${questions.size}", Toast.LENGTH_SHORT).show()
        binding.next.isEnabled = true
    }

    private fun displayQuestion() {
        binding.questionBox.text = questions[currentQuestionIndex]
        binding.option1.text = options[currentQuestionIndex][0]
        binding.option2.text = options[currentQuestionIndex][1]
        binding.option3.text = options[currentQuestionIndex][2]
        binding.option4.text = options[currentQuestionIndex][3]

    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
        } else {
            Toast.makeText(
                requireContext(),
                "Incorrect! The correct answer is: ${options[currentQuestionIndex][correctAnswerIndex]}",
                Toast.LENGTH_SHORT
            ).show()
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.questionBox.postDelayed({ displayQuestion() }, 1500)
        } else {
            showResult()
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.next.isEnabled = false
    }
}
